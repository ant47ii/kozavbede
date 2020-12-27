package ru.kozavbede.java.classfile;

import ru.kozavbede.java.constpool.IInfo;
import ru.kozavbede.java.interfaces.Interface;

public class ClassFile {

	private final int minorVersion;
	private final int majorVersion;

	private IInfo[] constantPool;
	private Interface[] interfaces;

	public ClassFile(int minorVersion, int majorVersion) {
		this.minorVersion = minorVersion;
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
