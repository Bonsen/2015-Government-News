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
		// �����ݽ��н���
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "utf-8"));
			String temp;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
			br.close();
			result = sb.toString();
			// ��ӡandroid���ϴ���JSON����
			System.out.println("result is : " + result);
			NewsCode = JsonTools.GetNewsCode(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// �ж�
		if (NewsCode != 0) {
			switch (NewsCode) {
			case OperateCode.ADD_NEWS:// ���һ������
				try {
					int flag = DBOperate.AddNews(JsonTools.getNews(result));
					if (flag == 1) {
						// ����Ҫ�����е����ݻط�
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

			case OperateCode.UPDATE_NEWS:// ����һ������
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

			case OperateCode.DELETE_NEWS:// ɾ��һ������

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

			case OperateCode.CHECK_NEWS:// �������
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

			case OperateCode.SEARCH_NEWS:// ����
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

			case OperateCode.LOGIN:// ��½
				User user = JsonTools.GetUser(result);
				System.out.println("user is : " + user.toString());
				// ��֤�û�
				try {
					if ((DBOperate.CheckUser(user).getPassword()).equals(user
							.getPassword())) {
						user = DBOperate.CheckUser(user);
						System.out.println(user.toString());
						if (user.getStatus() == 1) {
							// ȡ��ȫ����Ŀ����������,����ǹ���Ա
							out.println(URLEncoder.encode(
									DataProvider.ProviderInitDataAdmin(user),
									"utf-8"));
						} else {
							// ȡ����Ӧ����Ŀ���������ݣ��������ͨ�û�
							out.println(URLEncoder.encode(
									DataProvider.ProviderInitDataUser(user),
									"utf-8"));
						}
					} else {
						System.out.println("�û���֤ʧ�ܣ�");
						// ����ʧ����
						out.println(OperateCode.FAIL);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				out.flush();
				out.close();
				break;
			case OperateCode.COLUMN:// ��ȡ������Ŀ
				break;

			case OperateCode.NEWS_BY_COLUMN:// ����������Ŀ��ȡ�����б�
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

			case OperateCode.NEWS_BY_COLUMN_ADMIN:// ����Ա����������Ŀ��ȡ�����б�
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
