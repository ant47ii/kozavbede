package ru.kozavbede.java;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import ru.kozavbede.java.classfile.ClassFile;
import ru.kozavbede.java.classfile.ClassFileReader;
import ru.kozavbede.java.constpool.ConstantPool;
import ru.kozavbede.java.printer.ClassFilePrinter;
import ru.kozavbede.java.printer.ConstantPoolPrinter;
import ru.kozavbede.java.printer.FieldPrinter;
import ru.kozavbede.java.printer.MethodPrinter;

public class App {

	public static void main(String[] args) throws IOException {
		try (InputStream is = new FileInputStream("C:\\java\\Bl.class")) {
			ClassFileReader classFileReader = ClassFileReader.Builder.from(is);
			ClassFile classFile = classFileReader.read();
			ConstantPool constPool = classFile.getConstantPool();

			ClassFilePrinter.printInfo(constPool, classFile);

			FieldPrinter.printFields(constPool, classFile.getFields());
			ConstantPoolPrinter.printConstantPool(constPool);
			MethodPrinter.printMethods(constPool, classFile.getMethods());
		}
	}

}
