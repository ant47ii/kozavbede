package ru.kozavbede.java.printer;

import static ru.kozavbede.java.printer.Helper.getUtf8Info;
import static ru.kozavbede.java.printer.Helper.println;

import java.util.stream.Collectors;

import ru.kozavbede.java.Modifier;
import ru.kozavbede.java.attributes.AttributeType;
import ru.kozavbede.java.attributes.IAttribute;
import ru.kozavbede.java.attributes.impl.ConstantAttribute;
import ru.kozavbede.java.constpool.ConstantPool;
import ru.kozavbede.java.constpool.IConstantPoolRow;
import ru.kozavbede.java.constpool.impl.StringInfo;
import ru.kozavbede.java.constpool.impl.Utf8Info;
import ru.kozavbede.java.fields.Field;
import ru.kozavbede.java.fields.FieldType;

public class FieldPrinter {

	private FieldPrinter() {

	}

	public static void printFields(ConstantPool constPool, Field[] fields) {
		for (Field field : fields) {
			printField(constPool, field);
		}
	}

	private static void printField(ConstantPool constPool, Field field) {
		String modifiers = getModifiers(field);
		String type = getType(constPool, field);
		String name = getName(constPool, field);

		String constVal = getConstantValue(constPool, field);
		if (constVal != null) {
			constVal = " = " + constVal;
		} else {
			constVal = "";
		}

		println("  %s %s %s%s;", modifiers, type, name, constVal);
	}

	private static String getType(ConstantPool constPool, Field field) {
		int descIndex = field.getDescriptorIndex();
		Utf8Info utf8 = getUtf8Info(constPool, descIndex);
		String typeName = utf8.getValue();

		char term = typeName.charAt(0);
		FieldType type = FieldType.fromTerm(term);

		if (type == FieldType.CLASS_NAME) {
			return typeName.substring(1, typeName.length() - 1);
		} else {
			return type.getType();
		}
	}

	private static String getModifiers(Field field) {
		int flags = field.getAccessFlags();
		return Modifier.fromValue(flags).stream().map(Modifier::getName).collect(Collectors.joining(" "));
	}

	private static String getName(ConstantPool constPool, Field field) {
		int nidx = field.getNameIndex();
		Utf8Info name = getUtf8Info(constPool, nidx);
		return name.getValue();
	}

	private static String getConstantValue(ConstantPool constPool, Field field) {
		IConstantPoolRow con = null;
		for (IAttribute attr : field.getAttribures()) {
			if (attr.getType() == AttributeType.CONSTANT_VALUE) {
				ConstantAttribute constAttr = (ConstantAttribute) attr;
				con = constPool.get(constAttr.getConstantValueIndex());
			}
		}

		if (con == null) {
			return null;
		}

		if (con instanceof StringInfo) {
			StringInfo str = (StringInfo) con;
			String value = getUtf8Info(constPool, str.getNameIndex()).getValue();
			return "\"" + value + "\"";
		} else {
			return con.toString();
		}
	}
}
