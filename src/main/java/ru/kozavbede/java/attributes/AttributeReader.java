package ru.kozavbede.java.attributes;

import java.io.IOException;
import java.io.InputStream;

import ru.kozavbede.java.reader.MultiInputStreamReader;

public class AttributeReader extends MultiInputStreamReader<Attribute[]> {

	public AttributeReader(InputStream is) {
		super(is);
	}

	@Override
	public Attribute[] read(int attributeCount) throws IOException {
		Attribute[] attributes = new Attribute[attributeCount];
		for (int i = 0; i < attributeCount; i++) {
			int attributeNameIndex = read2Int();

			// TODO разобраться
			// фиктивно прочитаем тело атрибута.
			// здесь необходим пул констант т.к. нужно определение attributeNameIndex
			int attributeLength = read4Int();
			readNBytes(attributeLength);

			Attribute attribute = new Attribute(attributeNameIndex);
			attributes[i] = attribute;
		}
		return attributes;
	}

}
