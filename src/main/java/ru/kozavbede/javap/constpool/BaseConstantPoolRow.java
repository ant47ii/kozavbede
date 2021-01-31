package ru.kozavbede.javap.constpool;

public abstract class BaseConstantPoolRow implements IConstantPoolRow {

	protected int index;

	protected BaseConstantPoolRow(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}
}
