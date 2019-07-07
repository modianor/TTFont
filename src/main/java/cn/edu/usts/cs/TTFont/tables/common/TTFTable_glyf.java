package cn.edu.usts.cs.TTFont.tables.common;

import java.io.RandomAccessFile;
import java.util.Map;
import java.util.Vector;

import cn.edu.usts.cs.TTFont.DefaultProperties;
import cn.edu.usts.cs.TTFont.table.TTFTable;
import cn.edu.usts.cs.TTFont.table.TTFTables;
import cn.edu.usts.cs.TTFont.tables.common.objects.TTFTable_glyfGeneric;
import cn.edu.usts.cs.TTFont.tables.common.objects.TTFTable_maxpStats;
import cn.edu.usts.cs.TTFont.util.CachedFileInput;
import cn.edu.usts.cs.TTFont.util.Debug;
import cn.edu.usts.cs.TTFont.util.RandomAccessInput;

public class TTFTable_glyf extends TTFTable {
	
	public Vector<TTFTable_glyfGeneric> glyphs = new Vector<TTFTable_glyfGeneric>();
	private Map<String,TTFTable> ttfTables = null;
		
	@Override
	public boolean readFrom(RandomAccessFile ttf, int offset, int length, int checksum, Map<String,TTFTable> tables) throws Exception {
		TTFTable loca = tables.get(TTFTables.LOCA);
		if (loca==null) {
			return false; /* we need to be processed later */
		}
		
		if (DefaultProperties.USE_CACHE) {
			 /* usefull with large fonts > 1MB */
			RandomAccessInput cis = new CachedFileInput(ttf, offset);
			boolean ret = readFromCache(cis, offset, length, checksum, tables);
			cis.close();
			cis = null;
			return ret;
		}

		int offsets[] = ((TTFTable_loca)loca).offsets;
		long fp = ttf.getFilePointer();
		Debug.println("There will be "+offsets.length+" glyphs according to LOCA",this);
								
		for (int i=0; i<offsets.length-1; i++) {
			ttf.seek(fp+offsets[i]);
			TTFTable_glyfGeneric.readGlyph((RandomAccessInput)ttf, glyphs, tables,offsets[i]==offsets[i+1]);			
		}
		this.ttfTables = tables;
		return true;
	}
	
	public boolean readFromCache(RandomAccessInput ttf, int offset, int length, int checksum, Map<String,TTFTable> tables) throws Exception {
		TTFTable loca = tables.get(TTFTables.LOCA);
		int offsets[] = ((TTFTable_loca)loca).offsets;
		long fp = ttf.getFilePointer();
		Debug.println("There will be "+(offsets.length-1)+" glyphs according to cached LOCA",this);
		
		for (int i=0; i<offsets.length-1; i++) {
			ttf.seek(fp+offsets[i]);
			TTFTable_glyfGeneric.readGlyph(ttf, glyphs, tables, offsets[i]==offsets[i+1]);			
		}
		this.ttfTables = tables;
		return true;
	}

	
	
	@Override
	public boolean writeToFile(RandomAccessFile ttf, Map<String, TTFTable> tables) throws Exception {
		this.prepareWrite(ttf);
		TTFTable_loca loca = (TTFTable_loca) tables.get(TTFTables.LOCA);
		TTFTable_maxpStats maxpStats = new TTFTable_maxpStats(tables);
		
		if (loca.offsets.length!=this.glyphs.size()+1) {
			loca.offsets = new int[this.glyphs.size()+1];
		}
		for (int i=1; i < loca.offsets.length; i++) {
			TTFTable_glyfGeneric gl = this.glyphs.get(i-1);
			loca.offsets[i-1] = (int) (this.walign4(ttf) - this.my_offset);
			gl.writeToFile(ttf, tables);
			maxpStats.validate(gl);
		}
		this.walign4(ttf);
		this.finishWrite(ttf);
		loca.offsets[this.glyphs.size()] = this.my_length;
		TTFTable head = tables.get(TTFTables.HEAD);
		
		if (this.my_length >= (Short.MAX_VALUE-2)*2)
			((TTFTable_head)head).indexToLocFormat = 1;
		else
			((TTFTable_head)head).indexToLocFormat = 0;
		
		maxpStats.updateMaxp();
		return true;
	}
	
	@Override
	public boolean isViewUserFriendly() {
		return true;
	}	
}
 