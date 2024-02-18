package com.jung.inheritancejoin;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class BankAccount extends BillingDetails{
	private String bankName;
	private String routingNumber;
}
