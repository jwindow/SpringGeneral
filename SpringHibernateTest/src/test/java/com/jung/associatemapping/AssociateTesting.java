package com.jung.associatemapping;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=AssociateTestingConfig.class)
public class AssociateTesting {
	@Autowired
	EntityManagerFactory emf;
	@Autowired
	ItemRepository itemRepo;
//	@Test
	void deleteByRepo(){
		itemRepo.deleteById(1L);
	}

	@Test
	void retrieveTest() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<Bid> cq=cb.createQuery(Bid.class);
		
		Root<Bid> root = cq.from(Bid.class);
		
		
		cq.select(root);
		
		TypedQuery<Bid> q= em.createQuery(cq);
		List<Bid> list=q.getResultList();
		
		System.out.println("list Size:"+list.size());
		list.forEach(bid->{
			System.out.println("Item:"+bid.getItem());
		});
		em.getTransaction().commit();
		em.close();
		
	}
//	@Test
	void deleteTest() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Item item=em.find(Item.class, 1L);
//		for (Iterator<Bid> it = item.getBids().iterator(); it.hasNext(); ) {
//		    Bid aDrugStrength = it.next();
//		    it.remove();
////		    if (!aDrugStrength.isValidDrugDescription()) {
////		        it.remove();
////		    }
//		}
		
//		item.getBids().forEach(bid->{
//			item.removeBid(bid);
//		});
//		em.remove(item);
		
		Long id=1L;
		
		CriteriaBuilder cb=em.getCriteriaBuilder();
		
		CriteriaDelete<Bid> cdBid=cb.createCriteriaDelete(Bid.class);
		
		Root<Bid> delBid = cdBid.from(Bid.class);
		
		cdBid.where(cb.equal(delBid.get("item"), item));
		em.createQuery(cdBid).executeUpdate();
		
		
		
		CriteriaDelete<Item> cd=cb.createCriteriaDelete(Item.class);
		
		Root<Item> delRoot = cd.from(Item.class);
		
		cd.where(cb.equal(delRoot.get("id"), id));
		em.createQuery(cd).executeUpdate();
		
		em.getTransaction().commit();
		em.close();
	}
	@BeforeEach
	void insertData() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		for (int i = 0; i < 3; i++) {
			Item item = new Item();
			item.setName("Item_name_"+i);
			
			for(int j=0;j<3;j++) {
				Bid bid=new Bid();
				bid.setUserName("bid_UserName_"+j);
				bid.setPrice(new BigDecimal(j));
				item.addBid(bid);
			}
			for(int j=0;j<3;j++) {
				Bid bid=new Bid();
				bid.setUserName("bid_UserName_"+j);
				bid.setPrice(new BigDecimal(j));
				item.addBid(bid);
			}
			
//			bid.setItem(item);
//			em.persist(bid);
			em.persist(item);
		}

		em.getTransaction().commit();
		em.close();
		

		
		
		
	}
}
