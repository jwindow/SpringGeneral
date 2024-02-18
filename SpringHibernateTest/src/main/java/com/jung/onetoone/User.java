package com.jung.onetoone;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="users")
public class User {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Long id;
	@Getter @Setter
	private String name;
	
	@OneToOne(fetch = FetchType.LAZY,optional=false,cascade=CascadeType.PERSIST)
	@JoinColumn(name = "ADDR_ID")
	@Getter @Setter
	private Address address;
	
}
