package ru.kozavbede.java.classfile;

import ru.kozavbede.java.constpool.IConstantPoolRow;
import ru.kozavbede.java.fields.Field;
import ru.kozavbede.java.interfaces.Interface;
import ru.kozavbede.java.methods.Method;

public class ClassFile {

	private int minorVersion;
	private int majorVersion;

	private IConstantPoolRow[] constantPool;
	private Interface[] interfaces;
	private Field[] fields;
	private Method[] methods;

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

	public IConstantPoolRow[] getConstantPool() {
		return constantPool;
	}

	public void setConstantPool(IConstantPoolRow[] infos) {
		this.constantPool = infos;
	}

	public Interface[] getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(Interface[] interfaces) {
		this.interfaces = interfaces;
	}

	public Field[] getFields() {
		return fields;
	}

	public void setFields(Field[] fields) {
		this.fields = fields;
	}

	public Method[] getMethods() {
		return methods;
	}

	public void setMethods(Method[] methods) {
		this.methods = methods;
	}

}
