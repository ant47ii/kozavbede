package ru.kozavbede.javap.printer;

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

}
