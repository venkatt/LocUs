package com.example.locus.entity;

public class User {
	public static String UnknownName = "Unknown";
	
	private String name;
	private Sex sex;
	private String ip;
	
	public User() {
		this(UnknownName, Sex.Unknown, null);
	}
	
	public User(String name, Sex sex, String ip){
		this.name = name;
		this.sex = sex;
		this.ip = ip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", sex=" + sex + ", ip=" + ip + "]";
	}
	
}
