package ru.kozavbede.java.methods;

import java.util.ArrayList;
import java.util.List;

import ru.kozavbede.java.fields.FieldType;

public class MethodDescriptor {

	private final FieldTypeRow[] parameterTypes;
	private final FieldTypeRow returnType;

	private MethodDescriptor(FieldTypeRow[] parameterTypes, FieldTypeRow returnType) {
		this.parameterTypes = parameterTypes;
		this.returnType = returnType;
	}

	public static MethodDescriptor from(String descriptor) {
		Parser parser = new Parser(descriptor);
		return parser.parse();
	}

	public FieldTypeRow[] getParameterTypes() {
		return parameterTypes;
	}

	public FieldTypeRow getReturnType() {
		return returnType;
	}

	public static class FieldTypeRow {
		private boolean isArray;
		private FieldType type;
		private String className;

		public FieldTypeRow(FieldType type, boolean isArray, String className) {
			this.type = type;
			this.isArray = isArray;
			this.className = className;
		}

		public boolean isArray() {
			return isArray;
		}

		public FieldType getType() {
			return type;
		}

		public String getClassName() {
			return className;
		}
	}

	private static class Parser {

		private final String parameterDescriptor;
		private final String returnDescriptor;
		private int index;

		protected Parser(String descriptor) {
			int last = descriptor.indexOf(')');
			parameterDescriptor = descriptor.substring(1, last);
			returnDescriptor = descriptor.substring(last + 1, descriptor.length());
		}

		public MethodDescriptor parse() {
			return new MethodDescriptor(parseParameters(), parseReturn());
		}

		private FieldTypeRow[] parseParameters() {
			if (parameterDescriptor.isEmpty()) {
				return new FieldTypeRow[0];
			}

			index = -1;
			List<FieldTypeRow> types = new ArrayList<>();
			while (index < parameterDescriptor.length() - 1) {
				FieldTypeRow type = parse(parameterDescriptor);
				types.add(type);
			}

			return types.toArray(new FieldTypeRow[0]);
		}

		private FieldTypeRow parseReturn() {
			index = -1;
			return parse(returnDescriptor);
		}

		private FieldTypeRow parse(String s) {
			boolean isArray = false;
			FieldType type = read(s);

			if (type == FieldType.ARRAY) {
				isArray = true;
				type = read(s);
			}

			String className = null;
			if (type == FieldType.CLASS_NAME) {
				className = readClass(s);
			} else if (isArray) {
				type = read(s);
			}

			return new FieldTypeRow(type, isArray, className);
		}

		private FieldType read(String descriptor) {
			char term = descriptor.charAt(++index);
			return FieldType.fromTerm(term);
		}

		private String readClass(String descriptor) {
			int last = descriptor.indexOf(';', index);
			String result = descriptor.substring(++index, last);
			index = last;
			return result;
		}

	}
}
