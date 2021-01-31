package ru.kozavbede.javap.constpool.impl;

import ru.kozavbede.javap.constpool.BaseConstantPoolRow;

public abstract class BaseRefInfo extends BaseConstantPoolRow {

	private final int classIndex;
	private final int nameAndTypeIndex;

	protected BaseRefInfo(int index, int classIndex, int nameAndTypeIndex) {
		super(index);
		this.classIndex = classIndex;
		this.nameAndTypeIndex = nameAndTypeIndex;
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
