package ru.kozavbede.java.attributes;

import java.io.IOException;
import java.io.InputStream;

import ru.kozavbede.java.attributes.impl.ConstantAttribute;
import ru.kozavbede.java.attributes.impl.SignatureAttribute;
import ru.kozavbede.java.constpool.ConstantPool;
import ru.kozavbede.java.constpool.impl.Utf8Info;
import ru.kozavbede.java.reader.MultiInputStreamReader;

public class AttributeReader extends MultiInputStreamReader<IAttribute[]> {

	private final ConstantPool pool;

	protected AttributeReader(InputStream is, ConstantPool pool) {
		super(is);
		this.pool = pool;
	}

	@Override
	public IAttribute[] read(int attributeCount) throws IOException {
		IAttribute[] attributes = new IAttribute[attributeCount];
		for (int i = 0; i < attributeCount; i++) {
			int attributeNameIndex = read2Int();
			int attributeLength = read4Int();

			Utf8Info name = pool.get(attributeNameIndex, Utf8Info.class);
			AttributeType type = AttributeType.fromName(name.getValue());
			IAttribute attribute = new BaseAttribute(attributeNameIndex, type);

			if (type != null) {
				switch (type) {
				case CONSTANT_VALUE:
					attribute = readConstantValue(attribute);
					break;
				case SIGNATURE:
					attribute = readSignature(attribute);
					break;

				default:
					// Для неподдерживаемых атрибутов фиктивно прочитаем тело.
					readNBytes(attributeLength);
					attribute = null;
					break;
				}
			}

			attributes[i] = attribute;
		}
		return attributes;
	}

	private ConstantAttribute readConstantValue(IAttribute attribute) throws IOException {
		int attributeNameIndex = read2Int();
		return new ConstantAttribute(attribute, attributeNameIndex);
	}

	private SignatureAttribute readSignature(IAttribute attribute) throws IOException {
		int signatureIndex = read2Int();
		return new SignatureAttribute(attribute, signatureIndex);
	}

	public static class Builder {

		private Builder() {

		}

		public static AttributeReader from(InputStream is, ConstantPool pool) {
			return new AttributeReader(is, pool);
		}

	}
}
