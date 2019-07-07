package cn.edu.usts.cs.TTFont.gui.vcontrols;

import cn.edu.usts.cs.TTFont.util.InfoException;

public class UShortValidator extends Validator {
	
	private static Validator _this = null;

	public Object validate(Object o) throws Exception {
		if (o instanceof String) {
			Integer i = null;
			try {
				i = Integer.parseInt((String)o);
			}
			catch (Exception ex) {
				throw new InfoException("Value should be in range 0 to 65536");
			}
			if (i > 65536 || i < 0)
				throw new InfoException("Value should be in range 0 to 65536");
			return i;		
		}
		else if (o instanceof Integer) {
			Integer i = (Integer) o;
			if (i > 65536 || i < 0)
				throw new InfoException("Value should be in range 0 to 65536");
			return i;
		}
		throw new InfoException("Invalid object value");		
	}
	
	synchronized public static Validator getInstance() {
		if (_this == null) {
			_this = new UShortValidator();
		}
		return _this;
	}	

}
