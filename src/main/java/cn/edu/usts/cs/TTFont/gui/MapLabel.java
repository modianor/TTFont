package cn.edu.usts.cs.TTFont.gui;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import cn.edu.usts.cs.TTFont.tables.common.objects.GlyphFactory;
import cn.edu.usts.cs.TTFont.tables.common.objects.TTFTable_cmapFormat;
import cn.edu.usts.cs.TTFont.tables.common.objects.TTFTable_cmapMap;
import cn.edu.usts.cs.TTFont.tables.common.objects.TTFTable_glyfComposite;
import cn.edu.usts.cs.TTFont.tables.common.objects.TTFTable_glyfGeneric;
import cn.edu.usts.cs.TTFont.tables.common.objects.TTFTable_glyfSimple;
import cn.edu.usts.cs.TTFont.util.Debug;
import cn.edu.usts.cs.TTFont.util.Util;

public class MapLabel extends JLabel implements MouseListener {

	private static final long serialVersionUID = 1L;
	private final static String ADD_MAPPING = " <a href=#>[add mapping]</a>";
	private TTFTable_cmapMap map;
	private TTFTable_cmapFormat cmapFormat;
	private Font font = null;
	
	public MapLabel(Font f, TTFTable_cmapMap _map, TTFTable_cmapFormat _format) {
		this.map = _map;
		this.cmapFormat = _format;
		this.font = f;
		updateText();
	}
	
	public void updateText() {
		int gid = cmapFormat.getGlyphIdForChar(map.ch);
		String info = "'"+map.ch+"' represented by glyph id="+gid;
		if (gid==0) {
			info += ADD_MAPPING;
			this.addMouseListener(this);				
		}
		else {
			this.removeMouseListener(this);
		}
		setText("<html>"+info+"</html>");
	}
	
	public void mouseClicked(MouseEvent e) {
		try {
			int c1 = cmapFormat.getGlyphIdForChar(map.c1);
			int c2 = cmapFormat.getGlyphIdForChar(map.c2);
			Debug.println("Trying to create new glyph from "+map.c1+" ("+c1+") and "+c2,this);
			
			if (c1>0 && c2>0) {
				int[] cs = { c1, c2, map.flags };
				TTFTable_glyfComposite gl = GlyphFactory.createCompositeGlyph(font,cmapFormat.ttfTables, map.name, cs, true);
				if (gl!=null)
					cmapFormat.injectGlyphMapping(map.ch, gl.getIndex());				
			}
			else if (c1>0) {
				TTFTable_glyfGeneric gl = GlyphFactory.createGlyph(font,cmapFormat.ttfTables, map.name, c1, true);
				if (gl!=null)
					cmapFormat.injectGlyphMapping(map.ch, gl.getIndex());
			}
			else {
				TTFTable_glyfSimple gl = GlyphFactory.createSimpleGlyph(font,cmapFormat.ttfTables, map.name, true);
				if (gl!=null)
					cmapFormat.injectGlyphMapping(map.ch, gl.getIndex());
			}
		}
		catch (Exception ex) {
			Util.showException(ex, null, "Error while adding new mapping");
		}
		this.updateText();
	}

	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
