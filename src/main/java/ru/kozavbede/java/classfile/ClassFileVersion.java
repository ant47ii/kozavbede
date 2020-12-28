package ru.kozavbede.java.classfile;

public class ClassFileVersion {

	private final int minorVersion;
	private final int majorVersion;

	public ClassFileVersion(int minorVersion, int majorVersion) {
		this.minorVersion = minorVersion;
		this.majorVersion = majorVersion;
	}

	public int getMinorVersion() {
		return minorVersion;
	}

	public int getMajorVersion() {
		return majorVersion;
	}
}
