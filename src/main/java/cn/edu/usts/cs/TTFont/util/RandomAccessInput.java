package cn.edu.usts.cs.TTFont.util;

import java.io.DataInput;
import java.io.IOException;

public interface RandomAccessInput extends DataInput {
	public long getFilePointer();
	public void seek(long where) throws IOException;
	public void close();
}
