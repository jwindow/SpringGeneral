package com.jung.basic;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
@Entity
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Long id;
	
	Item(){}
	@Getter @Setter
	private String title;
	
	@CreationTimestamp
	private LocalDate createdOn;
	
	@UpdateTimestamp
	@Getter
	private Instant lastUpdated;
	@Getter @Setter
	@Convert(converter =ItemConverter.class)
	private ItemType itemType;

	public Item(String title) {
		this.title=title;
	}
	@OneToMany(mappedBy = "item",fetch =  FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
	Set<ItemDetail> details =new HashSet<>();
	
	void addDetail(ItemDetail detail) {
		details.add(detail);
		detail.setItem(this);
	}
}
