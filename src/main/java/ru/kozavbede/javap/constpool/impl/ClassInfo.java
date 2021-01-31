package ru.kozavbede.javap.constpool.impl;

import ru.kozavbede.javap.constpool.BaseConstantPoolRow;
import ru.kozavbede.javap.constpool.Tag;

public class ClassInfo extends BaseConstantPoolRow {

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
