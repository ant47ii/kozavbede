package ru.kozavbede.java.constpool.impl;

import ru.kozavbede.java.constpool.Tag;

public class InterfaceMethodRefInfo extends BaseRefInfo {

	public InterfaceMethodRefInfo(int index, int classIndex, int nameAndTypeIndex) {
		super(index, classIndex, nameAndTypeIndex);
	}

	@Override
	public Tag getTag() {
		return Tag.INTERFACE_METHOD_REF;
	}
}
