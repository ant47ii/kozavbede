package ru.kozavbede.javap.methods;

import java.io.IOException;
import java.io.InputStream;

import ru.kozavbede.javap.attributes.AttributeReader;
import ru.kozavbede.javap.constpool.ConstantPool;
import ru.kozavbede.javap.reader.MultiInputStreamReader;

public class MethodReader extends MultiInputStreamReader<Method[]> {

	private final AttributeReader attributeReader;

	private MethodReader(InputStream is, ConstantPool pool) {
		super(is);
		this.attributeReader = AttributeReader.Builder.from(is, pool);
	}

	@Override
	public Method[] read(int methodCount) throws IOException {
		Method[] methods = new Method[methodCount];
		for (int i = 0; i < methodCount; i++) {
			int accessFlags = read2Int();
			int nameIndex = read2Int();
			int descriptorIndex = read2Int();
			int attributesCount = read2Int();

			Method method = new Method(accessFlags, nameIndex, descriptorIndex);
			method.setAttribures(attributeReader.read(attributesCount));
			methods[i] = method;
		}
		return methods;
	}

	public static class Builder {

		private Builder() {

		}

		public static MethodReader from(InputStream is, ConstantPool pool) {
			return new MethodReader(is, pool);
		}
	}
}
