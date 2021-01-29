package kr.or.ddit.ioc;

public class DbConfig {
	
	//중프에선 이거 프로퍼티스안에다가 넣음
	private String url;
	private String driverClassName;
	private String userName;
	private String password;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDriverClassName() {
		return driverClassName;
	}
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "DbConfig [url=" + url + ", driverClassName=" + driverClassName + ", userName=" + userName
				+ ", password=" + password + "]";
	}
	
	
	
}
