package ru.kozavbede.java.attributes;

import java.io.IOException;
import java.io.InputStream;

import ru.kozavbede.java.constpool.ConstantPool;
import ru.kozavbede.java.constpool.impl.Utf8Info;
import ru.kozavbede.java.reader.MultiInputStreamReader;

public class AttributeReader extends MultiInputStreamReader<Attribute[]> {

	private final ConstantPool pool;

	protected AttributeReader(InputStream is, ConstantPool pool) {
		super(is);
		this.pool = pool;
	}

	@Override
	public Attribute[] read(int attributeCount) throws IOException {
		Attribute[] attributes = new Attribute[attributeCount];
		for (int i = 0; i < attributeCount; i++) {
			int attributeNameIndex = read2Int();

			Utf8Info name = pool.get(attributeNameIndex, Utf8Info.class);

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

	public static class Builder {

		private Builder() {

		}

		public static AttributeReader from(InputStream is, ConstantPool pool) {
			return new AttributeReader(is, pool);
		}

	}
}
