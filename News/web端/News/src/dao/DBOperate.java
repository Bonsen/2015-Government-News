package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.Column;
import bean.News;
import bean.Search;
import bean.User;

/**
 * @author ZYY �ṩ�����ݿ�ĸ��ֲ�����ֻ�ṩԭʼ���ݼ������ṩ���ݵķ�װ
 */
public class DBOperate {

	static ConnectionDao db = new ConnectionDao();

	// �����û���������û�����
	public static User GetUserByName(String name) throws SQLException {
		User user = null;
		Connection conn = db.getConnection();
		Statement s = conn.createStatement();
		String sql = "select * from news where name ='" + name + "'";
		ResultSet rs = s.executeQuery(sql);
		while (rs.next()) {
			user = new User();
			user.setName(rs.getString("name"));
			user.setId(rs.getInt("id"));
			user.setPassword(rs.getString("password"));
			user.setStatus(rs.getInt("status"));
		}
		rs.close();
		s.close();
		conn.close();
		return user;
	}

	// ��������б�
	public static List<News> GetNewsByAuthorAndColumn(String name,
			String pColumn, String cColumn) throws SQLException {
		List<News> list = new ArrayList<News>();
		Connection conn = db.getConnection();
		Statement s = conn.createStatement();
		String sql = "select * from newsdetail where author ='" + name
				+ "' and cColumn ='" + cColumn + "' and pColumn='" + pColumn
				+ "' order by status asc, time desc";
		ResultSet rs = s.executeQuery(sql);
		while (rs.next()) {
			News news = new News();
			news.setAuthor(rs.getString("author"));
			news.setcColumn(rs.getString("cColumn"));
			news.setContent(rs.getString("content"));
			news.setId(rs.getInt("id"));
			news.setpColumn(rs.getString("pColumn"));
			news.setStatus(rs.getInt("status"));
			news.setTime(rs.getString("time"));
			news.setTitle(rs.getString("title"));
			list.add(news);
		}
		rs.close();
		s.close();
		conn.close();
		return list;
	}
	
