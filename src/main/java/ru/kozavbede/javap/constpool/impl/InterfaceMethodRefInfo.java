package ru.kozavbede.javap.constpool.impl;

import ru.kozavbede.javap.constpool.Tag;

public class InterfaceMethodRefInfo extends BaseRefInfo {

	public InterfaceMethodRefInfo(int index, int classIndex, int nameAndTypeIndex) {
		super(index, classIndex, nameAndTypeIndex);
	}

	@Override
	public Tag getTag() {
		return Tag.INTERFACE_METHOD_REF;
	}
}
