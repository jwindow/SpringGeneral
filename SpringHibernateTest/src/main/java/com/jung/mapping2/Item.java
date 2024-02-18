package com.jung.mapping2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
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
	@Embedded
	@CollectionTable(name="IMAGES",joinColumns = @JoinColumn(name="ITEM_ID"))
	@GenericGenerator(name = "seq",strategy = "sequence")
	@CollectionId(column = @Column(
			name = "IMAGE_D"),
			type= @Type( type = "long"), 
			generator = "seq")
	private Collection<Image> images=new ArrayList<>();
	public Collection<Image> getImages(){
		return Collections.unmodifiableCollection(images);
	}
	public void addImage(Image image) {
		images.add(image);
	}
	
	
}
