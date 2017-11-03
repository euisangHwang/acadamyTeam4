package kr.ac.syu.sieun.dto;

import java.io.Serializable;

//메모리
//1 자생부생
//2 자설부설
//3 생주부주
//4 설공메사
// 다형성 발생원리
// 1 부타자생 
// 2 부타자참 List<Student>
// 3 부메자호 - 오버라이딩 - VMI
public class Members implements Serializable{
	private int idx;
	private String id;
	private String pass;
	private String name;
	private int level;
	public Members() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Members(int idx, String id, String pass, String name, int level) {
		super();
		this.idx = idx;
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.level = level;
	}
	public Members(String id, String pass, String name, int level) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.level = level;
	}
	@Override
	public String toString() {
		return "Members [idx=" + idx + ", id=" + id + ", pass=" + pass + ", name=" + name + ", level=" + level + "]";
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
}
