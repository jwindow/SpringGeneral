package com.jung.inheritance;

import java.util.List;

public interface BankAccountRepo extends BillingDetailsRepo<BankAccount>{
	List<BankAccount> findByBankName(String bankName);
}
