package ru.kozavbede.java.constpool.impl;

import ru.kozavbede.java.constpool.BaseInfo;
import ru.kozavbede.java.constpool.Tag;

public class Utf8Info extends BaseInfo {

	private final String value;

	public Utf8Info(int index, String value) {
		super(index);
		this.value = value;
	}

	@Override
	public Tag getTag() {
		return Tag.UTF8;
	}

}
