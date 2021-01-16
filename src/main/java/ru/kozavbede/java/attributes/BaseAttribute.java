package ru.kozavbede.java.attributes;

public class BaseAttribute {

	private final int attributeNameIndex;
	private final AttributeType type;

	protected BaseAttribute(int attributeNameIndex, AttributeType type) {
		this.attributeNameIndex = attributeNameIndex;
		this.type = type;
	}

	public int getAttributeNameIndex() {
		return attributeNameIndex;
	}

	public AttributeType getType() {
		return type;
	}

}
