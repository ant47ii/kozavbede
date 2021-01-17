package ru.kozavbede.java.attributes;

public enum AttributeType {
	// @formatter:off
	CONSTANT_VALUE("ConstantValue"),
	SIGNATURE("Signature"),
	CODE("Code"),
	SOURCE_FILE("SourceFile");
	// @formatter:on

	private final String name;

	private AttributeType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static AttributeType fromName(String name) {
		for (AttributeType attr : AttributeType.values()) {
			if (attr.getName().equals(name)) {
				return attr;
			}
		}

		return null;

	}
}
