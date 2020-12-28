package ru.kozavbede.java.classfile;

/**
 * 
 * Информация о версии файла класса.
 *
 */
public class ClassFileVersion {

	private final int minorVersion;
	private final int majorVersion;

	/**
	 * Создание новой информации о версии файла класса.
	 * 
	 * @param minorVersion Второстепенная версия файла класса.
	 * @param majorVersion Основная версия файла класса.
	 */
	public ClassFileVersion(int minorVersion, int majorVersion) {
		this.minorVersion = minorVersion;
		this.majorVersion = majorVersion;
	}

	/**
	 * Получить второстепенную версию файла класса.
	 * 
	 * @return Второстепенная версия файла класса.
	 */
	public int getMinorVersion() {
		return minorVersion;
	}

	/**
	 * Получить основную версию файла класса.
	 * 
	 * @return Основная версия файла класса.
	 */
	public int getMajorVersion() {
		return majorVersion;
	}
}
