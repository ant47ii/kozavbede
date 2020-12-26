package ru.kozavbede.java.classfile;

import java.io.IOException;
import java.io.InputStream;

import ru.kozavbede.java.reader.BaseInputStreamReader;

public class ClassFileReader extends BaseInputStreamReader<ClassFile> {

	private final ClassFileBuilder builder;

	public ClassFileReader(InputStream is, ClassFileBuilder builder) {
		super(is);
		this.builder = builder;
	}

	@Override
	public ClassFile read() throws IOException {
		return builder.createMain(this);
	}

}
