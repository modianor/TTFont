package cn.edu.usts.cs.TTFont.gui;

import cn.edu.usts.cs.TTFont.util.Debug;
import cn.edu.usts.cs.TTFont.util.Util;

public class JGUIFrame extends GUIFrame {

	private static final long serialVersionUID = 1L;

	@Override
	protected void initialize() {
		try {
					
		}
		catch (Exception e) {
			Debug.printlnErr("Error while initializing look and feel: "+e,this);
		}		
	}
	
	public static void main(String[] args)
	{
		parseArgs(args);
		JGUIFrame gui = null;
		try {
			gui = new JGUIFrame();		
		}
		catch(Exception e) {
			Util.showException(e, gui, "Exception");
		}
	}
}
