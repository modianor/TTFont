package cn.edu.usts.cs.TTFont.tools;

import java.io.File;
import java.io.FilenameFilter;

import javax.swing.filechooser.FileFilter;

import cn.edu.usts.cs.TTFont.TTFont;

public class TTFilter extends FileFilter implements FilenameFilter {

	@Override
	public boolean accept(File f) {
		return f.getName().toLowerCase().endsWith(TTFont.TTF_EXT) || f.isDirectory();
	}

	@Override
	public String getDescription() {
		return "TrueType font file";
	}

	public boolean accept(File dir, String name) {
		return name.toLowerCase().endsWith(TTFont.TTF_EXT);
	}			
}