package com.jung.mapping2;

import java.util.Objects;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class Image {
	private String fileName;
	private int width;
	private int height;
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Image other = (Image) obj;
		return Objects.equals(fileName, other.fileName) && height == other.height && width == other.width;
	}
	@Override
	public int hashCode() {
		return Objects.hash(fileName, height, width);
	}
	
}
