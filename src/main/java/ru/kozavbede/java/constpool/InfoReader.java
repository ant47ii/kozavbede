package ru.kozavbede.java.constpool;

import java.io.IOException;
import java.io.InputStream;

import ru.kozavbede.java.constpool.impl.ClassInfo;
import ru.kozavbede.java.constpool.impl.DoubleInfo;
import ru.kozavbede.java.constpool.impl.FieldRefInfo;
import ru.kozavbede.java.constpool.impl.FloatInfo;
import ru.kozavbede.java.constpool.impl.IntegerInfo;
import ru.kozavbede.java.constpool.impl.InterfaceMethodRefInfo;
import ru.kozavbede.java.constpool.impl.LongInfo;
import ru.kozavbede.java.constpool.impl.MethodRefInfo;
import ru.kozavbede.java.constpool.impl.NameAndTypeInfo;
import ru.kozavbede.java.constpool.impl.StringInfo;
import ru.kozavbede.java.constpool.impl.Utf8Info;
import ru.kozavbede.java.reader.BaseInputStreamReader;

public class InfoReader extends BaseInputStreamReader<IInfo[]> {

	public InfoReader(InputStream is) {
		super(is);
	}

	@Override
	public IInfo[] read() throws IOException {
		int constantPoolCount = read2Int();
		if (constantPoolCount > 0) {
			return readInfo(constantPoolCount);
		}

		return new IInfo[0];
	}

	private IInfo[] readInfo(int constantPoolCount) throws IOException {
		IInfo[] infos = new IInfo[constantPoolCount - 1];
		for (int i = 0; i < constantPoolCount - 1; i++) {
			int infoTagType = read1Int();
			Tag tag = Tag.fromIndex(infoTagType);
			if (tag == null) {
				// TODO: throw ex...
			}

			infos[i] = readTag(tag, i + 1);
			if (tag == Tag.LONG || tag == Tag.DOUBLE) {
				// then the next usable item in the pool is located at index n+2. The
				// constant_pool index n+1 must be valid but is considered unusable.
				i++;
			}
		}
		return infos;
	}

	private IInfo readTag(Tag tag, int tagIndex) throws IOException {
		switch (tag) {
		case CLASS:
			return createClass(tagIndex);
		case NAME_AND_TYPE:
			return createNameAndType(tagIndex);
		case STRING:
			return createString(tagIndex);
		case UTF8:
			return createUtf8(tagIndex);
		case INTEGER:
			return createInteger(tagIndex);
		case FLOAT:
			return createFloat(tagIndex);
		case LONG:
			return createLong(tagIndex);
		case DOUBLE:
			return createDouble(tagIndex);
		case FIELD_REF:
			return createFieldRef(tagIndex);
		case METHOD_REF:
			return createMethodRef(tagIndex);
		case INTERFACE_METHOD_REF:
			return createInterfaceMethodRef(tagIndex);
		default:
			return null;
		}
	}

	private IInfo createClass(int tagIndex) throws IOException {
		int nameIndex = read2Int();
		return new ClassInfo(tagIndex, nameIndex);
	}

	private IInfo createNameAndType(int tagIndex) throws IOException {
		int nameIndex = read2Int();
		int descriptorIndex = read2Int();
		return new NameAndTypeInfo(tagIndex, nameIndex, descriptorIndex);
	}

	private IInfo createString(int tagIndex) throws IOException {
		int stringIndex = read2Int();
		return new StringInfo(tagIndex, stringIndex);
	}

	private IInfo createUtf8(int tagIndex) throws IOException {
		int len = read2Int();
		byte[] str = readNBytes(len);
		return new Utf8Info(tagIndex, new String(str));
	}

	private IInfo createFieldRef(int tagIndex) throws IOException {
		int classIndex = read2Int();
		int nameAndTypeIndex = read2Int();
		return new FieldRefInfo(tagIndex, classIndex, nameAndTypeIndex);
	}

	private IInfo createInterfaceMethodRef(int tagIndex) throws IOException {
		int classIndex = read2Int();
		int nameAndTypeIndex = read2Int();
		return new InterfaceMethodRefInfo(tagIndex, classIndex, nameAndTypeIndex);
	}

	private IInfo createMethodRef(int tagIndex) throws IOException {
		int classIndex = read2Int();
		int nameAndTypeIndex = read2Int();
		return new MethodRefInfo(tagIndex, classIndex, nameAndTypeIndex);
	}

	private IInfo createInteger(int tagIndex) throws IOException {
		int value = read4Int();
		return new IntegerInfo(tagIndex, value);
	}

	private IInfo createFloat(int tagIndex) throws IOException {
		float value = Float.intBitsToFloat(read4Int());
		return new FloatInfo(tagIndex, value);
	}

	private IInfo createLong(int tagIndex) throws IOException {
		long value = read8Long();
		return new LongInfo(tagIndex, value);
	}

	private IInfo createDouble(int tagIndex) throws IOException {
		long longValue = read8Long();
		double value = Double.longBitsToDouble(longValue);
		return new DoubleInfo(tagIndex, value);
	}
}
