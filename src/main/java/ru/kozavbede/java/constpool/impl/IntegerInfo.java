package ru.kozavbede.java.constpool.impl;

import ru.kozavbede.java.constpool.BaseConstantPoolRow;
import ru.kozavbede.java.constpool.Tag;

public class IntegerInfo extends BaseConstantPoolRow {

	private final int value;

	public IntegerInfo(int index, int value) {
		super(index);
		this.value = value;
	}

	@Override
	public Tag getTag() {
		return Tag.INTEGER;
	}

	@Override
	public String toString() {
		return Integer.toString(value);
	}

	public int getValue() {
		return value;
	}

}
