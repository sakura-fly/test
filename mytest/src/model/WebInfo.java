package model;

public class WebInfo {
	String url;
	String realip;
	String fip;
	String pattam;
	String os;
	String browser;
	String time;
	String type;
	String uri;
	String pathInfo;

	public String getPathInfo() {
		return pathInfo;
	}

	public void setPathInfo(String pathInfo) {
		this.pathInfo = pathInfo;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRealip() {
		return realip;
	}

	public void setRealip(String realip) {
		this.realip = realip;
	}

	public String getFip() {
		return fip;
	}

	public void setFip(String fip) {
		this.fip = fip;
	}

	public String getPattam() {
		return pattam;
	}

	public void setPattam(String pattam) {
		this.pattam = pattam;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}
	
	@Override
	public String toString() {
		String res = "url:" + url + ",真实IP:" + realip + ",代理IP:" + fip + ",参数:" + pattam + ",操作系统:" + os + ",浏览器:" + browser + ",访问时间:" + time + ",访问类型:" + type + ",路径信息:" + pathInfo ;
		return res;
	}
	
}