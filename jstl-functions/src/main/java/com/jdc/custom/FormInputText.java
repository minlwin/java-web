package com.jdc.custom;

import jakarta.servlet.jsp.JspException;

public class FormInputText extends FormInput {

	private static final long serialVersionUID = 1L;

	private String placeholder;

	private static final String inputElement = """
			<div>
				<label>%s</label>
				<input type="text" name="%s" value="%s" placeholder="%s" />
			</div>
			""";

	@Override
	public int doStartTag() throws JspException {

		try {
			pageContext.getOut().append(inputElement.formatted(label, name, inputValue, placeholder));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SKIP_BODY;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}


}
