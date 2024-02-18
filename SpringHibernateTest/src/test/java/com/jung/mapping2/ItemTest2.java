package com.jung.mapping2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=ItemConfig.class)
//@DataJpaTest
//@SpringBootTest
public class ItemTest2 {
	@Autowired
	@Qualifier("sessionFactory1")
	EntityManagerFactory emf;
	@Autowired
	SessionFactory sessionFactory1;
	@Autowired
	SessionFactory sessionFactory2;
		
	@Test
	void retieveTest() {
		System.out.println("sessionFactory2:"+sessionFactory1);
		System.out.println("sessionFactory2:"+sessionFactory2);
		Session session=sessionFactory1.openSession();
		
		
		
		
		/*
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<Item> cq=cb.createQuery(Item.class);
		
		Root<Item> root=cq.from(Item.class);
		root.fetch("images");
		cq.select(root);
		
		List<Item> resultList= em.createQuery(cq.distinct(true)).getResultList();
		resultList.forEach(item->{
			System.out.println("Item Name:"+item.getName());
			
			Collection<Image> images=item.getImages();
			System.out.println("Image Size:"+images.size());
			
			images.forEach(img->{
				System.out.println("FilName Name:"+img.getFileName());
			});
			
		});
		assertEquals(3,resultList.size());
		
		
		em.getTransaction().commit();
		em.close(); 
		*/
	}
//	@BeforeEach
	void insertData() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		for (int i = 0; i < 3; i++) {
			Item item = new Item("Item_" + i);
			for (int j = 0; j < 3; j++) {
				Image img=new Image("image_"+j,(i*100)+(j+1),(i+1)*(j+1)+1);
				item.addImage(img);
			}
			em.persist(item);
		}

		em.getTransaction().commit();
		em.close(); 
	}
}
