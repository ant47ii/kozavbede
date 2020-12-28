package ru.kozavbede.java.classfile;

import ru.kozavbede.java.attributes.Attribute;
import ru.kozavbede.java.constpool.IConstantPoolRow;
import ru.kozavbede.java.fields.Field;
import ru.kozavbede.java.interfaces.Interface;
import ru.kozavbede.java.methods.Method;

public class ClassFile {

	private final ClassFileVersion version;
	private final ClassFileInfo info;
	// TODO : унести в класс
	private final IConstantPoolRow[] constantPool;
	private Interface[] interfaces;
	private Field[] fields;
	private Method[] methods;
	private Attribute[] attributes;

	public ClassFile(ClassFileVersion version, ClassFileInfo info, IConstantPoolRow[] constantPool) {
		this.version = version;
		this.info = info;
		this.constantPool = constantPool;
	}

	public ClassFileVersion getVersion() {
		return version;
	}

	public ClassFileInfo getInfo() {
		return info;
	}

	public IConstantPoolRow[] getConstantPool() {
		return constantPool;
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

	public Attribute[] getAttributes() {
		return attributes;
	}

	public void setAttributes(Attribute[] attributes) {
		this.attributes = attributes;
	}

}
