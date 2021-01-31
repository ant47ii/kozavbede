package ru.kozavbede.javap.interfaces;

import java.io.IOException;
import java.io.InputStream;

import ru.kozavbede.javap.reader.MultiInputStreamReader;

public class InterfaceReader extends MultiInputStreamReader<Interface[]> {

	private InterfaceReader(InputStream is) {
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

	public static class Builder {

		private Builder() {

		}

		public static InterfaceReader from(InputStream is) {
			return new InterfaceReader(is);
		}

	}

}
