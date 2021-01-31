package ru.kozavbede.javap.attributes.impl;

import ru.kozavbede.javap.attributes.BaseAttribute;
import ru.kozavbede.javap.attributes.IAttribute;

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
