package ru.kozavbede.java.constpool.impl;

import ru.kozavbede.java.constpool.BaseInfo;
import ru.kozavbede.java.constpool.Tag;

public class StringInfo extends BaseInfo {

	private final int stringIndex;

	public StringInfo(int index, int stringIndex) {
		super(index);
		this.stringIndex = stringIndex;
	}

	@Override
	public Tag getTag() {
		return Tag.STRING;
	}

}
