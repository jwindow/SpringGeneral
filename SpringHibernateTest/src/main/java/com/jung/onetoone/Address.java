package com.jung.onetoone;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity

public class Address {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Long id;
	@Getter @Setter
	private String country;
}
