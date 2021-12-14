package com.jdc.custom.simple;

import java.io.IOException;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

public class SelectOption extends SimpleTagSupport{

	private String value;
	private String label;
	
	@Override
	public void doTag() throws JspException, IOException {
		getJspContext().getOut().append("""
					<option value="%s">%s</option>
				""".formatted(value, label));
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
