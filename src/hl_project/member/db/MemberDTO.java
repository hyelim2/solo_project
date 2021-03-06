package hl_project.member.db;

import java.sql.Time;

public class MemberDTO {
	private String id = null;
	private String pw = null;
	private String name = null;
	private String phone = null;
	private String email = null;
	private String address = null;
	private Time reg_date = null;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Time getReg_date() {
		return reg_date;
	}

	public void setReg_date(Time reg_date) {
		this.reg_date = reg_date;
	}

	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", pw=" + pw + ", name=" + name + ", phone=" + phone + ", email=" + email
				+ ", address=" + address + ", reg_date=" + reg_date + "]";
	}

}
