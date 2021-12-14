package com.jdc.custom.tags;

import java.io.IOException;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;

public class H1Tag extends TagSupport{

	private static final long serialVersionUID = 1L;
	
	private String value;
	
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			pageContext.getOut().append("<h1>%s</h1>".formatted(value));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
}
