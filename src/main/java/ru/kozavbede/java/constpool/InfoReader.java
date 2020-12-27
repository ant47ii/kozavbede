package ru.kozavbede.java.constpool;

import java.io.IOException;
import java.io.InputStream;

import ru.kozavbede.java.reader.BaseInputStreamReader;

public class InfoReader extends BaseInputStreamReader<IInfo[]> {

	private final InfoBuilder builder;

	public InfoReader(InputStream is, InfoBuilder builder) {
		super(is);
		this.builder = builder;
	}

	@Override
	public IInfo[] read() throws IOException {
		int constantPoolCount = read2Int();
		if (constantPoolCount > 0) {
			return readInfo(constantPoolCount);
		}

		return new IInfo[0];
	}

	private IInfo[] readInfo(int constantPoolCount) throws IOException {
		IInfo[] infos = new IInfo[constantPoolCount - 1];
		for (int i = 0; i < constantPoolCount - 1; i++) {
			int infoTagType = read1Int();
			Tag tag = Tag.fromIndex(infoTagType);
			if (tag == null) {
				// TODO: throw ex...
			}

			infos[i] = builder.buildTag(tag, i + 1, this);
			if (tag == Tag.LONG || tag == Tag.DOUBLE) {
				// then the next usable item in the pool is located at index n+2. The
				// constant_pool index n+1 must be valid but is considered unusable.
				i++;
			}
		}
		return infos;
	}

}
