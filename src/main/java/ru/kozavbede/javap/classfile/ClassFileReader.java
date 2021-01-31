package ru.kozavbede.javap.classfile;

import java.io.IOException;
import java.io.InputStream;

import ru.kozavbede.javap.attributes.AttributeReader;
import ru.kozavbede.javap.attributes.IAttribute;
import ru.kozavbede.javap.constpool.ConstantPool;
import ru.kozavbede.javap.constpool.ConstantPoolReader;
import ru.kozavbede.javap.fields.Field;
import ru.kozavbede.javap.fields.FieldReader;
import ru.kozavbede.javap.interfaces.Interface;
import ru.kozavbede.javap.interfaces.InterfaceReader;
import ru.kozavbede.javap.methods.Method;
import ru.kozavbede.javap.methods.MethodReader;
import ru.kozavbede.javap.reader.SingleInputStreamReader;

public class ClassFileReader extends SingleInputStreamReader<ClassFile> {

	private ClassFileReader(InputStream is) {
		super(is);
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
		ConstantPoolReader reader = ConstantPoolReader.Builder.from(is);
		int constantPoolCount = read2Int();
		return reader.read(constantPoolCount);
	}

	private ClassFileInfo readClassInfo() throws IOException {
		int accessFlags = read2Int();
		int thisClass = read2Int();
		int superClass = read2Int();
		return new ClassFileInfo(accessFlags, thisClass, superClass);
	}

	private void readInterfaces(ClassFile classFile) throws IOException {
		InterfaceReader reader = InterfaceReader.Builder.from(is);
		int interfaceCount = read2Int();
		Interface[] interfaces = reader.read(interfaceCount);
		classFile.setInterfaces(interfaces);
	}

	private void readFields(ClassFile classFile) throws IOException {
		FieldReader reader = FieldReader.Builder.from(is, classFile.getConstantPool());
		int fieldCount = read2Int();
		Field[] fields = reader.read(fieldCount);
		classFile.setFields(fields);
	}

	private void readMethods(ClassFile classFile) throws IOException {
		MethodReader reader = MethodReader.Builder.from(is, classFile.getConstantPool());
		int methodsCount = read2Int();
		Method[] methods = reader.read(methodsCount);
		classFile.setMethods(methods);
	}

	private void readAttributes(ClassFile classFile) throws IOException {
		AttributeReader reader = AttributeReader.Builder.from(is, classFile.getConstantPool());
		int attributeCount = read2Int();
		IAttribute[] attributes = reader.read(attributeCount);
		classFile.setAttributes(attributes);
	}

	public static class Builder {

		private Builder() {

		}

		public static ClassFileReader from(InputStream is) {
			return new ClassFileReader(is);
		}
	}
}
