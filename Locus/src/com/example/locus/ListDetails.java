package com.example.locus;

public class ListDetails {
	public String name;
	public int profilePic;
	
	public ListDetails(){
		super();
	}
	public ListDetails(int profilePic, String name){
		this.name = name;
		this.profilePic= profilePic;		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getProfilePc() {
		return profilePic;
	}
	public void setProfilePic(int profilePic) {
		this.profilePic = profilePic;
	}
	
	
}
