package ru.kozavbede.javap.printer;

import static ru.kozavbede.javap.printer.Helper.println;

import java.util.stream.Collectors;

import ru.kozavbede.javap.Modifier;
import ru.kozavbede.javap.attributes.AttributeType;
import ru.kozavbede.javap.attributes.IAttribute;
import ru.kozavbede.javap.attributes.impl.ConstantAttribute;
import ru.kozavbede.javap.constpool.ConstantPool;
import ru.kozavbede.javap.constpool.IConstantPoolRow;
import ru.kozavbede.javap.constpool.impl.StringInfo;
import ru.kozavbede.javap.constpool.impl.Utf8Info;
import ru.kozavbede.javap.fields.Field;
import ru.kozavbede.javap.fields.FieldType;

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
		if (constVal.length() > 0) {
			constVal = " = " + constVal;
		}

		println("  %s %s %s%s", modifiers, type, name, constVal);
	}

	private static String getType(ConstantPool constPool, Field field) {
		String typeName = constPool.get(field.getDescriptorIndex(), Utf8Info.class).getValue();

		FieldType type = FieldType.fromTerm(typeName.charAt(0));
		if (type == FieldType.CLASS_NAME) {
			return typeName.substring(1, typeName.length() - 1);
		}
		return type.getType();
	}

	private static String getModifiers(Field field) {
		int flags = field.getAccessFlags();
		return Modifier.fromValue(flags).stream()
				.map(Modifier::getName)
				.collect(Collectors.joining(" "));
	}

	private static String getName(ConstantPool constPool, Field field) {
		return constPool.get(field.getNameIndex(), Utf8Info.class).getValue();
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
			return "";
		}

		if (con instanceof StringInfo) {
			StringInfo str = (StringInfo) con;
			String value = constPool.get(str.getNameIndex(), Utf8Info.class).getValue();
			return "\"" + value + "\"";
		}
		return con.toString(); // TODO это плохо операться на toString, нужен метод получения значения.
	}
}
