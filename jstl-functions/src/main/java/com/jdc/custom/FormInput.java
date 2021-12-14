package com.jdc.custom;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.DynamicAttributes;
import jakarta.servlet.jsp.tagext.TagSupport;

public class FormInput extends TagSupport implements DynamicAttributes{

	private static final long serialVersionUID = 1L;

	protected String label;
	protected String name;
	protected String inputValue;
	
	public void setLabel(String label) {
		this.label = label;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}
	
	@Override
	public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {
		System.out.println(localName);
	}

	
}
