package ru.kozavbede.javap.constpool.impl;

import ru.kozavbede.javap.constpool.BaseConstantPoolRow;
import ru.kozavbede.javap.constpool.Tag;

public class DoubleInfo extends BaseConstantPoolRow {

	private final double value;

	public DoubleInfo(int index, double value) {
		super(index);
		this.value = value;
	}

	@Override
	public Tag getTag() {
		return Tag.DOUBLE;
	}

	@Override
	public String toString() {
		return Double.toString(value) + "d";
	}

	public double getValue() {
		return value;
	}
}
