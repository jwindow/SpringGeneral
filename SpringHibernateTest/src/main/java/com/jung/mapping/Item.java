package com.jung.mapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OrderColumn;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OrderBy;
import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Item {
	@Getter
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter @Setter
	private String name;
	public Item() {}	
	public Item(String name) {
		this.name = name;
	}
	
	@ElementCollection
	@CollectionTable(name="IMAGE",joinColumns = @JoinColumn(name="ITEM_ID"))
	@Column(name="FILENAME")
	
	@GenericGenerator(name = "ss",strategy = "sequence")
	@CollectionId(column = @Column(name="IMG_ID"),type = @Type(type="long"),generator = "ss")
	@OrderBy(clause = "FILENAME DESC")
//	@OrderColumn(name = "IMGQ_SEQ")
	private Collection<String> images=new ArrayList<>();
	
	public Collection<String> getImages() {
//		return Collections.unmodifiableList(images);
		return Collections.unmodifiableCollection(images);
	}
	public void addImage(String image) {
		images.add(image);
	}	

}
