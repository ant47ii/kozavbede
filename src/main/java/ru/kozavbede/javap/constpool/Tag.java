package ru.kozavbede.javap.constpool;

/**
 *
 * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4
 *
 */
public enum Tag {

	UTF8("Utf8", 1),
	INTEGER("Integer", 3),
	FLOAT("Float", 4),
	LONG("Long", 5),
	DOUBLE("Double", 6),
	CLASS("Class", 7),
	STRING("String", 8),
	FIELD_REF("Fieldref", 9),
	METHOD_REF("Methodref", 10),
	INTERFACE_METHOD_REF("InterfaceMethodref", 11),
	NAME_AND_TYPE("NameAndType", 12),
	//METHOD_HANDLE("MethodHandle", 15),
	//METHOD_TYPE("MethodType", 16),
	//INVOKE_DYNAMIC("InvokeDynamic", 18)
	;

	private final String name;
	private final int index;

	Tag(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public String getDisplayName() {
		return name;
	}

	public static Tag fromIndex(int index) {
		for(Tag value : values()) {
			if(value.index == index) {
				return value;
			}
		}

		return null;
	}
}
