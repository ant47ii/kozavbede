package ru.kozavbede.java.methods;

import java.io.IOException;
import java.io.InputStream;

import ru.kozavbede.java.attributes.AttributeReader;
import ru.kozavbede.java.reader.MultiInputStreamReader;

public class MethodReader extends MultiInputStreamReader<Method[]> {

	private final AttributeReader attributeReader;

	public MethodReader(InputStream is) {
		super(is);
		this.attributeReader = new AttributeReader(is);
	}

	@Override
	public Method[] read(int methodCount) throws IOException {
		Method[] methods = new Method[methodCount];
		for (int i = 0; i < methodCount; i++) {
			int accessFlags = read2Int(); // TODO
			int nameIndex = read2Int();
			int descriptorIndex = read2Int();
			int attributesCount = read2Int();

			Method method = new Method(nameIndex, descriptorIndex);
			method.setAttribures(attributeReader.read(attributesCount));
			methods[i] = method;
		}
		return methods;
	}
}
