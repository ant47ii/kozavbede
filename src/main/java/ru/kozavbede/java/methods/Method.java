package ru.kozavbede.java.methods;

import ru.kozavbede.java.attributes.IAttribute;

public class Method {

	private final int accessFlags;
	private final int nameIndex;
	private final int descriptorIndex;
	private IAttribute[] attribures;

	public Method(int accessFlags, int nameIndex, int descriptorIndex) {
		this.accessFlags = accessFlags;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
	}

	public int getAccessFlags() {
		return accessFlags;
	}

	public int getNameIndex() {
		return nameIndex;
	}

	public int getDescriptorIndex() {
		return descriptorIndex;
	}

	public IAttribute[] getAttribures() {
		return attribures;
	}

	public void setAttribures(IAttribute[] attribures) {
		this.attribures = attribures;
	}

}
