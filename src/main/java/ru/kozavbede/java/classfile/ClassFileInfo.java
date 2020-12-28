package ru.kozavbede.java.classfile;

public class ClassFileInfo {

	private final int accessFlags;
	private final int thisClass;
	private final int superClass;

	public ClassFileInfo(int accessFlags, int thisClass, int superClass) {
		this.accessFlags = accessFlags;
		this.thisClass = thisClass;
		this.superClass = superClass;
	}

	public int getAccessFlags() {
		return accessFlags;
	}

	public int getThisClass() {
		return thisClass;
	}

	public int getSuperClass() {
		return superClass;
	}
}
