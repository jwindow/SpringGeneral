package com.jung.inheritance;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BankInheritanceConfig.class)
public class BillingInhertanceTest {
	@Autowired
	BillingDetailsRepo<BillingDetails> billingDetailsRepo;
	
	@Test
	void retrieveTest() {
		inertData();
		List<BillingDetails> cards=billingDetailsRepo.findAll();
		assertAll(
				()-> assertEquals(1,cards.size()),
				() -> assertEquals("CreditCard Owner",cards.get(0).getOwner())
				);
		
		List<BillingDetails> cards2=billingDetailsRepo.findByOwner("CreditCard Owner");
		assertEquals(1,cards2.size());
		
	}
	
	void inertData() {
		CreditCard cc=new CreditCard();
		cc.setExpMonth(11);
		cc.setExpYear(2024);
		cc.setOwner("CreditCard Owner");
		billingDetailsRepo.save(cc);
	}

}
