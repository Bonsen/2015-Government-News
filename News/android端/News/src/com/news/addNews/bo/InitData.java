package com.news.addNews.bo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.widget.EditText;
import android.widget.TextView;

import com.news.content.bean.NewsContentBean;

public class InitData {

	@TargetApi(Build.VERSION_CODES.DONUT)
	public static List<Map<String, String>> loadData(
			List<Map<String, String>> imgList, EditText editNewsContent,
			Context activityContext, String editModel,
			NewsContentBean newsContentBean, EditText editNewsTitle,
			TextView column, TextView tTime, TextView author) {
		// 如果是新增记事模式，则将editText清空
		if (editModel.equals("newsAdd")) {
			editNewsContent.setText("");
			SharedPreferences sharedPreferences = (activityContext)
					.getSharedPreferences("ShareData", Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = sharedPreferences.edit();

			author.setText("作者：" + sharedPreferences.getString("author", ""));
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm");
			Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
			String time = formatter.format(curDate);
			editor.putString("time", time);
			editor.commit();
			tTime.setText(time.substring(0, 10));
			column.setText(sharedPreferences.getString("pColumn", "") + "-"
					+ sharedPreferences.getString("cColumn", ""));
		}
		// 如果编辑的是已存在的记事，则将数据库的保存的数据取出，并显示在EditText中
		else if (editModel.equals("update")) {
			editNewsTitle.setText(newsContentBean.getTitle());
			tTime.setText(newsContentBean.getTime().substring(0,10));
			author.setText("作者：" + newsContentBean.getAuthor());
			column.setText(newsContentBean.getpColumn() + "-"
					+ newsContentBean.getcColumn());
			String context = newsContentBean.getContent();
			// 定义正则表达式，用于匹配路径
			// Pattern p = Pattern.compile("/([^\\.]*)\\.\\w{3}");//
			// /([^\.]*)\.\w{3}
			// Matcher m = p.matcher(context);
			try {
				// if (m.groupCount() > 1) {
				// int startIndex = 0;
				// for (int i = 0; i < m.groupCount(); i++) {
				// // 取出路径前的文字
				// if (m.start() > 0) {
				// editNewsContent.append(context.substring(
				// startIndex, m.start()));
				// }
				// SpannableString ss = new SpannableString(m.group()
				// .toString());
				// // 取出路径
				// String path = m.group().toString();
				// // 取出路径的后缀
				// String type = path.substring(path.length() - 3,
				// path.length());
				// Bitmap bm = null;
				// Bitmap rbm = null;
				// bm = BitmapFactory.decodeFile(m.group());
				// // 缩放图片
				// rbm = ImageBo.resize(bm, 480);
				// // 为图片添加边框效果
				// rbm = ImageBo.getBitmapHuaSeBianKuang(rbm);
				// ImageSpan span = new ImageSpan(activityContext, rbm);
				// ss.setSpan(span, 0, m.end() - m.start(),
				// Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				// editNewsContent.append(ss);
				// startIndex = m.end();
				//
				// // 用List记录该录音的位置及所在路径，用于单击事件
				// Map<String, String> map = new HashMap<String, String>();
				// map.put("location", m.start() + "-" + m.end());
				// map.put("path", path);
				// imgList.add(map);
				// // 将最后一个图片之后的文字添加在TextView中
				// editNewsContent.append(context.substring(startIndex,
				// context.length()));

				// }
				// } else {
				editNewsContent.setText(context);
				// }
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return imgList;
	}
}
