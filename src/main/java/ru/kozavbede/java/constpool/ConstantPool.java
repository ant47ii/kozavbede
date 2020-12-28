package ru.kozavbede.java.constpool;

import java.util.Iterator;

/**
 * 
 * Пул констант.
 *
 */
public class ConstantPool implements Iterable<IConstantPoolRow> {

	private final IConstantPoolRow[] pool;

	public ConstantPool(IConstantPoolRow[] pool) {
		this.pool = pool;
	}

	/**
	 * Получить значения пула констант.
	 * 
	 * @return Массив записей пула констант.
	 */
	public IConstantPoolRow[] getPool() {
		return pool;
	}

	@Override
	public Iterator<IConstantPoolRow> iterator() {
		return new Itr();
	}

	public <T extends IConstantPoolRow> T get(int index, Class<T> type) {
		return type.cast(pool[index - 1]);
	}

	private class Itr implements Iterator<IConstantPoolRow> {
		int cursor = 0;

		@Override
		public boolean hasNext() {
			return cursor < pool.length;
		}

		@Override
		public IConstantPoolRow next() {
			return pool[cursor++];
		}
	}

}
