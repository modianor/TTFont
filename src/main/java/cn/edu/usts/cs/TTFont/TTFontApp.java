package cn.edu.usts.cs.TTFont;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.HashSet;
import java.util.Set;

import cn.edu.usts.cs.TTFont.tables.common.TTFTable_cmap;
import cn.edu.usts.cs.TTFont.tables.common.objects.TTFTable_cmapFormat12;
import cn.edu.usts.cs.TTFont.tables.common.objects.TTFTable_cmapFormat12.Group;
import cn.edu.usts.cs.TTFont.tables.common.objects.TTFTable_cmapSub;

/**
 * Hello world!
 *
 */
public class TTFontApp {

	private TTFTable_cmapFormat12 cmap;
	private Set<Long> MIN_MAX = new HashSet<Long>();

	public TTFontApp(String str) throws IOException, Exception {
		cmap = getMappingrelation(str);
	}

	public String cleanData(String str) throws Exception {
		StringBuffer sb = new StringBuffer(str);
		for (int i = 0; i < sb.length(); i++) {
			long r = (long) (sb.charAt(i));
			if (MIN_MAX.contains(r)) {
				Integer n = getRealNumber(sb.charAt(i) + "");
				sb.setCharAt(i, (n + "").charAt(0));
			}
		}
		return sb.toString();
	}

	private TTFTable_cmapFormat12 getMappingrelation(String str) throws IOException, Exception {
		File temp_ttf_file = string2ttf(str);
		TTFont ttf = new TTFont(temp_ttf_file, "r");
		TTFTable_cmap cmap = (TTFTable_cmap) ttf.getTable("cmap");
		TTFTable_cmapSub[] subtables = cmap.subtables;
		TTFTable_cmapFormat12 cmapFormat1 = (TTFTable_cmapFormat12) subtables[1].cmapFormat;
		Group[] groups = cmapFormat1.groups;

		for (int i = 0; i < groups.length; i++) {
			MIN_MAX.add(groups[i].startCharCode);
		}

		return cmapFormat1;
	}

	private Integer getRealNumber(String price) throws Exception {
		String real_num = "";
		for (int i = 0; i < price.length(); i++) {
			int ch = price.charAt(i);
			int n = cmap.getGlyphIdForChar(ch) - 1;
			real_num += n;
		}

		return Integer.parseInt(real_num);

	}

	private static File string2ttf(String str) throws IOException {
		Decoder decoder = Base64.getDecoder();
		byte[] decode = decoder.decode(str);
		File ttf = File.createTempFile("58_font", ".ttf");
		FileOutputStream fos = new FileOutputStream(ttf);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		bos.write(decode);
		bos.close();
		fos.close();
		return ttf;
	}

	public Set<Long> getMIN_MAX() {
		return MIN_MAX;
	}
}
