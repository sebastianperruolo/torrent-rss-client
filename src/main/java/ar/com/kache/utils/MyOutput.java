package ar.com.kache.utils;

import java.io.IOException;
import java.io.PrintStream;

public class MyOutput extends PrintStream {

	private PrintStream[] streams;

	public MyOutput(PrintStream... streams) {
		super(streams[0]);

		this.streams = streams;
	}

	public void println(String s) {
		for (PrintStream ps : this.streams) {
			ps.println(s);
		}
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#println(java.lang.Object)
	 */
	@Override
	public void println(Object x) {
		for (PrintStream ps : this.streams) {
			ps.println(x);
		}
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#flush()
	 */
	@Override
	public void flush() {
		for (PrintStream ps : this.streams) {
			ps.flush();
		}
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#close()
	 */
	@Override
	public void close() {
		for (PrintStream ps : this.streams) {
			ps.close();
		}
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#print(boolean)
	 */
	@Override
	public void print(boolean b) {
		for (PrintStream ps : this.streams) {
			ps.print(b);
		}
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#print(char)
	 */
	@Override
	public void print(char c) {
		for (PrintStream ps : this.streams) {
			ps.print(c);
		}
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#print(int)
	 */
	@Override
	public void print(int i) {
		for (PrintStream ps : this.streams) {
			ps.print(i);
		}
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#print(long)
	 */
	@Override
	public void print(long l) {
		for (PrintStream ps : this.streams) {
			ps.print(l);
		}
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#print(float)
	 */
	@Override
	public void print(float f) {
		for (PrintStream ps : this.streams) {
			ps.print(f);
		}
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#print(double)
	 */
	@Override
	public void print(double d) {
		for (PrintStream ps : this.streams) {
			ps.print(d);
		}
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#print(char[])
	 */
	@Override
	public void print(char[] s) {
		for (PrintStream ps : this.streams) {
			ps.print(s);
		}
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#print(java.lang.String)
	 */
	@Override
	public void print(String s) {
		for (PrintStream ps : this.streams) {
			ps.print(s);
		}
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#print(java.lang.Object)
	 */
	@Override
	public void print(Object obj) {
		for (PrintStream ps : this.streams) {
			ps.print(obj);
		}
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#println()
	 */
	@Override
	public void println() {
		for (PrintStream ps : this.streams) {
			ps.println();
		}
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#println(boolean)
	 */
	@Override
	public void println(boolean x) {
		for (PrintStream ps : this.streams) {
			ps.println(x);
		}
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#println(char)
	 */
	@Override
	public void println(char x) {
		for (PrintStream ps : this.streams) {
			ps.println(x);
		}
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#println(int)
	 */
	@Override
	public void println(int x) {
		for (PrintStream ps : this.streams) {
			ps.println(x);
		}
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#println(long)
	 */
	@Override
	public void println(long x) {
		for (PrintStream ps : this.streams) {
			ps.println(x);
		}
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#println(float)
	 */
	@Override
	public void println(float x) {
		for (PrintStream ps : this.streams) {
			ps.println(x);
		}
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#println(double)
	 */
	@Override
	public void println(double x) {
		for (PrintStream ps : this.streams) {
			ps.println(x);
		}
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#println(char[])
	 */
	@Override
	public void println(char[] x) {
		for (PrintStream ps : this.streams) {
			ps.println(x);
		}
	}

	/* (non-Javadoc)
	 * @see java.io.FilterOutputStream#write(byte[])
	 */
	@Override
	public void write(byte[] b) throws IOException {
		for (PrintStream ps : this.streams) {
			ps.println(b);
		}
	}

	/*
	 * overwrite all other methods
	 */
	
}
