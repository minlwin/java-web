package com.jdc.custom.tags;

import java.io.IOException;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;

public class HelloTag extends TagSupport{

	private static final long serialVersionUID = 1L;
	
	@Override
	public int doStartTag() throws JspException {
		
		try {
			pageContext.getOut().append("Hello Custom Tag");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return SKIP_BODY;
	}

}
