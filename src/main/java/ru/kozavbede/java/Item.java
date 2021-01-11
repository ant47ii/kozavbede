package ru.kozavbede.java;

public enum Item {
	// @formatter:off
	BYTE('B', "byte"),
	CHAR('C', "char"),
	DOUBLE('D', "double"),
	FLOAT('F', "float"),
	INT('I', "int"),
	LONG('J', "long"),
	SHORT('S', "short"),
	BOOLEAN('Z', "boolean"),
	STRING('s', "String"),
	ENUM('e', "Enum type"),
	CLASS('c', "class"),
	ANNOTATION('@', "Annotation type"),
	ARRAY('[', "Array type");
	// @formatter:on

	private final char tagItem;
	private final String type;

	private Item(char tagItem, String type) {
		this.tagItem = tagItem;
		this.type = type;
	}

	public char getTagItem() {
		return tagItem;
	}

	public String getType() {
		return type;
	}
}
