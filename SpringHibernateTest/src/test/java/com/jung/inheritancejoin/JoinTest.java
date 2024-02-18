package com.jung.inheritancejoin;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JoinTestConfig.class)
public class JoinTest {

	@Autowired
	EntityManagerFactory emf;
	
	@Test
	void retrieveTest() {
		insertData();
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<BillingDetails> cq= cb.createQuery(BillingDetails.class);
		int exp=11;
		
		Root<BillingDetails> root=cq.from(BillingDetails.class);
		
//		Join<BillingDetails,CreditCard> join=root.join("");
		
//		cb.treat(join);
		
		
		cq.select(root);//.where(cb.equal(root.get("expMonth"), exp));// root.get(""));
		TypedQuery<BillingDetails> tq=em.createQuery(cq);
		
		
		List<BillingDetails> resultList=tq.getResultList();
		
		System.out.println("resultList.size:"+resultList.size());
		
		em.getTransaction().commit();
		em.close();
	}
//	@Test
	void retrieveCreditCardTest() {
		insertData();
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<CreditCard> cq= cb.createQuery(CreditCard.class);
		
		Root<CreditCard> root=cq.from(CreditCard.class);
		cq.select(root);
		TypedQuery<CreditCard> tq=em.createQuery(cq);
		
		
		List<CreditCard> resultList=tq.getResultList();
		
		System.out.println("resultList.size:"+resultList.size());
		
		em.getTransaction().commit();
		em.close();
	}
	
	void insertData() {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		
		CreditCard cc=new CreditCard();
		
		cc.setExpMonth(11);
		cc.setExpYear(2024);
		cc.setOwner("CreditCard Owner");
		em.persist(cc);
		
		em.getTransaction().commit();
		em.close();
		
		
	}
}
