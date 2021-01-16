package ru.kozavbede.java.classfile;

import ru.kozavbede.java.attributes.BaseAttribute;
import ru.kozavbede.java.constpool.ConstantPool;
import ru.kozavbede.java.fields.Field;
import ru.kozavbede.java.interfaces.Interface;
import ru.kozavbede.java.methods.Method;

/**
 * 
 * Файл класса.
 *
 */
public class ClassFile {

	private final ClassFileVersion version;
	private final ClassFileInfo info;
	private final ConstantPool constantPool;
	private Interface[] interfaces;
	private Field[] fields;
	private Method[] methods;
	private BaseAttribute[] attributes;

	public ClassFile(ClassFileVersion version, ClassFileInfo info, ConstantPool constantPool) {
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

	public ConstantPool getConstantPool() {
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

	public BaseAttribute[] getAttributes() {
		return attributes;
	}

	public void setAttributes(BaseAttribute[] attributes) {
		this.attributes = attributes;
	}

}
