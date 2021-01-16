package ru.kozavbede.java.attributes.impl;

import ru.kozavbede.java.attributes.BaseAttribute;

public class ConstantAttribute extends BaseAttribute {

	private final int constantValueIndex;

	public ConstantAttribute(BaseAttribute base, int constantValueIndex) {
		super(base.getAttributeNameIndex(), base.getType());
		this.constantValueIndex = constantValueIndex;
	}

	public int getConstantValueIndex() {
		return constantValueIndex;
	}

}
