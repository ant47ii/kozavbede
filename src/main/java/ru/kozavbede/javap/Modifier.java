package ru.kozavbede.javap;

import java.util.EnumSet;
import java.util.Set;

public enum Modifier {
	// @formatter:off
	PUBLIC("public", 0x0001),
	PRIVATE("private", 0x0002),
	PROTECTED("protected", 0x0004),
	STATIC("static", 0x0008),
	FINAL("final", 0x0010),
	SUPER("super", 0x0020),
	VOLATILE("volatile", 0x0040),
	TRANSIENT("transient", 0x0080),
	INTERFACE("interface", 0x0200),
	ABSTRACT("abstract", 0x0400),
	SYNTHETIC("synthetic", 0x1000),
	ANNOTATION("annotation", 0x2000),
	ENUM("enum", 0x4000);
	// @formatter:on

	private final String name;
	private final int value;

	Modifier(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}

	public boolean is(int value) {
		return (value & this.value) != 0;
	}

	public static Set<Modifier> fromValue(int value) {
		EnumSet<Modifier> result = EnumSet.noneOf(Modifier.class);
		for (Modifier modifier : Modifier.values()) {
			if (modifier.is(value)) {
				result.add(modifier);
			}
		}

		return result;
	}

}