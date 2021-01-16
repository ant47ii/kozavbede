package ru.kozavbede.java.attributes;

public class BaseAttribute implements IAttribute {

	private final int attributeNameIndex;
	private final AttributeType type;

	protected BaseAttribute(int attributeNameIndex, AttributeType type) {
		this.attributeNameIndex = attributeNameIndex;
		this.type = type;
	}

	@Override
	public int getAttributeNameIndex() {
		return attributeNameIndex;
	}

	@Override
	public AttributeType getType() {
		return type;
	}

}
