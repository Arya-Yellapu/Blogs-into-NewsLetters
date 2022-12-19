package com.blog.newsLetter.dao;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="newslettermails")
public class MailsEntity {

	@Id
	@Column(name="email_id")
	private String mailId;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="email_id")
	private List<MailConnectionEntity> mailConnectionList;

	public List<MailConnectionEntity> getMailConnectionList() {
		return mailConnectionList;
	}

	public void setMailConnectionList(List<MailConnectionEntity> mailConnectionList) {
		this.mailConnectionList = mailConnectionList;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	
	
	
	
}
