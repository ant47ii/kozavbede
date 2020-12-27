package ru.kozavbede.java;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import ru.kozavbede.java.classfile.ClassFile;
import ru.kozavbede.java.classfile.ClassFileBuilder;
import ru.kozavbede.java.classfile.ClassFileReader;
import ru.kozavbede.java.constpool.IInfo;
import ru.kozavbede.java.constpool.InfoBuilder;
import ru.kozavbede.java.constpool.InfoReader;
import ru.kozavbede.java.constpool.impl.ClassInfo;
import ru.kozavbede.java.constpool.impl.MethodRefInfo;
import ru.kozavbede.java.constpool.impl.NameAndTypeInfo;
import ru.kozavbede.java.constpool.impl.StringInfo;
import ru.kozavbede.java.constpool.impl.Utf8Info;

public class App {

	public static void main(String[] args) throws IOException {
		try (InputStream is = new FileInputStream("C:\\java\\Bl.class")) {
			ClassFileReader classFileReader = new ClassFileReader(is, new ClassFileBuilder());
			ClassFile classFile = classFileReader.read();

			InfoReader infoReader = new InfoReader(is, new InfoBuilder());
			IInfo[] infos = infoReader.read();

			printConstantPool(infos);
		}
	}

	private static void printConstantPool(IInfo[] infos) {
		System.out.println("Constant pool:");
		for (IInfo info : infos) {
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

	private static String getDisplayValue(IInfo[] infos, IInfo info) {
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
			MethodRefInfo methodrefInfo = (MethodRefInfo) info;
			ClassInfo clsInfo = getClassInfoInfo(infos, methodrefInfo.getClassIndex());
			NameAndTypeInfo typeInfo = getNameAndTypeInfo(infos, methodrefInfo.getNameAndTypeIndex());
			return getDisplayValue(infos, clsInfo) + "." + getDisplayValue(infos, typeInfo);
		}

		return "";
	}

	private static Utf8Info getUtf8Info(IInfo[] infos, int index) {
		return (Utf8Info) infos[index - 1];
	}

	private static ClassInfo getClassInfoInfo(IInfo[] infos, int index) {
		return (ClassInfo) infos[index - 1];
	}

	private static NameAndTypeInfo getNameAndTypeInfo(IInfo[] infos, int index) {
		return (NameAndTypeInfo) infos[index - 1];
	}

}
