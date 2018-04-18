package cn.bdqn.entity;

public class Admin {
	private Integer password;
	private String username;
	public Integer getPassword() {
		return password;
	}
	public void setPassword(Integer password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String toString() {
		return "Admin [password=" + password + ", username=" + username + "]";
	}
}
