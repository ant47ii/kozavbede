package ru.kozavbede.java.constpool.impl;

import ru.kozavbede.java.constpool.BaseInfo;
import ru.kozavbede.java.constpool.Tag;

public class LongInfo extends BaseInfo {

	private final long value;

	public LongInfo(int index, long value) {
		super(index);
		this.value = value;
	}

	@Override
	public Tag getTag() {
		return Tag.LONG;
	}

	@Override
	public String toString() {
		return Long.toString(value) + "l";
	}

	public long getValue() {
		return value;
	}
}
