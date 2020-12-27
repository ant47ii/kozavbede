package ru.kozavbede.java.constpool.impl;

import ru.kozavbede.java.constpool.BaseInfo;
import ru.kozavbede.java.constpool.IRef;
import ru.kozavbede.java.constpool.Tag;

public class MethodrefInfo extends BaseInfo implements IRef {

	private final int classIndex;
	private final int nameAndTypeIndex;

	public MethodrefInfo(int index, int classIndex, int nameAndTypeIndex) {
		super(index);
		this.classIndex = classIndex;
		this.nameAndTypeIndex = nameAndTypeIndex;
	}

	@Override
	public Tag getTag() {
		return Tag.METHOD_REF;
	}

	@Override
	public String toString() {
		return "#" + classIndex + ".#" + nameAndTypeIndex;
	}

	public int getClassIndex() {
		return classIndex;
	}

	public int getNameAndTypeIndex() {
		return nameAndTypeIndex;
	}
}
