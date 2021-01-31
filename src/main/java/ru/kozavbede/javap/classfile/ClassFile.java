package ru.kozavbede.javap.classfile;

import ru.kozavbede.javap.attributes.IAttribute;
import ru.kozavbede.javap.constpool.ConstantPool;
import ru.kozavbede.javap.fields.Field;
import ru.kozavbede.javap.interfaces.Interface;
import ru.kozavbede.javap.methods.Method;

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
	private IAttribute[] attributes;

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

	public IAttribute[] getAttributes() {
		return attributes;
	}

	public void setAttributes(IAttribute[] attributes) {
		this.attributes = attributes;
	}

}
