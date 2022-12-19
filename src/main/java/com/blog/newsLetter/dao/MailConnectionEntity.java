package com.blog.newsLetter.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="newslettermailconnections")
public class MailConnectionEntity{

	@Id
	@Column(name="id")
	@SequenceGenerator(name="pkGen",sequenceName="newslettermailconnections_id_seq",allocationSize=1)
	@GeneratedValue(generator="pkGen",strategy=GenerationType.SEQUENCE)
	private int id;
	
	
	
	@Column(name="link")
	private String link;

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	
	
}
