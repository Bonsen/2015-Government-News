package bean;

public class User {

	private int id;
	private String name;
	private String password;
	private int status;// 身份验证

	public User() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	public String toString()
	{
		
		return "name is : "+this.name+" ; "+"id is : "+this.id+" ; "+"status is : "+this.status;
	}

}
