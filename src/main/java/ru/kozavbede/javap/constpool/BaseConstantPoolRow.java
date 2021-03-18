package ru.kozavbede.javap.constpool;

public abstract class BaseConstantPoolRow implements IConstantPoolRow {

	protected int index;

	protected BaseConstantPoolRow(int index) {
		this.index = index;
	}

	@Override
	public int getIndex() {
		return index;
	}
}
