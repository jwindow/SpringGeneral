package com.jung.basic;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
public class ItemDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Long id;
	ItemDetail(){}
	@Getter @Setter
	private String detail;
	@ManyToOne(fetch = FetchType.LAZY)
	@Setter
	Item item;
	public ItemDetail(String detail) {
		this.detail = detail;
	}
}
