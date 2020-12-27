package ru.kozavbede.java.constpool.impl;

import ru.kozavbede.java.constpool.BaseInfo;
import ru.kozavbede.java.constpool.Tag;

public class ClassInfo extends BaseInfo {

	private final int nameIndex;

	public ClassInfo(int index, int nameIndex) {
		super(index);
		this.nameIndex = nameIndex;
	}

	@Override
	public Tag getTag() {
		return Tag.CLASS;
	}

	public int getNameIndex() {
		return nameIndex;
	}

	@Override
	public String toString() {
		return "#" + nameIndex;
	}

}
