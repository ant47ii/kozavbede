package ru.kozavbede.java.printer;

import static ru.kozavbede.java.printer.Helper.getClassInfoInfo;
import static ru.kozavbede.java.printer.Helper.getNameAndTypeInfo;
import static ru.kozavbede.java.printer.Helper.getUtf8Info;
import static ru.kozavbede.java.printer.Helper.println;

import ru.kozavbede.java.constpool.ConstantPool;
import ru.kozavbede.java.constpool.IConstantPoolRow;
import ru.kozavbede.java.constpool.impl.BaseRefInfo;
import ru.kozavbede.java.constpool.impl.ClassInfo;
import ru.kozavbede.java.constpool.impl.NameAndTypeInfo;
import ru.kozavbede.java.constpool.impl.StringInfo;
import ru.kozavbede.java.constpool.impl.Utf8Info;

public class ConstantPoolPrinter {

	private ConstantPoolPrinter() {

	}

	public static void printConstantPool(ConstantPool constPool) {
		println("Constant pool:");
		for (IConstantPoolRow row : constPool) {
			if (row == null) {
				// long/double
				continue;
			}
			int index = row.getIndex();
			String tagName = row.getTag().getDisplayName();
			String value = row.toString();
			String displayValue = getDisplayValue(constPool, row);
			println(" #%-3s = %-15s %-20s %s", index, tagName, value, displayValue);
		}
	}

	public static String getDisplayValue(ConstantPool constPool, IConstantPoolRow info) {
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
}
