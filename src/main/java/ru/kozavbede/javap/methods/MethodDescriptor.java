package ru.kozavbede.javap.methods;

import java.util.ArrayList;
import java.util.List;

import ru.kozavbede.javap.fields.FieldType;

public class MethodDescriptor {

	private final IFieldTypeRow[] parameterTypes;
	private final IFieldTypeRow returnType;

	private MethodDescriptor(IFieldTypeRow[] parameterTypes, IFieldTypeRow returnType) {
		this.parameterTypes = parameterTypes;
		this.returnType = returnType;
	}

	public static MethodDescriptor from(String descriptor) {
		Parser parser = new Parser(descriptor);
		return parser.parse();
	}

	public IFieldTypeRow[] getParameterTypes() {
		return parameterTypes;
	}

	public IFieldTypeRow getReturnType() {
		return returnType;
	}

	public static interface IFieldTypeRow {
		public FieldType getType();

		public FieldType getTarget();
	}

	public static class FieldTypeRow implements IFieldTypeRow {
		private FieldType type;
		private FieldType target;

		public FieldTypeRow(FieldType type, FieldType target) {
			this.type = type;
			this.target = target;
		}

		@Override
		public FieldType getType() {
			return type;
		}

		@Override
		public FieldType getTarget() {
			return target;
		}
	}

	public static class FieldClassTypeRow extends FieldTypeRow {
		private String className;

		public FieldClassTypeRow(FieldType type, FieldType target, String className) {
			super(type, target);
			this.className = className;
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

		private IFieldTypeRow[] parseParameters() {
			if (parameterDescriptor.isEmpty()) {
				return new IFieldTypeRow[0];
			}

			index = -1;
			List<IFieldTypeRow> types = new ArrayList<>();
			while (index < parameterDescriptor.length() - 1) {
				IFieldTypeRow type = parse(parameterDescriptor);
				types.add(type);
			}

			return types.toArray(new IFieldTypeRow[0]);
		}

		private IFieldTypeRow parseReturn() {
			index = -1;
			return parse(returnDescriptor);
		}

		private IFieldTypeRow parse(String s) {
			FieldType type = read(s);
			FieldType target = null;

			if (type == FieldType.CLASS_NAME) {
				return new FieldClassTypeRow(type, null, readClass(s));
			}

			if (type == FieldType.ARRAY) {
				target = read(s);
			}

			if (target == FieldType.CLASS_NAME) {
				return new FieldClassTypeRow(type, target, readClass(s));
			} else {
				return new FieldTypeRow(type, target);
			}
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
