package ru.kozavbede.javap.printer;

import static ru.kozavbede.javap.printer.Helper.println;

import java.util.Arrays;
import java.util.stream.Collectors;

import ru.kozavbede.javap.Modifier;
import ru.kozavbede.javap.classfile.ClassFile;
import ru.kozavbede.javap.classfile.ClassFileInfo;
import ru.kozavbede.javap.classfile.ClassFileVersion;
import ru.kozavbede.javap.constpool.ConstantPool;
import ru.kozavbede.javap.constpool.impl.ClassInfo;
import ru.kozavbede.javap.constpool.impl.Utf8Info;
import ru.kozavbede.javap.interfaces.Interface;

public class ClassFilePrinter {

	private ClassFilePrinter() {

	}

	public static void printInfo(ConstantPool constPool, ClassFile classFile) {
		printVersion(classFile);
		ClassFileInfo classFileInfo = classFile.getInfo();

		int flags = classFileInfo.getAccessFlags();
		String thisClass = getThisClass(constPool, classFileInfo);
		String superClass = getSuperClass(constPool, classFileInfo);

		println(" flags: (0x%x) %s", flags, getDisplayFlags(flags));
		println(" this_class: #%s   // %s", classFileInfo.getThisClass(), thisClass);
		println(" super_class: #%s   // %s", classFileInfo.getSuperClass(), superClass);

		String displayModifier = getModifier(flags);
		String type = getType(flags);

		String extendsClass = "";
		if (!"java/lang/Object".equals(superClass)) {
			extendsClass = "extends " + superClass;
		}

		String impls = getImplements(constPool, classFile);
		println("%s %s %s %s %s", displayModifier, type, thisClass, extendsClass, impls);
	}

	private static void printVersion(ClassFile classFile) {
		ClassFileVersion version = classFile.getVersion();
		println(" minor version: " + version.getMinorVersion());
		println(" major version: " + version.getMajorVersion());
	}

	private static String getDisplayFlags(int flags) {
		return Modifier.fromValue(flags).stream().map(Modifier::name).collect(Collectors.joining(", "));
	}

	private static String getThisClass(ConstantPool constPool, ClassFileInfo classFileInfo) {
		ClassInfo clsInfo = constPool.get(classFileInfo.getThisClass(), ClassInfo.class);
		return constPool.get(clsInfo.getNameIndex(), Utf8Info.class).getValue();
	}

	private static String getSuperClass(ConstantPool constPool, ClassFileInfo classFileInfo) {
		ClassInfo sclsInfo = constPool.get(classFileInfo.getSuperClass(), ClassInfo.class);
		return constPool.get(sclsInfo.getNameIndex(), Utf8Info.class).getValue();
	}

	private static String getModifier(int flags) {
		return Modifier.fromValue(flags).stream()
				.filter(x -> x != Modifier.SUPER)
				.map(Modifier::getName)
				.collect(Collectors.joining(" "));
	}

	private static String getType(int flags) {
		if (Modifier.INTERFACE.is(flags)) {
			return "interface";
		}
		return "class";
	}

	private static String getImplements(ConstantPool constPool, ClassFile classFile) {
		Interface[] intfs = classFile.getInterfaces();
		if (intfs.length == 0) {
			return "";
		}

		String impls = Arrays.stream(intfs)
				.map(x -> constPool.get(x.getClassIndex(), ClassInfo.class))
				.map(x -> ConstantPoolPrinter.getDisplayValue(constPool, x))
				.collect(Collectors.joining(", "));
		return String.format("implements %s", impls);
	}
}
