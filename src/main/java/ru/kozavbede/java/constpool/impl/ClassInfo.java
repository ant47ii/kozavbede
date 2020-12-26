package ru.kozavbede.java.constpool.impl;

import ru.kozavbede.java.constpool.BaseInfo;
import ru.kozavbede.java.constpool.Tag;

public final class ClassInfo extends BaseInfo {

	private final int nameIndex; // ссылка на utf8
	private Utf8Info utf8Info;

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

	public Utf8Info getNameInfo() {
		return utf8Info;
	}

}
