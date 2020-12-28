package ru.kozavbede.java.fields;

import ru.kozavbede.java.fields.attributes.Attribute;

public class Field {

	private final int nameIndex;
	private final int descriptorIndex;
	private Attribute[] attribures;

	public Field(int nameIndex, int descriptorIndex) {
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
	}

	public int getNameIndex() {
		return nameIndex;
	}

	public int getDescriptorIndex() {
		return descriptorIndex;
	}

	public Attribute[] getAttribures() {
		return attribures;
	}

	public void setAttribures(Attribute[] attribures) {
		this.attribures = attribures;
	}

}
