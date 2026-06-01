package com.avisys.empmgmt.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class MailData {
	private long id;
	private String email;
	private Map<String, String> data;
	
	public MailData() {
		super();
	}

	public MailData(long id, String email, Map<String, String> data) {
		super();
		this.id = id;
		this.email = email;
		this.data = data;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}
	
	
}
