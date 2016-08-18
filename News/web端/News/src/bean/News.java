package bean;

public class News {

	private String title;
	private String content;
	private int id;
	private int status;// 新闻状态[审核、未审核、驳回]
	private String time;// 新闻发布时间
	private String author;
	private String pColumn;// 父栏目
	private String cColumn;// 子栏目

	public News() {

	}
	public News(String title,String content,String time,String author,String pColumn,String cColumn)
	{
		
		this.title=title;
		this.content=content;
		this.time=time;
		this.author=author;
		this.pColumn=pColumn;
		this.cColumn=cColumn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAuthor() {
		return author;
	}
	
	
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getpColumn() {
		return pColumn;
	}

	public void setpColumn(String pColumn) {
		this.pColumn = pColumn;
	}

	public String getcColumn() {
		return cColumn;
	}

	public void setcColumn(String cColumn) {
		this.cColumn = cColumn;
	}
	
	@Override
	public  String toString()
	{
		return "author : "+this.author +" title : " +this.title +" time : " +this.time +" pColumn : " +this.pColumn +" cColumn : " +this.cColumn+" status : " +this.status;
	}
}
