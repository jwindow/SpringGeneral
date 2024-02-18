package com.jung.basic;

public enum ItemType {
	BEST("BEST1",1),
	MIDDLE("MIDDLE1",2),
	LOW("LOW1",3);
	
	String desc;
	Integer num;
	ItemType(String desc,Integer num) {
		this.desc=desc;
		this.num=num;
	}
	public static ItemType getType(Integer i) {
		for(ItemType type:values()) {
			if(type.num.equals(i)){
				return type;
			}
		}
		throw new IllegalArgumentException("Cannot find Item type for "+i);
	}
	
}
