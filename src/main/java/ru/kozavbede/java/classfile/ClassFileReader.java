package ru.kozavbede.java.classfile;

import java.io.IOException;
import java.io.InputStream;

import ru.kozavbede.java.attributes.Attribute;
import ru.kozavbede.java.attributes.AttributeReader;
import ru.kozavbede.java.constpool.ConstantPool;
import ru.kozavbede.java.constpool.ConstantPoolReader;
import ru.kozavbede.java.fields.Field;
import ru.kozavbede.java.fields.FieldReader;
import ru.kozavbede.java.interfaces.Interface;
import ru.kozavbede.java.interfaces.InterfaceReader;
import ru.kozavbede.java.methods.Method;
import ru.kozavbede.java.methods.MethodReader;
import ru.kozavbede.java.reader.SingleInputStreamReader;

public class ClassFileReader extends SingleInputStreamReader<ClassFile> {

	private final ConstantPoolReader infoReader;
	private final InterfaceReader interfaceReader;
	private final FieldReader fieldReader;
	private final MethodReader methodReader;
	private final AttributeReader attributeReader;

	public ClassFileReader(InputStream is) {
		super(is);
		this.infoReader = new ConstantPoolReader(is);
		this.interfaceReader = new InterfaceReader(is);
		this.fieldReader = new FieldReader(is);
		this.methodReader = new MethodReader(is);
		this.attributeReader = new AttributeReader(is);
	}

	@Override
	public ClassFile read() throws IOException {
		ClassFileVersion classFileVersion = readVersion();
		ConstantPool constantPool = readConstantPool();
		ClassFileInfo info = readClassInfo();
		ClassFile classFile = new ClassFile(classFileVersion, info, constantPool);

		readInterfaces(classFile);
		readFields(classFile);
		readMethods(classFile);
		readAttributes(classFile);

		return classFile;
	}

	private ClassFileVersion readVersion() throws IOException {
		read4Int(); // magic
		int minorVersion = read2Int();
		int majorVersion = read2Int();
		return new ClassFileVersion(minorVersion, majorVersion);
	}

	private ConstantPool readConstantPool() throws IOException {
		int constantPoolCount = read2Int();
		return infoReader.read(constantPoolCount);
	}

	private ClassFileInfo readClassInfo() throws IOException {
		int accessFlags = read2Int();
		int thisClass = read2Int();
		int superClass = read2Int();
		return new ClassFileInfo(accessFlags, thisClass, superClass);
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

	private void readMethods(ClassFile classFile) throws IOException {
		int methodsCount = read2Int();
		Method[] methods = methodReader.read(methodsCount);
		classFile.setMethods(methods);
	}

	private void readAttributes(ClassFile classFile) throws IOException {
		int attributeCount = read2Int();
		Attribute[] attributes = attributeReader.read(attributeCount);
		classFile.setAttributes(attributes);
	}
}
