package ru.kozavbede.java.fields;

import java.io.IOException;
import java.io.InputStream;

import ru.kozavbede.java.attributes.AttributeReader;
import ru.kozavbede.java.constpool.ConstantPool;
import ru.kozavbede.java.reader.MultiInputStreamReader;

public class FieldReader extends MultiInputStreamReader<Field[]> {

	private final AttributeReader attributeReader;

	private FieldReader(InputStream is, ConstantPool pool) {
		super(is);
		this.attributeReader = AttributeReader.Builder.from(is, pool);
	}

	@Override
	public Field[] read(int fieldCount) throws IOException {
		Field[] fields = new Field[fieldCount];
		for (int i = 0; i < fieldCount; i++) {
			int accessFlags = read2Int();
			int nameIndex = read2Int();
			int descriptorIndex = read2Int();

			Field field = new Field(accessFlags, nameIndex, descriptorIndex);
			int attributesCount = read2Int();
			field.setAttribures(attributeReader.read(attributesCount));
			fields[i] = field;
		}
		return fields;
	}

	public static class Builder {

		private Builder() {

		}

		public static FieldReader from(InputStream is, ConstantPool pool) {
			return new FieldReader(is, pool);
		}

	}

}
