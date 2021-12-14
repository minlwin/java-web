package com.jdc.custom.tags;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.DynamicAttributes;
import jakarta.servlet.jsp.tagext.TagSupport;

public class FormInputText extends TagSupport implements DynamicAttributes{

	private static final long serialVersionUID = 1L;

	private String name;
	private String value;
	private String label;
	private String placeholder;
	
	private Map<String, Object> values;
	
	public FormInputText() {
		values = new HashMap<>();
	}
	
	private static final String FORM_INPUT = """
			<div>
				<label class="form-label">%s</label>
				<input type="text" name="%s" value="%s" placeholder="%s" class="form-control" />
			</div>
			""";
	
	@Override
	public int doStartTag() throws JspException {
		try {
			pageContext.getOut().append(FORM_INPUT.formatted(label, name, value, placeholder));
			
			for(var entry : values.entrySet()) {
				System.out.println("%s : %s".formatted(entry.getKey(), entry.getValue()));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	@Override
	public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {
		values.put(localName, value);
	}


}
