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
		// �������������ģʽ����editText���
		if (editModel.equals("newsAdd")) {
			editNewsContent.setText("");
			SharedPreferences sharedPreferences = (activityContext)
					.getSharedPreferences("ShareData", Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = sharedPreferences.edit();

			author.setText("���ߣ�" + sharedPreferences.getString("author", ""));
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm");
			Date curDate = new Date(System.currentTimeMillis());// ��ȡ��ǰʱ��
			String time = formatter.format(curDate);
			editor.putString("time", time);
			editor.commit();
			tTime.setText(time.substring(0, 10));
			column.setText(sharedPreferences.getString("pColumn", "") + "-"
					+ sharedPreferences.getString("cColumn", ""));
		}
		// ����༭�����Ѵ��ڵļ��£������ݿ�ı��������ȡ��������ʾ��EditText��
		else if (editModel.equals("update")) {
			editNewsTitle.setText(newsContentBean.getTitle());
			tTime.setText(newsContentBean.getTime().substring(0,10));
			author.setText("���ߣ�" + newsContentBean.getAuthor());
			column.setText(newsContentBean.getpColumn() + "-"
					+ newsContentBean.getcColumn());
			String context = newsContentBean.getContent();
			// ����������ʽ������ƥ��·��
			// Pattern p = Pattern.compile("/([^\\.]*)\\.\\w{3}");//
			// /([^\.]*)\.\w{3}
			// Matcher m = p.matcher(context);
			try {
				// if (m.groupCount() > 1) {
				// int startIndex = 0;
				// for (int i = 0; i < m.groupCount(); i++) {
				// // ȡ��·��ǰ������
				// if (m.start() > 0) {
				// editNewsContent.append(context.substring(
				// startIndex, m.start()));
				// }
				// SpannableString ss = new SpannableString(m.group()
				// .toString());
				// // ȡ��·��
				// String path = m.group().toString();
				// // ȡ��·���ĺ�׺
				// String type = path.substring(path.length() - 3,
				// path.length());
				// Bitmap bm = null;
				// Bitmap rbm = null;
				// bm = BitmapFactory.decodeFile(m.group());
				// // ����ͼƬ
				// rbm = ImageBo.resize(bm, 480);
				// // ΪͼƬ��ӱ߿�Ч��
				// rbm = ImageBo.getBitmapHuaSeBianKuang(rbm);
				// ImageSpan span = new ImageSpan(activityContext, rbm);
				// ss.setSpan(span, 0, m.end() - m.start(),
				// Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				// editNewsContent.append(ss);
				// startIndex = m.end();
				//
				// // ��List��¼��¼����λ�ü�����·�������ڵ����¼�
				// Map<String, String> map = new HashMap<String, String>();
				// map.put("location", m.start() + "-" + m.end());
				// map.put("path", path);
				// imgList.add(map);
				// // �����һ��ͼƬ֮������������TextView��
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
