package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import json.JsonToObject;
import json.ObjectToJson;

import Code.OperateCode;
import bean.User;
import bo.DataProvider;
import bo.JsonTools;
import dao.DBOperate;

public class NewsControl extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public NewsControl() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@SuppressWarnings("deprecation")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer("");
		String result = "";
		int NewsCode = 0;
		// 将数据进行解析
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "utf-8"));
			String temp;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
			br.close();
			result = sb.toString();
			// 打印android端上传的JSON数据
			System.out.println("result is : " + result);
			NewsCode = JsonTools.GetNewsCode(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 判断
		if (NewsCode != 0) {
			switch (NewsCode) {
			case OperateCode.ADD_NEWS:// 添加一条新闻
				try {
					int flag = DBOperate.AddNews(JsonTools.getNews(result));
					if (flag == 1) {
						// 这里要将所有的数据回发
						out.println(OperateCode.ADD_NEWS_SUCCESS);
						out.flush();
						out.close();
					} else {
						out.println(OperateCode.ADD_NEWS_FAIL);
						out.flush();
						out.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case OperateCode.UPDATE_NEWS:// 更新一条新闻
				try {
					int flag = DBOperate.UpdateNews(JsonTools
							.UpdateNews(result));
					if (flag == 1) {
						out.println(OperateCode.SUCCESS);
						out.flush();
						out.close();
					} else {
						out.println(OperateCode.FAIL);
						out.flush();
						out.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;

			case OperateCode.DELETE_NEWS:// 删除一条新闻

				try {
					int flag = DBOperate.DeleteNews(JsonToObject.GetId(result));
					if (flag == 1) {
						out.println(OperateCode.SUCCESS);
						out.flush();
						out.close();
					} else {
						out.println(OperateCode.FAIL);
						out.flush();
						out.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case OperateCode.CHECK_NEWS:// 审核新闻
				try {
					int flag = DBOperate.CheckNews(JsonToObject.GetId(result));
					if (flag == 1) {
						out.println(OperateCode.SUCCESS);
						out.flush();
						out.close();
					} else {
						out.println(OperateCode.FAIL);
						out.flush();
						out.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case OperateCode.SEARCH_NEWS:// 搜索
				try {
					out.println(URLEncoder.encode(ObjectToJson
							.createJsonString("news",
									(DBOperate.SearchNews(JsonToObject
											.SearchNews(result)))), "utf-8"));
					out.flush();
					out.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;

			case OperateCode.LOGIN:// 登陆
				User user = JsonTools.GetUser(result);
				System.out.println("user is : " + user.toString());
				// 验证用户
				try {
					if ((DBOperate.CheckUser(user).getPassword()).equals(user
							.getPassword())) {
						user = DBOperate.CheckUser(user);
						System.out.println(user.toString());
						if (user.getStatus() == 1) {
							// 取出全部栏目，新闻数据,如果是管理员
							out.println(URLEncoder.encode(
									DataProvider.ProviderInitDataAdmin(user),
									"utf-8"));
						} else {
							// 取出相应的栏目，新闻数据，如果是普通用户
							out.println(URLEncoder.encode(
									DataProvider.ProviderInitDataUser(user),
									"utf-8"));
						}
					} else {
						System.out.println("用户验证失败！");
						// 发送失败码
						out.println(OperateCode.FAIL);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				out.flush();
				out.close();
				break;
			case OperateCode.COLUMN:// 获取新闻栏目
				break;

			case OperateCode.NEWS_BY_COLUMN:// 根据新闻栏目获取新闻列表
				try {
					out.println(URLEncoder.encode(
							DataProvider.GetNewsByColumn(result), "utf-8"));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				out.flush();
				out.close();
				break;

			case OperateCode.NEWS_BY_COLUMN_ADMIN:// 管理员根据新闻栏目获取新闻列表
				try {
					String jsonStirng=DataProvider.GetNewsByColumnAdmin(result);
					System.out.println(jsonStirng);
					out.println(URLEncoder.encode(jsonStirng, "utf-8"));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				out.flush();
				out.close();
				break;
			}
		}
		out.flush();
		out.close();
	}
}
