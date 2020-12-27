package ru.kozavbede.java.classfile;

import ru.kozavbede.java.constpool.IInfo;
import ru.kozavbede.java.interfaces.Interface;

public class ClassFile {

	private int minorVersion;
	private int majorVersion;

	private IInfo[] constantPool;
	private Interface[] interfaces;

	public ClassFile() {
	}

	public int getMinorVersion() {
		return minorVersion;
	}

	public void setMinorVersion(int minorVersion) {
		this.minorVersion = minorVersion;
	}

	public int getMajorVersion() {
		return majorVersion;
	}

	public void setMajorVersion(int majorVersion) {
		this.majorVersion = majorVersion;
	}

	public IInfo[] getConstantPool() {
		return constantPool;
	}

	public void setConstantPool(IInfo[] infos) {
		this.constantPool = infos;
	}

	public Interface[] getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(Interface[] interfaces) {
		this.interfaces = interfaces;
	}

}
