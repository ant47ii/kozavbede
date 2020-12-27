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

public class App {

	public static void main(String[] args) throws IOException {
		try (InputStream is = new FileInputStream("C:\\java\\Bl.class")) {
			ClassFileReader classFileReader = new ClassFileReader(is);
			ClassFile classFile = classFileReader.read();

			printConstantPool(classFile.getConstantPool());
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
