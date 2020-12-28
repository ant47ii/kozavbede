package ru.kozavbede.java.methods;

import ru.kozavbede.java.attributes.Attribute;

public class Method {

	private final int nameIndex;
	private final int descriptorIndex;
	private Attribute[] attribures;

	public Method(int nameIndex, int descriptorIndex) {
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
	}

	public int getNameIndex() {
		return nameIndex;
	}

	public int getDescriptorIndex() {
		return descriptorIndex;
	}

	public Attribute[] getAttribures() {
		return attribures;
	}

	public void setAttribures(Attribute[] attribures) {
		this.attribures = attribures;
	}

}
