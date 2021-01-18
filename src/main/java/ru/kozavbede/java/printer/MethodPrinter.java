package ru.kozavbede.java.printer;

import static ru.kozavbede.java.printer.Helper.println;

import java.util.Arrays;
import java.util.stream.Collectors;

import ru.kozavbede.java.Modifier;
import ru.kozavbede.java.constpool.ConstantPool;
import ru.kozavbede.java.constpool.impl.Utf8Info;
import ru.kozavbede.java.methods.Method;
import ru.kozavbede.java.methods.MethodDescriptor;
import ru.kozavbede.java.methods.MethodDescriptor.FieldClassTypeRow;
import ru.kozavbede.java.methods.MethodDescriptor.IFieldTypeRow;

public class MethodPrinter {

	private MethodPrinter() {

	}

	public static void printMethods(ConstantPool constPool, Method[] methods) {
		println("Methods:");
		for (Method method : methods) {
			printMethod(constPool, method);
		}
	}

	private static void printMethod(ConstantPool constPool, Method method) {
		Utf8Info name = constPool.get(method.getNameIndex(), Utf8Info.class);
		Utf8Info descriptor = constPool.get(method.getDescriptorIndex(), Utf8Info.class);
		String accessFlags = getAccessFlags(method);

		MethodDescriptor desc = MethodDescriptor.from(descriptor.getValue());

		String returnType = getReturnType(desc);
		String parameters = getParameters(desc);

		println("  %s %s %s(%s)", accessFlags, returnType, name, parameters);
	}

	private static String getAccessFlags(Method method) {
		int flags = method.getAccessFlags();
		return Modifier.fromValue(flags).stream().map(Modifier::getName).collect(Collectors.joining(" "));
	}

	private static String getReturnType(MethodDescriptor desc) {
		return getType(desc.getReturnType());
	}

	private static String getParameters(MethodDescriptor desc) {
		return Arrays.stream(desc.getParameterTypes()).map(MethodPrinter::getType).collect(Collectors.joining(", "));
	}

	private static String getType(IFieldTypeRow row) {
		String type = null;

		if (row instanceof FieldClassTypeRow) {
			FieldClassTypeRow cls = (FieldClassTypeRow) row;
			type = cls.getClassName();
		} else {
			type = row.getType().getType();
		}

		if (row.getTarget() != null) {
			return type + "[]";
		}

		return type;
	}
}
