package com.qa.hubspot.util;

public class Credentials {
	
	String appUsername;
	String appPassword;
	
	//Const..
	public Credentials(String appUsername, String appPassword) {
		this.appUsername = appUsername;
		this.appPassword = appPassword;
	}

	//Getters and Setters:
	public String getAppUsername() {
		return appUsername;
	}


	public void setAppUsername(String appUsername) {
		this.appUsername = appUsername;
	}


	public String getAppPassword() {
		return appPassword;
	}


	public void setAppPassword(String appPassword) {
		this.appPassword = appPassword;
	}
	
	
	
	

}
