package ru.kozavbede.javap.constpool.impl;

import ru.kozavbede.javap.constpool.Tag;

public class MethodRefInfo extends BaseRefInfo {

	public MethodRefInfo(int index, int classIndex, int nameAndTypeIndex) {
		super(index, classIndex, nameAndTypeIndex);
	}

	@Override
	public Tag getTag() {
		return Tag.METHOD_REF;
	}

}
