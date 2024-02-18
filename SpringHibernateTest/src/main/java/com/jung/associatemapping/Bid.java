package com.jung.associatemapping;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
@Entity
public class Bid {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter
	private Long id;
	@Getter @Setter
	private String userName;
	
	@Getter @Setter
	private BigDecimal price;
	
	@Getter @Setter	
	@ManyToOne(fetch=FetchType.LAZY)
	private Item item;

	@Override
	public int hashCode() {
		return Objects.hash(id, price, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bid other = (Bid) obj;
		return Objects.equals(id, other.id) && Objects.equals(price, other.price)
				&& Objects.equals(userName, other.userName);
	}
	
	
	
	
}
