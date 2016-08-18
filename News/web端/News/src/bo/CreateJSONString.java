package bo;

import java.util.List;

import net.sf.json.JSONObject;
import bean.Column;
import bean.News;
import bean.User;

public class CreateJSONString {
	public static String createJsonString(String key, Object value) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(key, value);
		return jsonObject.toString();
	}
	
	public static String CreateTreeJsonString(List<Column> cList,List<News> nList,User user)
	{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("column", cList);
		jsonObject.put("news", nList);
		jsonObject.put("user", user);
		return jsonObject.toString();
	}
}
