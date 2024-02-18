package com.jung.inheritancejoin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BillingDetails {
	@Getter
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Getter @Setter
	private String owner;
}
