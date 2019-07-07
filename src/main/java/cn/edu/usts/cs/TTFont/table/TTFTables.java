package cn.edu.usts.cs.TTFont.table;

import java.util.Map;
import java.util.TreeMap;

import cn.edu.usts.cs.TTFont.tables.TTFTable_DSIG;
import cn.edu.usts.cs.TTFont.tables.TTFTable_EBSC;
import cn.edu.usts.cs.TTFont.tables.TTFTable_LTSH;
import cn.edu.usts.cs.TTFont.tables.TTFTable_VDMX;
import cn.edu.usts.cs.TTFont.tables.TTFTable_Zapf;
import cn.edu.usts.cs.TTFont.tables.TTFTable_acnt;
import cn.edu.usts.cs.TTFont.tables.TTFTable_avar;
import cn.edu.usts.cs.TTFont.tables.TTFTable_bdat;
import cn.edu.usts.cs.TTFont.tables.TTFTable_bhed;
import cn.edu.usts.cs.TTFont.tables.TTFTable_bloc;
import cn.edu.usts.cs.TTFont.tables.TTFTable_bsln;
import cn.edu.usts.cs.TTFont.tables.TTFTable_cvar;
import cn.edu.usts.cs.TTFont.tables.TTFTable_cvt;
import cn.edu.usts.cs.TTFont.tables.TTFTable_fdsc;
import cn.edu.usts.cs.TTFont.tables.TTFTable_feat;
import cn.edu.usts.cs.TTFont.tables.TTFTable_fmtx;
import cn.edu.usts.cs.TTFont.tables.TTFTable_fpgm;
import cn.edu.usts.cs.TTFont.tables.TTFTable_fvar;
import cn.edu.usts.cs.TTFont.tables.TTFTable_gasp;
import cn.edu.usts.cs.TTFont.tables.TTFTable_gvar;
import cn.edu.usts.cs.TTFont.tables.TTFTable_hdmx;
import cn.edu.usts.cs.TTFont.tables.TTFTable_hsty;
import cn.edu.usts.cs.TTFont.tables.TTFTable_just;
import cn.edu.usts.cs.TTFont.tables.TTFTable_kern;
import cn.edu.usts.cs.TTFont.tables.TTFTable_lcar;
import cn.edu.usts.cs.TTFont.tables.TTFTable_mort;
import cn.edu.usts.cs.TTFont.tables.TTFTable_morx;
import cn.edu.usts.cs.TTFont.tables.TTFTable_opbd;
import cn.edu.usts.cs.TTFont.tables.TTFTable_prep;
import cn.edu.usts.cs.TTFont.tables.TTFTable_prop;
import cn.edu.usts.cs.TTFont.tables.TTFTable_trak;
import cn.edu.usts.cs.TTFont.tables.TTFTable_vhea;
import cn.edu.usts.cs.TTFont.tables.TTFTable_vmtx;
import cn.edu.usts.cs.TTFont.tables.common.TTFTable_OS_2;
import cn.edu.usts.cs.TTFont.tables.common.TTFTable_cmap;
import cn.edu.usts.cs.TTFont.tables.common.TTFTable_glyf;
import cn.edu.usts.cs.TTFont.tables.common.TTFTable_head;
import cn.edu.usts.cs.TTFont.tables.common.TTFTable_hhea;
import cn.edu.usts.cs.TTFont.tables.common.TTFTable_hmtx;
import cn.edu.usts.cs.TTFont.tables.common.TTFTable_loca;
import cn.edu.usts.cs.TTFont.tables.common.TTFTable_maxp;
import cn.edu.usts.cs.TTFont.tables.common.TTFTable_name;
import cn.edu.usts.cs.TTFont.tables.common.TTFTable_post;

public final class TTFTables {
	public final static String ACNT = "acnt"; /* accent attachment */
	public final static String AVAR = "avar"; /* axis variation */
	public final static String BDAT = "bdat"; /* bitmap data */
	public final static String BHED = "bhed"; /* bitmap font header */
	public final static String BLOC = "bloc"; /* bitmap location */
	public final static String BSLN = "bsln"; /* baseline */
	public final static String CMAP = "cmap"; /* character code mapping */
	public final static String CVAR = "cvar"; /* CVT variation */
	public final static String CVT  = "cvt "; /* control value table */
	public final static String DSIG = "DSIG"; /* digital signature */
	public final static String EBSC = "EBSC"; /* embedded bitmap scaling control */
	public final static String FDSC = "fdsc"; /* font descriptor */
	public final static String FEAT = "feat"; /* layout feature */
	public final static String FMTX = "fmtx"; /* font metrics */
	public final static String FPGM = "fpgm"; /* font program */
	public final static String FVAR = "fvar"; /* font variation */
	public final static String GASP = "gasp"; /* grid-fitting and scan-conversion procedure */
	public final static String GLYF = "glyf"; /* glyph outline */
	public final static String GVAR = "gvar"; /* glyph variation */
	public final static String HDMX = "hdmx"; /* horizontal device metrics */
	public final static String HEAD = "head"; /* font header */
	public final static String HHEA = "hhea"; /* horizontal header */
	public final static String HMTX = "hmtx"; /* horizontal metrics */
	public final static String HSTY = "hsty"; /* horizontal style */
	public final static String JUST = "just"; /* justification */
	public final static String KERN = "kern"; /* kerning */
	public final static String LCAR = "lcar"; /* ligature caret */
	public final static String LOCA = "loca"; /* glyph location */
	public final static String MAXP = "maxp"; /* maximum profile */
	public final static String MORT = "mort"; /* metamorphosis */
	public final static String MORX = "morx"; /* extended metamorphosis */
	public final static String NAME = "name"; /* name */
	public final static String OPDB	= "opbd"; /* optical bounds */
	public final static String OS_2 = "OS/2"; /* compatibility */
	public final static String POST = "post"; /* glyph name and PostScript compatibility */
	public final static String PREP = "prep"; /* control value program */
	public final static String PROP = "prop"; /* properties */
	public final static String TRAK = "trak"; /* tracking */
	public final static String VHEA = "vhea"; /* vertical header */
	public final static String VMTX = "vmtx"; /* vertical metrics */
	public final static String ZAPF = "Zapf"; /* glyph reference */
	public final static String LTSH = "LTSH"; /* MS Linear Threshold */
	public final static String VDMX = "VDMX"; /* MS Vertical Device Metrics */
	
