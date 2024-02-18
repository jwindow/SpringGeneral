package com.jung.inheritancejoin;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@PrimaryKeyJoinColumn(name = "CREDIT_CARD_ID")
public class CreditCard extends BillingDetails{
	private Integer expYear;
	private Integer expMonth;
}
