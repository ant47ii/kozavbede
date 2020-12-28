package ru.kozavbede.java;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import ru.kozavbede.java.classfile.ClassFile;
import ru.kozavbede.java.classfile.ClassFileReader;
import ru.kozavbede.java.constpool.ConstantPool;
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

			ConstantPool constPool = classFile.getConstantPool();
			printConstantPool(constPool);
			printInterfaces(constPool, classFile.getInterfaces());
			printFileds(constPool, classFile.getFields());
			printMethods(constPool, classFile.getMethods());
		}
	}

	private static void printMethods(ConstantPool constPool, Method[] methods) {
		System.out.println("Methods:");
		for (Method method : methods) {
			Utf8Info name = getUtf8Info(constPool, method.getNameIndex());
			Utf8Info descriptor = getUtf8Info(constPool, method.getDescriptorIndex());
			System.out.println(String.format(" %s %s", name, descriptor));
		}
	}

	private static void printFileds(ConstantPool constPool, Field[] fields) {
		System.out.println("Fields:");
		for (Field field : fields) {
			Utf8Info name = getUtf8Info(constPool, field.getNameIndex());
			Utf8Info descriptor = getUtf8Info(constPool, field.getDescriptorIndex());
			System.out.println(String.format(" %s %s", name, descriptor));
		}
	}

	private static void printInterfaces(ConstantPool constPool, Interface[] interfaces) {
		System.out.println("Interfaces:");
		for (Interface inter : interfaces) {
			ClassInfo classInfo = getClassInfoInfo(constPool, inter.getClassIndex());
			String displayValue = getDisplayValue(constPool, classInfo);
			System.out.println(String.format(" %s", displayValue));
		}
	}

	private static void printConstantPool(ConstantPool constPool) {
		System.out.println("Constant pool:");
		for (IConstantPoolRow row : constPool) {
			if (row == null) {
				// long/double
				continue;
			}
			int index = row.getIndex();
			String tagName = row.getTag().getDisplayName();
			String value = row.toString();
			String displayValue = getDisplayValue(constPool, row);
			System.out.println(String.format(" #%-3s = %-15s %-20s %s", index, tagName, value, displayValue));
		}
	}

	private static String getDisplayValue(ConstantPool constPool, IConstantPoolRow info) {
		switch (info.getTag()) {
		case STRING:
			StringInfo stringInfo = (StringInfo) info;
			Utf8Info stringInfoName = getUtf8Info(constPool, stringInfo.getNameIndex());
			return stringInfoName.getValue();
		case NAME_AND_TYPE:
			NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo) info;
			Utf8Info typeName = getUtf8Info(constPool, nameAndTypeInfo.getNameIndex());
			Utf8Info descName = getUtf8Info(constPool, nameAndTypeInfo.getDescriptorIndex());
			return typeName.getValue() + ":" + descName.getValue();
		case CLASS:
			ClassInfo classInfo = (ClassInfo) info;
			Utf8Info className = getUtf8Info(constPool, classInfo.getNameIndex());
			return className.getValue();
		case FIELD_REF:
		case INTERFACE_METHOD_REF:
		case METHOD_REF:
			BaseRefInfo refInfo = (BaseRefInfo) info;
			ClassInfo clsInfo = getClassInfoInfo(constPool, refInfo.getClassIndex());
			NameAndTypeInfo typeInfo = getNameAndTypeInfo(constPool, refInfo.getNameAndTypeIndex());
			return getDisplayValue(constPool, clsInfo) + "." + getDisplayValue(constPool, typeInfo);
		default:
			return "";
		}
	}

	private static Utf8Info getUtf8Info(ConstantPool constPool, int index) {
		return constPool.get(index, Utf8Info.class);
	}

	private static ClassInfo getClassInfoInfo(ConstantPool constPool, int index) {
		return constPool.get(index, ClassInfo.class);
	}

	private static NameAndTypeInfo getNameAndTypeInfo(ConstantPool constPool, int index) {
		return constPool.get(index, NameAndTypeInfo.class);
	}

}
