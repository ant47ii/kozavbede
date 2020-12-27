package ru.kozavbede.java.classfile;

import java.io.IOException;
import java.io.InputStream;

import ru.kozavbede.java.constpool.IConstantPoolRow;
import ru.kozavbede.java.constpool.ConstantPoolReader;
import ru.kozavbede.java.interfaces.Interface;
import ru.kozavbede.java.interfaces.InterfaceReader;
import ru.kozavbede.java.reader.BaseInputStreamReader;

public class ClassFileReader extends BaseInputStreamReader<ClassFile> {

	private final ConstantPoolReader infoReader;
	private final InterfaceReader interfaceReader;

	public ClassFileReader(InputStream is) {
		super(is);
		this.infoReader = new ConstantPoolReader(is);
		this.interfaceReader = new InterfaceReader(is);
	}

	@Override
	public ClassFile read() throws IOException {
		ClassFile classFile = new ClassFile();

		readVersion(classFile);
		readConstantPool(classFile);
		readClassInfo(classFile);
		readInterfaces(classFile);

		return classFile;
	}

	private void readVersion(ClassFile classFile) throws IOException {
		read4Int(); // magic
		int minorVersion = read2Int();
		int majorVersion = read2Int();

		classFile.setMinorVersion(minorVersion);
		classFile.setMajorVersion(majorVersion);
	}

	private void readConstantPool(ClassFile classFile) throws IOException {
		IConstantPoolRow[] constantPool = infoReader.read();
		classFile.setConstantPool(constantPool);
	}

	private void readClassInfo(ClassFile classFile) throws IOException {
		// TODO : обернуть в структуру. Писать в classFile.
		int accessFlags = read2Int();
		int thisClass = read2Int();
		int superClass = read2Int();
	}

	private void readInterfaces(ClassFile classFile) throws IOException {
		Interface[] interfaces = interfaceReader.read();
		classFile.setInterfaces(interfaces);
	}

}
