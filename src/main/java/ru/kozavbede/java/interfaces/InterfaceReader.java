package ru.kozavbede.java.interfaces;

import java.io.IOException;
import java.io.InputStream;

import ru.kozavbede.java.reader.BaseInputStreamReader;

public class InterfaceReader extends BaseInputStreamReader<Interface[]> {

	public InterfaceReader(InputStream is) {
		super(is);
	}

	@Override
	public Interface[] read() throws IOException {
		int count = read2Int();
		if (count > 0) {
			Interface[] interfaces = new Interface[count];
			for (int i = 0; i < count; i++) {
				int classIndex = read2Int();
				interfaces[i] = new Interface(classIndex);
			}
			return interfaces;
		}

		return new Interface[0];
	}

}
