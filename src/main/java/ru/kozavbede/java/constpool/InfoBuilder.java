package ru.kozavbede.java.constpool;

import java.io.IOException;

import ru.kozavbede.java.constpool.impl.ClassInfo;
import ru.kozavbede.java.constpool.impl.DoubleInfo;
import ru.kozavbede.java.constpool.impl.FloatInfo;
import ru.kozavbede.java.constpool.impl.IntegerInfo;
import ru.kozavbede.java.constpool.impl.LongInfo;
import ru.kozavbede.java.constpool.impl.MethodrefInfo;
import ru.kozavbede.java.constpool.impl.NameAndTypeInfo;
import ru.kozavbede.java.constpool.impl.StringInfo;
import ru.kozavbede.java.constpool.impl.Utf8Info;
import ru.kozavbede.java.reader.IByteReader;

public class InfoBuilder {

	public InfoBuilder() {

	}

	public IInfo buildTag(Tag tag, int tagIndex, IByteReader reader) throws IOException {
		switch (tag) {
		case CLASS:
			return createClass(tagIndex, reader);
		case NAME_AND_TYPE:
			return createNameAndType(tagIndex, reader);
		case STRING:
			return createString(tagIndex, reader);
		case UTF8:
			return createUtf8(tagIndex, reader);
		case INTEGER:
			return createInteger(tagIndex, reader);
		case FLOAT:
			return createFloat(tagIndex, reader);
		case LONG:
			return createLong(tagIndex, reader);
		case DOUBLE:
			return createDouble(tagIndex, reader);
		case FIELD_REF:
		case METHOD_REF:
		case INTERFACE_METHOD_REF:
			return createRef(tagIndex, reader);
		default:
			return null;
		}
	}

	private IInfo createClass(int tagIndex, IByteReader reader) throws IOException {
		int nameIndex = reader.read2Int();
		return new ClassInfo(tagIndex, nameIndex);
	}

	private IInfo createNameAndType(int tagIndex, IByteReader reader) throws IOException {
		int nameIndex = reader.read2Int();
		int descriptorIndex = reader.read2Int();
		return new NameAndTypeInfo(tagIndex, nameIndex, descriptorIndex);
	}

	private IInfo createString(int tagIndex, IByteReader reader) throws IOException {
		int stringIndex = reader.read2Int();
		return new StringInfo(tagIndex, stringIndex);
	}

	private IInfo createUtf8(int tagIndex, IByteReader reader) throws IOException {
		int len = reader.read2Int();
		byte[] str = reader.readNBytes(len);
		return new Utf8Info(tagIndex, new String(str));
	}

	private IInfo createRef(int tagIndex, IByteReader reader) throws IOException {
		int classIndex = reader.read2Int();
		int nameAndTypeIndex = reader.read2Int();
		return new MethodrefInfo(tagIndex, classIndex, nameAndTypeIndex);
	}

	private IInfo createInteger(int tagIndex, IByteReader reader) throws IOException {
		int value = reader.read4Int();
		return new IntegerInfo(tagIndex, value);
	}

	private IInfo createFloat(int tagIndex, IByteReader reader) throws IOException {
		float value = Float.intBitsToFloat(reader.read4Int());
		return new FloatInfo(tagIndex, value);
	}

	private IInfo createLong(int tagIndex, IByteReader reader) throws IOException {
		long value = reader.read8Long();
		return new LongInfo(tagIndex, value);
	}

	private IInfo createDouble(int tagIndex, IByteReader reader) throws IOException {
		long longValue = reader.read8Long();
		double value = Double.longBitsToDouble(longValue);
		return new DoubleInfo(tagIndex, value);
	}
}