	public final static String OFFSET_SUBTABLE = "TTFOffsetSubtable";
	public final static String DIRECTORY_ENTRY = "TTFDirEntry";
	
	private final static String[] TABS_REQUIRED = { CMAP, GLYF, HEAD, HHEA, HMTX,
		   LOCA, MAXP, NAME, POST, OS_2 };
	/* Windows needs OS_2, but OSX doesn't */

	public final static int PADDING = 3;
	private static TreeMap<String,Class> TTF_TABLES = new TreeMap<String,Class>();
	
	static {
		TTF_TABLES.put(ACNT, TTFTable_acnt.class);
		TTF_TABLES.put(AVAR, TTFTable_avar.class);
		TTF_TABLES.put(BDAT, TTFTable_bdat.class);
		TTF_TABLES.put(BHED, TTFTable_bhed.class);
		TTF_TABLES.put(BLOC, TTFTable_bloc.class);
		TTF_TABLES.put(BSLN, TTFTable_bsln.class);
		TTF_TABLES.put(CMAP, TTFTable_cmap.class);
		TTF_TABLES.put(CVAR, TTFTable_cvar.class);
		TTF_TABLES.put(CVT,  TTFTable_cvt.class);
		TTF_TABLES.put(DSIG, TTFTable_DSIG.class);
		TTF_TABLES.put(EBSC, TTFTable_EBSC.class);
		TTF_TABLES.put(FDSC, TTFTable_fdsc.class);
		TTF_TABLES.put(FEAT, TTFTable_feat.class);
		TTF_TABLES.put(FMTX, TTFTable_fmtx.class);
		TTF_TABLES.put(FPGM, TTFTable_fpgm.class);
		TTF_TABLES.put(FVAR, TTFTable_fvar.class);
		TTF_TABLES.put(GASP, TTFTable_gasp.class);
		TTF_TABLES.put(GLYF, TTFTable_glyf.class);
		TTF_TABLES.put(GVAR, TTFTable_gvar.class);
		TTF_TABLES.put(HDMX, TTFTable_hdmx.class);
		TTF_TABLES.put(HEAD, TTFTable_head.class);
		TTF_TABLES.put(HHEA, TTFTable_hhea.class);
		TTF_TABLES.put(HMTX, TTFTable_hmtx.class);
		TTF_TABLES.put(HSTY, TTFTable_hsty.class);
		TTF_TABLES.put(JUST, TTFTable_just.class);
		TTF_TABLES.put(KERN, TTFTable_kern.class);
		TTF_TABLES.put(LCAR, TTFTable_lcar.class);
		TTF_TABLES.put(LOCA, TTFTable_loca.class);
		TTF_TABLES.put(MAXP, TTFTable_maxp.class);
		TTF_TABLES.put(MORT, TTFTable_mort.class);
		TTF_TABLES.put(MORX, TTFTable_morx.class);
		TTF_TABLES.put(NAME, TTFTable_name.class);
		TTF_TABLES.put(OPDB, TTFTable_opbd.class);
		TTF_TABLES.put(OS_2, TTFTable_OS_2.class);
		TTF_TABLES.put(POST, TTFTable_post.class);
		TTF_TABLES.put(PREP, TTFTable_prep.class);
		TTF_TABLES.put(PROP, TTFTable_prop.class);
		TTF_TABLES.put(TRAK, TTFTable_trak.class);
		TTF_TABLES.put(VHEA, TTFTable_vhea.class);
		TTF_TABLES.put(VMTX, TTFTable_vmtx.class);
		TTF_TABLES.put(ZAPF, TTFTable_Zapf.class);
		TTF_TABLES.put(LTSH, TTFTable_LTSH.class);
		TTF_TABLES.put(VDMX, TTFTable_VDMX.class);
		
		TTF_TABLES.put(OFFSET_SUBTABLE, TTFOffsetSubtable.class);
		TTF_TABLES.put(DIRECTORY_ENTRY, TTFDirEntry.class);
	}
	
	/**
	 * Return class for given table name. Used when creating new class instance
	 * @param name Name of table - constant from TTFTables
	 * @return Class matching given table name (may be null)
	 */
	public static Class getTableClass(String name) {
		return TTF_TABLES.get(name);
	}
	
	public static void checkRequiredTables(Map<String,TTFTable> tables) throws Exception {
		for (String name : TABS_REQUIRED) {
			if (tables.get(name)==null) {
				throw new Exception("Missing table "+name+"!");
			}
		}
	}
}
