package ru.kozavbede.javap;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import ru.kozavbede.javap.classfile.ClassFile;
import ru.kozavbede.javap.classfile.ClassFileReader;
import ru.kozavbede.javap.constpool.ConstantPool;
import ru.kozavbede.javap.printer.ClassFilePrinter;
import ru.kozavbede.javap.printer.ConstantPoolPrinter;
import ru.kozavbede.javap.printer.FieldPrinter;
import ru.kozavbede.javap.printer.MethodPrinter;

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
