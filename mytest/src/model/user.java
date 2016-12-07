package model;

public class user {
	String id;
	String uname;
	String pwd;
	int lv;
	int life;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getLv() {
		return lv;
	}

	public void setLv(int lv) {
		this.lv = lv;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
	
	@Override
	public String toString() {
		String r = "id:" + id + ",uname:" + uname + ",life:" + life + ",lv:" + lv;
		return r;
	}
	
}
