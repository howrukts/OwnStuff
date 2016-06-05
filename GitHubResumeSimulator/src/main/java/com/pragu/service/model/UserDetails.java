package com.pragu.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetails {

	private String name;
	private String login;
	private String email;
	private int followers;
	private int public_repos;
	private boolean hireable;
	private String blog;
	private String company;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getFollowers() {
		return followers;
	}

	public void setFollowers(int followers) {
		this.followers = followers;
	}

	public int getPublic_repos() {
		return public_repos;
	}

	public void setPublic_repos(int public_repos) {
		this.public_repos = public_repos;
	}

	public boolean isHireable() {
		return hireable;
	}

	public void setHireable(boolean hireable) {
		this.hireable = hireable;
	}

	public String getBlog() {
		return blog;
	}

	public void setBlog(String blog) {
		this.blog = blog;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
}
