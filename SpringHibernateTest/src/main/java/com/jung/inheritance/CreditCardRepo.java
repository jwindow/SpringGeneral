package com.jung.inheritance;

import java.util.List;

public interface CreditCardRepo extends BillingDetailsRepo<CreditCard>{
	List<CreditCard> findByExpYear(Integer year);
}
