package ru.kozavbede.javap.constpool.impl;

import ru.kozavbede.javap.constpool.BaseConstantPoolRow;
import ru.kozavbede.javap.constpool.Tag;

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
