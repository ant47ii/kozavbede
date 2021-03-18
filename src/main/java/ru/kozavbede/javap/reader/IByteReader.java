package ru.kozavbede.javap.reader;

import java.io.IOException;

/**
 *
 * Поддержка чтения потока байт.
 *
 */
public interface IByteReader {

	/**
	 * Прочитать 8 байта как число.
	 *
	 * @return
	 * @throws IOException
	 */
	long read8Long() throws IOException;

	/**
	 * Прочитать 4 байта как число.
	 *
	 * @return
	 * @throws IOException
	 */
	int read4Int() throws IOException;

	/**
	 * Прочитать 2 байта как число.
	 *
	 * @return
	 * @throws IOException
	 */
	int read2Int() throws IOException;

	/**
	 * Прочитать 1 байт как число.
	 *
	 * @return
	 * @throws IOException
	 */
	int read1Int() throws IOException;

	/**
	 * Прочитать n байт.
	 *
	 * @param len Количество считываемых байтов.
	 * @return
	 * @throws IOException
	 */
	byte[] readNBytes(int len) throws IOException;
}
