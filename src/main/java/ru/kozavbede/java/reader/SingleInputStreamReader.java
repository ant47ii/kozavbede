package ru.kozavbede.java.reader;

import java.io.IOException;
import java.io.InputStream;

public abstract class SingleInputStreamReader<T> extends BaseInputStreamReader {

	protected SingleInputStreamReader(InputStream is) {
		super(is);
	}

	public abstract T read() throws IOException;

}
