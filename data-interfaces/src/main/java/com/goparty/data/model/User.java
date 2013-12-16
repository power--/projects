package com.goparty.data.model;


import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
	@Id
	private String id;
	
	public String userName;
	
	public String password;
	
	public String nickName;

	public Date  birthdate;

	public String gender;

	public String location;
	
	public String signature;

	public String photo;

	public String weChatId;

	public String qqId;

	public String weiBoId;
	
	public String getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + userName + ", password="
				+ password + "]";
	}

}

