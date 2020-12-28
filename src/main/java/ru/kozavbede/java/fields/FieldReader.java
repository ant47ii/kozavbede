package ru.kozavbede.java.fields;

import java.io.IOException;
import java.io.InputStream;

import ru.kozavbede.java.attributes.AttributeReader;
import ru.kozavbede.java.reader.MultiInputStreamReader;

public class FieldReader extends MultiInputStreamReader<Field[]> {

	private final AttributeReader attributeReader;

	public FieldReader(InputStream is) {
		super(is);
		this.attributeReader = new AttributeReader(is);
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

}
