package com.jung.basic;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

//@Converter(autoApply=true)
public class ItemConverter implements AttributeConverter<ItemType,Integer>{

	@Override
	public Integer convertToDatabaseColumn(ItemType attribute) {
		return attribute.num;
	}

	@Override
	public ItemType convertToEntityAttribute(Integer dbData) {
		return ItemType.getType(dbData);
	}
}
