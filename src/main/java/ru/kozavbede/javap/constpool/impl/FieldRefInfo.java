package ru.kozavbede.javap.constpool.impl;

import ru.kozavbede.javap.constpool.Tag;

public class FieldRefInfo extends BaseRefInfo {

	public FieldRefInfo(int index, int classIndex, int nameAndTypeIndex) {
		super(index, classIndex, nameAndTypeIndex);
	}

	@Override
	public Tag getTag() {
		return Tag.FIELD_REF;
	}
}
