package com.jdc.custom;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.jsp.JspException;

public class FormInputSelect extends FormInput{

	private static final long serialVersionUID = 1L;
	
	private List<String> items;
	private int index;
	
	public void setItems(List<String> items) {
		this.items = items;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			pageContext.getOut().write("<select>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}
	
	@Override
	public int doAfterBody() throws JspException {
		pageContext.setAttribute("option", "<option>%s</option>".formatted(items.get(index)));
		return items.size() > ++ index ? EVAL_BODY_AGAIN : SKIP_BODY;
	}
	
	@Override
	public int doEndTag() throws JspException {
		try {
			pageContext.getOut().write("</select>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
}
