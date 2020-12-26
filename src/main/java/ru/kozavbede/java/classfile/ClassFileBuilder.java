package ru.kozavbede.java.classfile;

import java.io.IOException;

import ru.kozavbede.java.reader.IByteReader;

public class ClassFileBuilder {

	public ClassFileBuilder() {

	}

	public ClassFile createMain(IByteReader reader) throws IOException {
		reader.read4Int(); // magic
		int minorVersion = reader.read2Int();
		int majorVersion = reader.read2Int();

		return new ClassFile(minorVersion, majorVersion);
	}

}
