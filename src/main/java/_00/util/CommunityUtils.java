package _00.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;

public class CommunityUtils {
	public static Clob isToClob(InputStream is, String encoding) throws IOException, SQLException {
		Clob clob = null;
		try (InputStreamReader isr = new InputStreamReader(is, encoding);) {
			char[] c = new char[8192];
			StringBuffer buf = new StringBuffer();
			int len = 0;
			while ((len = isr.read(c)) != -1) {
				buf.append(new String(c, 0, len));
			}
			char[] ca = buf.toString().toCharArray();
			clob = new SerialClob(ca);
		}
		return clob;
	}

	public static Blob isToBlob(InputStream is, long size) throws IOException, SQLException {
		Blob blob = null;
		byte[] b = new byte[(int) size];
		is.read(b);
		blob = new SerialBlob(b);
		return blob;
	}
}
