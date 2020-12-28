package ru.kozavbede.java;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import ru.kozavbede.java.classfile.ClassFile;
import ru.kozavbede.java.classfile.ClassFileReader;
import ru.kozavbede.java.constpool.IConstantPoolRow;
import ru.kozavbede.java.constpool.impl.BaseRefInfo;
import ru.kozavbede.java.constpool.impl.ClassInfo;
import ru.kozavbede.java.constpool.impl.NameAndTypeInfo;
import ru.kozavbede.java.constpool.impl.StringInfo;
import ru.kozavbede.java.constpool.impl.Utf8Info;
import ru.kozavbede.java.fields.Field;
import ru.kozavbede.java.interfaces.Interface;
import ru.kozavbede.java.methods.Method;

public class App {

	public static void main(String[] args) throws IOException {
		try (InputStream is = new FileInputStream("C:\\java\\Bl.class")) {
			ClassFileReader classFileReader = new ClassFileReader(is);
			ClassFile classFile = classFileReader.read();

			printConstantPool(classFile.getConstantPool());
			printInterfaces(classFile.getConstantPool(), classFile.getInterfaces());
			printFileds(classFile.getConstantPool(), classFile.getFields());
			printMethods(classFile.getConstantPool(), classFile.getMethods());
		}
	}

	private static void printMethods(IConstantPoolRow[] constPool, Method[] methods) {
		System.out.println("Methods:");
		for (Method method : methods) {
			Utf8Info name = getUtf8Info(constPool, method.getNameIndex());
			Utf8Info descriptor = getUtf8Info(constPool, method.getDescriptorIndex());
			System.out.println(String.format(" %s %s", name, descriptor));
		}
	}

	private static void printFileds(IConstantPoolRow[] constPool, Field[] fields) {
		System.out.println("Fields:");
		for (Field field : fields) {
			Utf8Info name = getUtf8Info(constPool, field.getNameIndex());
			Utf8Info descriptor = getUtf8Info(constPool, field.getDescriptorIndex());
			System.out.println(String.format(" %s %s", name, descriptor));
		}
	}

	private static void printInterfaces(IConstantPoolRow[] constPool, Interface[] interfaces) {
		System.out.println("Interfaces:");
		for (Interface inter : interfaces) {
			ClassInfo classInfo = getClassInfoInfo(constPool, inter.getClassIndex());
			String displayValue = getDisplayValue(constPool, classInfo);
			System.out.println(String.format(" %s", displayValue));
		}
	}

	private static void printConstantPool(IConstantPoolRow[] infos) {
		System.out.println("Constant pool:");
		for (IConstantPoolRow info : infos) {
			if (info == null) {
				continue;
			}
			int index = info.getIndex();
			String tagName = info.getTag().getDisplayName();
			String value = info.toString();
			String displayValue = getDisplayValue(infos, info);
			System.out.println(String.format(" #%-3s = %-15s %-20s %s", index, tagName, value, displayValue));
		}
	}

	private static String getDisplayValue(IConstantPoolRow[] infos, IConstantPoolRow info) {
		switch (info.getTag()) {
		case STRING:
			StringInfo stringInfo = (StringInfo) info;
			Utf8Info stringInfoName = getUtf8Info(infos, stringInfo.getNameIndex());
			return stringInfoName.getValue();
		case NAME_AND_TYPE:
			NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo) info;
			Utf8Info typeName = getUtf8Info(infos, nameAndTypeInfo.getNameIndex());
			Utf8Info descName = getUtf8Info(infos, nameAndTypeInfo.getDescriptorIndex());
			return typeName.getValue() + ":" + descName.getValue();
		case CLASS:
			ClassInfo classInfo = (ClassInfo) info;
			Utf8Info className = getUtf8Info(infos, classInfo.getNameIndex());
			return className.getValue();
		case FIELD_REF:
		case INTERFACE_METHOD_REF:
		case METHOD_REF:
			BaseRefInfo refInfo = (BaseRefInfo) info;
			ClassInfo clsInfo = getClassInfoInfo(infos, refInfo.getClassIndex());
			NameAndTypeInfo typeInfo = getNameAndTypeInfo(infos, refInfo.getNameAndTypeIndex());
			return getDisplayValue(infos, clsInfo) + "." + getDisplayValue(infos, typeInfo);
		}

		return "";
	}

	private static Utf8Info getUtf8Info(IConstantPoolRow[] infos, int index) {
		return (Utf8Info) infos[index - 1];
	}

	private static ClassInfo getClassInfoInfo(IConstantPoolRow[] infos, int index) {
		return (ClassInfo) infos[index - 1];
	}

	private static NameAndTypeInfo getNameAndTypeInfo(IConstantPoolRow[] infos, int index) {
		return (NameAndTypeInfo) infos[index - 1];
	}

}
