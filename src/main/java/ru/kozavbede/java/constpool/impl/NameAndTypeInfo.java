package ru.kozavbede.java.constpool.impl;

import ru.kozavbede.java.constpool.BaseInfo;
import ru.kozavbede.java.constpool.Tag;

public class NameAndTypeInfo extends BaseInfo {

	private final int nameIndex; // utf8
	private final int descriptorIndex; // utf8

	public NameAndTypeInfo(int index, int nameIndex, int descriptorIndex) {
		super(index);
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
	}

	@Override
	public Tag getTag() {
		return Tag.NAME_AND_TYPE;
	}
}
