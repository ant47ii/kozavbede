package ru.kozavbede.java.attributes.impl;

import ru.kozavbede.java.attributes.BaseAttribute;
import ru.kozavbede.java.attributes.IAttribute;

public class SignatureAttribute extends BaseAttribute {

	private final int signatureIndex;

	public SignatureAttribute(IAttribute base, int signatureIndex) {
		super(base.getAttributeNameIndex(), base.getType());
		this.signatureIndex = signatureIndex;
	}

	public int getSignatureIndex() {
		return signatureIndex;
	}

}
