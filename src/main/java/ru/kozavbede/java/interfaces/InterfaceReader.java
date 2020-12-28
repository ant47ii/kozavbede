package ru.kozavbede.java.interfaces;

import java.io.IOException;
import java.io.InputStream;

import ru.kozavbede.java.reader.MultiInputStreamReader;

public class InterfaceReader extends MultiInputStreamReader<Interface[]> {

	public InterfaceReader(InputStream is) {
		super(is);
	}

	@Override
	public Interface[] read(int interfaceCount) throws IOException {
		Interface[] interfaces = new Interface[interfaceCount];
		for (int i = 0; i < interfaceCount; i++) {
			int classIndex = read2Int();
			interfaces[i] = new Interface(classIndex);
		}
		return interfaces;
	}

}
