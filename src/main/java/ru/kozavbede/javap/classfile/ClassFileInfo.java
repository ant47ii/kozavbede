package ru.kozavbede.javap.classfile;

import ru.kozavbede.javap.constpool.impl.ClassInfo;

/**
 *
 * Информация о файле класса.
 *
 */
public class ClassFileInfo {

	private final int accessFlags;
	private final int thisClass;
	private final int superClass;

	/**
	 * Создание информации о файле класса.
	 *
	 * @param accessFlags Права доступа.
	 * @param thisClass   Принадлежность к классу/интерфейсу.
	 * @param superClass  Суперкласс класса/интерфейса.
	 */
	public ClassFileInfo(int accessFlags, int thisClass, int superClass) {
		this.accessFlags = accessFlags;
		this.thisClass = thisClass;
		this.superClass = superClass;
	}

	/**
	 * Получить права доступа.
	 *
	 * @return Маска флагов прав доступа.
	 */
	public int getAccessFlags() {
		return accessFlags;
	}

	/**
	 * Получить принадлежность к классу/интерфейсу.
	 *
	 * @return Принадлежность классу/интерфейсу.
	 * @see ClassInfo
	 */
	public int getThisClass() {
		return thisClass;
	}

	/**
	 * Получить суперкласс класса/интерфейса.
	 *
	 * @return Суперкласс класса/интерфейса.
	 * @see ClassInfo
	 */
	public int getSuperClass() {
		return superClass;
	}
}
