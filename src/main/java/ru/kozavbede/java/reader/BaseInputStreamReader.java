package ru.kozavbede.java.reader;

import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * Базовая реализация чтения {@code InputStream}.
 *
 */
public abstract class BaseInputStreamReader implements IByteReader {

	protected final InputStream is;

	protected BaseInputStreamReader(InputStream is) {
		this.is = is;
	}

	@Override
	public long read8Long() throws IOException {
		byte[] bytes = readNBytes(8);
		return readLong(bytes);
	}

	@Override
	public int read4Int() throws IOException {
		byte[] bytes = readNBytes(4);
		return read(bytes);
	}

	@Override
	public int read2Int() throws IOException {
		byte[] bytes = readNBytes(2);
		return read(bytes);
	}

	@Override
	public int read1Int() throws IOException {
		byte[] bytes = readNBytes(1);
		return read(bytes);
	}

	@Override
	public byte[] readNBytes(int len) throws IOException {
		return is.readNBytes(len);
	}

	private int read(byte[] bytes) {
		int result = 0;
		for (int i = 0; i < bytes.length; i++) {
			result |= readInt(bytes[bytes.length - i - 1], i * 8);
		}
		return result;
	}

	private long readLong(byte[] bytes) {
		long result = 0;
		for (int i = 0; i < bytes.length; i++) {
			result |= readLong(bytes[bytes.length - i - 1], i * 8);
		}
		return result;
	}

	private int readInt(byte b, int position) {
		return Byte.toUnsignedInt(b) << position;
	}

	private long readLong(byte b, int position) {
		return Byte.toUnsignedLong(b) << position;
	}
}