	// ����Ա��������б�
		public static List<News> GetNews(
				String pColumn, String cColumn) throws SQLException {
			List<News> list = new ArrayList<News>();
			Connection conn = db.getConnection();
			Statement s = conn.createStatement();
			String sql = "select * from newsdetail where  cColumn ='" + cColumn + "' and pColumn='" + pColumn
					+ "' order by status asc, time desc";
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				News news = new News();
				news.setAuthor(rs.getString("author"));
				news.setcColumn(rs.getString("cColumn"));
				news.setContent(rs.getString("content"));
				news.setId(rs.getInt("id"));
				news.setpColumn(rs.getString("pColumn"));
				news.setStatus(rs.getInt("status"));
				news.setTime(rs.getString("time"));
				news.setTitle(rs.getString("title"));
				list.add(news);
			}
			rs.close();
			s.close();
			conn.close();
			return list;
		}


	// ��������
	public static List<News> SearchNews(Search search) throws SQLException {
		List<News> list = new ArrayList<News>();
		String sql = "select * from  newsdetail  where title like '%" + search.getKeyWord()
				+ "%' or content like '%" + search.getKeyWord() + "%' and time <'"
				+ search.getEndTime() + "' and time >'" + search.getStartTime() + "' and author='"
				+ search.getName() + "'";
		Connection conn = db.getConnection();
		Statement s = conn.createStatement();
		ResultSet rs = s.executeQuery(sql);
		while (rs.next()) {
			News news = new News();
			news.setAuthor(rs.getString("author"));
			news.setcColumn(rs.getString("cColumn"));
			news.setContent(rs.getString("content"));
			news.setId(rs.getInt("id"));
			news.setpColumn(rs.getString("pColumn"));
			news.setStatus(rs.getInt("status"));
			news.setTime(rs.getString("time"));
			news.setTitle(rs.getString("title"));
			list.add(news);
		}
		rs.close();
		s.close();
		conn.close();
		return list;
	}

	// ���ͻ��˴�����������ӵ���������ݿ���
	public static int AddNews(News news) throws SQLException {
		Connection conn = db.getConnection();
		Statement s = conn.createStatement();
		String sql = "insert into  newsdetail (title,author,cColumn,content,pColumn,status,time) values ('"
				+ news.getTitle()
				+ "','"
				+ news.getAuthor()
				+ "','"
				+ news.getcColumn()
				+ "','"
				+ news.getContent()
				+ "','"
				+ news.getpColumn()
				+ "','"
				+ news.getStatus()
				+ "','"
				+ news.getTime() + "')";
		int flag = s.executeUpdate(sql);
		s.close();
		conn.close();
		return flag;
	}

	// ��������
	public static int UpdateNews(News news) throws SQLException {
		Connection conn = db.getConnection();
		Statement s = conn.createStatement();
		String sql = "update newsdetail set title ='" + news.getTitle()
				+ "' , content ='" + news.getContent() + "' where id='"
				+ news.getId() + "'";
		int flag = s.executeUpdate(sql);
		s.close();
		conn.close();
		return flag;
	}

	// ɾ������
	public static int DeleteNews(int id) throws SQLException {
		Connection conn = db.getConnection();
		Statement s = conn.createStatement();
		String sql = "delete from newsdetail where id ='" + id + "'";
		int flag = s.executeUpdate(sql);
		s.close();
		conn.close();
		return flag;
	}

	// �����Ŀ
	public static List<Column> GetColumn() throws SQLException {
		List<Column> list = new ArrayList<Column>();
		Connection conn = db.getConnection();
		Statement s = conn.createStatement();
		String sql = "select * from news_column";
		ResultSet rs = s.executeQuery(sql);
		while (rs.next()) {
			Column column = new Column();
			column.setId(rs.getInt("id"));
			column.setpId(rs.getInt("news_column"));
			column.setLabel(rs.getString("content"));
			list.add(column);
		}
		rs.close();
		s.close();
		conn.close();
		return list;
	}

	// ��֤�û�
	public static User CheckUser(User user) throws SQLException {
		Connection conn = db.getConnection();
		Statement s = conn.createStatement();
		String sql = "select * from user where name ='" + user.getName() + "'";
		ResultSet rs = s.executeQuery(sql);
		User user_check = new User();
		try {
			while (rs.next()) {
				System.out.println("aexrr");
				user_check.setId(rs.getInt("id"));
				user_check.setStatus(rs.getInt("status"));
				user_check.setPassword(rs.getString("password"));
				user_check.setName(rs.getString("name"));
				rs.close();
				s.close();
				conn.close();
			}
		} catch (Exception e) {
			return user_check;
		}
		return user_check;
	}

	//�����Ŀ
	public static List<Column> GetColumnByUser(User user) throws SQLException {
		List<Column> list = new ArrayList<Column>();
		Connection conn = db.getConnection();
		Statement s = conn.createStatement();
		String sql = "(select news_column.id,news_column.content,news_column.news_column from news_column inner join user_column_re  where user_name='"
				+ user.getName()
				+ "' and user_column=content) union all (select * from  news_column where news_column.news_column in(select column_id from user_column_re where user_column_re.user_name='"
				+ user.getName() + "')) ";
		ResultSet rs = s.executeQuery(sql);
		while (rs.next()) {
			Column column = new Column();
			column.setId(rs.getInt("id"));
			column.setpId(rs.getInt("news_column"));
			column.setLabel(rs.getString("content"));
			list.add(column);
		}
		rs.close();
		s.close();
		conn.close();
		return list;
	}

	// �������
	public static int CheckNews(int id) throws SQLException {
		Connection conn = db.getConnection();
		Statement s = conn.createStatement();
		String sql = "update newsdetail set status ='" + 1 + "'  where id='"
				+ id + "'";
		int flag = s.executeUpdate(sql);
		s.close();
		conn.close();
		return flag;
	}
}
