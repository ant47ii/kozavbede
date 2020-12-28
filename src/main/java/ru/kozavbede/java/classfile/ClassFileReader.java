package ru.kozavbede.java.classfile;

import java.io.IOException;
import java.io.InputStream;

import ru.kozavbede.java.constpool.ConstantPoolReader;
import ru.kozavbede.java.constpool.IConstantPoolRow;
import ru.kozavbede.java.fields.Field;
import ru.kozavbede.java.fields.FieldReader;
import ru.kozavbede.java.interfaces.Interface;
import ru.kozavbede.java.interfaces.InterfaceReader;
import ru.kozavbede.java.reader.SingleInputStreamReader;

public class ClassFileReader extends SingleInputStreamReader<ClassFile> {

	private final ConstantPoolReader infoReader;
	private final InterfaceReader interfaceReader;
	private final FieldReader fieldReader;

	public ClassFileReader(InputStream is) {
		super(is);
		this.infoReader = new ConstantPoolReader(is);
		this.interfaceReader = new InterfaceReader(is);
		this.fieldReader = new FieldReader(is);
	}

	@Override
	public ClassFile read() throws IOException {
		ClassFile classFile = new ClassFile();

		readVersion(classFile);
		readConstantPool(classFile);
		readClassInfo(classFile);
		readInterfaces(classFile);
		readFields(classFile);

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
		int constantPoolCount = read2Int();
		IConstantPoolRow[] constantPool = infoReader.read(constantPoolCount);
		classFile.setConstantPool(constantPool);
	}

	private void readClassInfo(ClassFile classFile) throws IOException {
		// TODO : обернуть в структуру. Писать в classFile.
		int accessFlags = read2Int();
		int thisClass = read2Int();
		int superClass = read2Int();
	}

	private void readInterfaces(ClassFile classFile) throws IOException {
		int interfaceCount = read2Int();
		Interface[] interfaces = interfaceReader.read(interfaceCount);
		classFile.setInterfaces(interfaces);
	}

	private void readFields(ClassFile classFile) throws IOException {
		int fieldCount = read2Int();
		Field[] fields = fieldReader.read(fieldCount);
		classFile.setFields(fields);
	}

}
