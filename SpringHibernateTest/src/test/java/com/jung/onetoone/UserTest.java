package com.jung.onetoone;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserConfig.class)
public class UserTest {
	
	@Autowired
	EntityManagerFactory emf;
	@Test
	void retieveTest() {
		
		EntityManager em= emf.createEntityManager();
		em.getTransaction().begin();
		
		CriteriaBuilder cb=em.getCriteriaBuilder();
		
		CriteriaQuery<User> cq=cb.createQuery(User.class);
		
		Root<User> root=cq.from(User.class);
		//root.fetch(User_.);
		
		cq.select(root);
		
		List<User> users=em.createQuery(cq).getResultList();
		
		System.out.println("User Size:"+users.size());
		users.forEach(u->{
			System.out.println("Country:"+u.getAddress().getCountry());
		});
		
		
		
		
		
		
		
		em.getTransaction().commit();
		em.close();
	}

	@BeforeEach
	void insertData(){
		EntityManager em= emf.createEntityManager();
		em.getTransaction().begin();
		
		User u=new User();
		u.setName("User Name");
		
		Address addr=new Address();
		addr.setCountry("USA");
		
		u.setAddress(addr);
		
		em.persist(u);
		
		em.getTransaction().commit();
		em.close();
	}
}
