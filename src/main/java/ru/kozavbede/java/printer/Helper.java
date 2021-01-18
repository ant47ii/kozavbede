package ru.kozavbede.java.printer;

import ru.kozavbede.java.constpool.ConstantPool;
import ru.kozavbede.java.constpool.impl.ClassInfo;
import ru.kozavbede.java.constpool.impl.NameAndTypeInfo;
import ru.kozavbede.java.constpool.impl.Utf8Info;

public class Helper {

	private Helper() {

	}

	public static void println(String s, Object... args) {
		if (args.length > 0) {
			System.out.println(String.format(s, args));
		} else {
			System.out.println(s);
		}
	}

	public static void print(String s, Object... args) {
		if (args.length > 0) {
			System.out.print(String.format(s, args));
		} else {
			System.out.print(s);
		}
	}

	public static Utf8Info getUtf8Info(ConstantPool constPool, int index) {
		return constPool.get(index, Utf8Info.class);
	}

	public static ClassInfo getClassInfoInfo(ConstantPool constPool, int index) {
		return constPool.get(index, ClassInfo.class);
	}

	public static NameAndTypeInfo getNameAndTypeInfo(ConstantPool constPool, int index) {
		return constPool.get(index, NameAndTypeInfo.class);
	}
}
