package cn.edu.usts.cs.TTFont.tables;

import cn.edu.usts.cs.TTFont.table.TTFTable;

public class TTFTable_cvt extends TTFTable {

	@Override
	public String getTableName() {
		return super.getTableName()+" "; /* wyrownanie do 4 bajtow */
	}
	

}
