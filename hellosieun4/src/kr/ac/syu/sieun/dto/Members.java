package kr.ac.syu.sieun.dto;

import java.io.Serializable;


public class Members implements Serializable{

	private String id;
	private String pw;
	private String tel;
	private String name;
	private String address;
	
	//������� ��� (��½ÿ��� ��� / �Է� �ÿ��� ���x)
	private String homeimg;
	
	public String getid() {
		return id;
	}
	public void setid(String id) {
		this.id = id;
	}
	public String gethomeimg() {
		return homeimg;
	}
	public void sethomeimg(String homeimg) {
		this.homeimg = homeimg;
	}
	public String getpw() {
		return pw;
	}
	public void setpw(String pw) {
		this.pw = pw;
	}
	public String gettel() {
		return tel;
	}
	public void settel(String tel) {
		this.tel = tel;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Members [id=" + id + ", pw=" + pw + ", tel=" + tel + ", name=" + name + ", address="
				+ address + "]";
	}
	
}
