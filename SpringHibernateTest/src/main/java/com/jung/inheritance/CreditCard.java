package com.jung.inheritance;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@AttributeOverride( name="owner",column = @Column(name="cc_owner",nullable = false))
public class CreditCard extends BillingDetails{	
	private Integer expYear;
	private Integer expMonth;
}
