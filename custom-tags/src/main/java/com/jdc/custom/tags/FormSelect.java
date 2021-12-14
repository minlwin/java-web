package com.jdc.custom.tags;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.BodyTagSupport;

public class FormSelect<T> extends BodyTagSupport{

	private static final long serialVersionUID = 1L;

	private Iterator<T> items;
	private String var;
	
	private String label;
	private String name;

	
	private static final String OPEN_TAG = """
			<div>
				<label class="form-label">%s</label>
				<select class="form-select" name="%s">
			""";
	
	private static final String CLOSE_TAG = """
				</select>
			</div>
			""";
	
	@Override
	public int doStartTag() throws JspException {
		try {
			pageContext.getOut().append(OPEN_TAG.formatted(label, name));
			
			if(null != items && items.hasNext()) {
				pageContext.setAttribute(var, items.next());
				return EVAL_BODY_BUFFERED;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	
	@Override
	public int doAfterBody() throws JspException {
		try {
			bodyContent.getEnclosingWriter().append(bodyContent.getString());
			bodyContent.clear();
			
			if(items.hasNext()) {
				pageContext.setAttribute(var, items.next());
				return EVAL_BODY_AGAIN;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	
	
	@Override
	public int doEndTag() throws JspException {
		try {
			pageContext.getOut().append(CLOSE_TAG);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doEndTag();
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setVar(String var) {
		this.var = var;
	}
	
	public void setItems(List<T> items) {
		this.items = null;
		if(null != items && !items.isEmpty()) {
			this.items = items.iterator();
		}
	}
}
