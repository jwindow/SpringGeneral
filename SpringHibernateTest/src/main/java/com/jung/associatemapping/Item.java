package com.jung.associatemapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Item {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter
	private Long id;
	@Getter @Setter
	private String name;
	
	@OneToMany(mappedBy="item", cascade= {CascadeType.PERSIST,CascadeType.REMOVE})
	
//	private Set<Bid> bids=new HashSet<>();
//	public Set<Bid> getBids(){
//		return Collections.unmodifiableSet(bids);
//	}
	
	private Collection<Bid> bids=new ArrayList<>();
	public Collection<Bid> getBids(){
		return Collections.unmodifiableCollection(bids);
	}
	
	public void addBid(Bid bid) {
		bids.add(bid);
		bid.setItem(this);
	}
	public void removeBid(Bid bid) {
		bids.remove(bid);
		bid.setItem(null);
	}
}
