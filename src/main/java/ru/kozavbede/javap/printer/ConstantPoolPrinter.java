package ru.kozavbede.javap.printer;

import static ru.kozavbede.javap.printer.Helper.println;

import ru.kozavbede.javap.constpool.ConstantPool;
import ru.kozavbede.javap.constpool.IConstantPoolRow;
import ru.kozavbede.javap.constpool.impl.BaseRefInfo;
import ru.kozavbede.javap.constpool.impl.ClassInfo;
import ru.kozavbede.javap.constpool.impl.NameAndTypeInfo;
import ru.kozavbede.javap.constpool.impl.StringInfo;
import ru.kozavbede.javap.constpool.impl.Utf8Info;

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
			Utf8Info stringInfoName = constPool.get(stringInfo.getNameIndex(), Utf8Info.class);
			return stringInfoName.getValue();
		case NAME_AND_TYPE:
			NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo) info;
			Utf8Info typeName = constPool.get(nameAndTypeInfo.getNameIndex(), Utf8Info.class);
			Utf8Info descName = constPool.get(nameAndTypeInfo.getDescriptorIndex(), Utf8Info.class);
			return typeName.getValue() + ":" + descName.getValue();
		case CLASS:
			ClassInfo classInfo = (ClassInfo) info;
			Utf8Info className = constPool.get(classInfo.getNameIndex(), Utf8Info.class);
			return className.getValue();
		case FIELD_REF:
		case INTERFACE_METHOD_REF:
		case METHOD_REF:
			BaseRefInfo refInfo = (BaseRefInfo) info;
			ClassInfo clsInfo = constPool.get(refInfo.getClassIndex(), ClassInfo.class);
			NameAndTypeInfo typeInfo = constPool.get(refInfo.getNameAndTypeIndex(), NameAndTypeInfo.class);
			return getDisplayValue(constPool, clsInfo) + "." + getDisplayValue(constPool, typeInfo);
		default:
			return "";
		}
	}
}
