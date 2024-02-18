package com.jung.mapping;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ItemConfig.class)
public class ItemTest {
	@Autowired
	EntityManagerFactory emf;

	@Test
	void retrieveTest() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<Item> cq=cb.createQuery(Item.class);
		
		Root<Item> root = cq.from(Item.class);
		root.fetch("images");
		
		cq.select(root).distinct(true);
		
		TypedQuery<Item> q= em.createQuery(cq);
		List<Item> list=q.getResultList();
		
		assertEquals(3,list.size());
		
		
		list.forEach(item->{
			assertEquals(3,item.getImages().size());
//			System.out.println("image Size:"+item.getImages().size());
		});

		
		em.getTransaction().commit();
		em.close();
	}
//	@Test
	void test2() {
		EntityManager em= emf.createEntityManager();
		em.getTransaction().begin();
		
		CriteriaBuilder cb=em.getCriteriaBuilder();		
		CriteriaQuery<String> cq=cb.createQuery(String.class);
		
		Root<Item> root=cq.from(Item.class);
		
		cq.select(root.get("name"));
		
		Predicate prdit=cb.equal(root.get("name"), "Item_1");//   root.get("name").equals(""));
		
		List<Predicate> preds=new ArrayList<>();
		preds.add(prdit);
		
		
		TypedQuery<String> tq=em.createQuery(cq.where(preds.stream().toArray(Predicate[]::new)));
		List<String> resultList=tq.getResultList();
		
		System.out.println("Result List size:"+resultList.size());
		
		
		em.getTransaction().commit();
		em.close();
	}
//	@Test
	void test1() {
		EntityManager em= emf.createEntityManager();
		em.getTransaction().begin();
		
		CriteriaBuilder cb=em.getCriteriaBuilder();		
		CriteriaQuery<Item> cq=cb.createQuery(Item.class);
		
		Root<Item> root=cq.from(Item.class);
		
		cq.select(root);
		
		Predicate prdit=cb.equal(root.get("name"), "Item_1");//   root.get("name").equals(""));
		
		List<Predicate> preds=new ArrayList<>();
		preds.add(prdit);
		
		
		TypedQuery<Item> tq=em.createQuery(cq.where(preds.stream().toArray(Predicate[]::new)));
		List<Item> resultList=tq.getResultList();
		
		System.out.println("Result List size:"+resultList.size());
		
		
		em.getTransaction().commit();
		em.close();
	}

	@BeforeEach
	void insertData() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		for (int i = 0; i < 3; i++) {
			Item item = new Item("Item_" + i);
			for (int j = 0; j < 3; j++) {
				item.addImage("image_" + j);
			}
			em.persist(item);
		}

		em.getTransaction().commit();
		em.close();
	}

}
