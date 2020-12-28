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
			// The value of the attribute_length item indicates the length of the subsequent
			// information in bytes. The length does not include the initial six bytes that
			// contain the attribute_name_index and attribute_length items.
			int attributeLength = read4Int();
			int info = read1Int();

			Attribute attribute = new Attribute(attributeNameIndex);
			attributes[i] = attribute;
		}
		return attributes;
	}

}
