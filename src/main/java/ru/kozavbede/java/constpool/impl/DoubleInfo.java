package ru.kozavbede.java.constpool.impl;

import ru.kozavbede.java.constpool.BaseInfo;
import ru.kozavbede.java.constpool.Tag;

public class DoubleInfo extends BaseInfo {

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
