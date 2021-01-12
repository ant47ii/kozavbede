package ru.kozavbede.java.fields;

public enum FieldType {
	// @formatter:off
	BYTE('B', "byte"),
	CHAR('C', "char"),
	DOUBLE('D', "double"),
	FLOAT('F', "float"),
	INT('I', "int"),
	LONG('J', "long"),
	CLASS_NAME('L', "class"),
	SHORT('S', "short"),
	BOOLEAN('Z', "boolean"),
	ARRAY('[', "Array type");
	// @formatter:on

	private final char term;
	private final String type;

	private FieldType(char term, String type) {
		this.term = term;
		this.type = type;
	}

	public char getTerm() {
		return term;
	}

	public String getType() {
		return type;
	}
}
