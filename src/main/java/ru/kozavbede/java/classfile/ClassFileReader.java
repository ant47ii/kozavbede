package ru.kozavbede.java.classfile;

import java.io.IOException;
import java.io.InputStream;

import ru.kozavbede.java.constpool.InfoBuilder;
import ru.kozavbede.java.constpool.InfoReader;
import ru.kozavbede.java.interfaces.InterfaceReader;
import ru.kozavbede.java.reader.BaseInputStreamReader;

public class ClassFileReader extends BaseInputStreamReader<ClassFile> {

	private final ClassFileBuilder builder;
	private final InfoReader infoReader;
	private final InterfaceReader interfaceReader;

	public ClassFileReader(InputStream is, ClassFileBuilder builder) {
		super(is);
		this.builder = builder;
		this.infoReader = new InfoReader(is, new InfoBuilder());
		this.interfaceReader = new InterfaceReader(is);
	}

	@Override
	public ClassFile read() throws IOException {
		ClassFile classFile = builder.createMain(this);
		classFile.setConstantPool(infoReader.read());

		builder.readClassInfo(this);
		classFile.setInterfaces(interfaceReader.read());

		return classFile;
	}

}
