package com.jung.basic;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BasicConfig.class)

public class BasicTesting {
	private static final Logger LOGGER = LoggerFactory.getLogger(BasicTesting.class);
	
	@Autowired
	EntityManagerFactory emf;
	
	

	@Test
	void retrieveTest() throws InterruptedException {
		insertData();
		EntityManager em=emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Item item= em.find(Item.class, 1L);
		LOGGER.debug("======= time : "+item.getLastUpdated());
		ItemDetail detail=new ItemDetail("detail-3");
		item.addDetail(detail);
//		item.setTitle("Title -2");
		
		Thread.sleep(5000);
		em.getTransaction().commit();
		em.close();
		LOGGER.debug("======= time2 : "+item.getLastUpdated());
		
	}

	void insertData() {
		LOGGER.debug("======= insering data ========");
		EntityManager em=emf.createEntityManager();
		
		em.getTransaction().begin();
		Item item=new Item("title-0");
		item.setItemType(ItemType.MIDDLE);
		ItemDetail detail=new ItemDetail("detail-1");
		ItemDetail detail2=new ItemDetail("detail-2");
		item.addDetail(detail);
		item.addDetail(detail2);
		em.persist(item);

		
		em.getTransaction().commit();
		em.close();
		LOGGER.debug("======= insering data ends========");
	}

}
