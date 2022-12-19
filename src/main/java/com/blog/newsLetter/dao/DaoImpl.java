package com.blog.newsLetter.dao;


import java.util.LinkedList;
import java.util.List;


import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class DaoImpl{
	
	public static int flag=1;

	@PersistenceContext
	EntityManager em;
	
    public List<MailsEntity> getMailAndLinks()
	{
		Query query = em.createQuery("select me from MailsEntity me");
		List<MailsEntity> list = query.getResultList();
		return list;
	}
	
	public void insertData(String email,String links)
	{
		MailsEntity ma = em.find(MailsEntity.class, email);
		if(ma==null)
		{
		MailsEntity mea = new MailsEntity();
		List<MailConnectionEntity> list = new LinkedList<>();
		
		String[] arr = links.split(",");
		for(String s : arr)
		{
		MailConnectionEntity me = new MailConnectionEntity();
		me.setLink(s);
		list.add(me);
		}
		
		mea.setMailId(email);
		mea.setMailConnectionList(list);
		
		em.persist(mea);
		}
		else
		{
			List<MailConnectionEntity> list = ma.getMailConnectionList();
			
			String[] arr = links.split(",");
			for(String s : arr)
			{
				
                MailConnectionEntity me = new MailConnectionEntity();
                me.setLink(s);
                list.add(me);
				
			}
			
		}
	}
	
	
	
	
}
