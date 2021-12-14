package com.jdc.custom.tags;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;

public class ForEachTag<T> extends TagSupport{

	private static final long serialVersionUID = 1L;

	private List<T> items;
	private String item;
	private String title;
	
	private int index;
	
	@Override
	public int doStartTag() throws JspException {
		
		try {
			index = 0;
			pageContext.getOut().append("""
					<div class="list-group">
						<div class="list-group-item active">
							%s
						</div>	
					""".formatted(title));
			
			if(null != items && !items.isEmpty()) {
				pageContext.setAttribute(item, items.get(index ++));
				return EVAL_BODY_INCLUDE;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return SKIP_BODY;
	}
	
	@Override
	public int doAfterBody() throws JspException {
		
		if(items.size() > index) {
			pageContext.setAttribute(item, items.get(index ++));
			return EVAL_BODY_AGAIN;
		}
		return SKIP_BODY;
	}
	
	@Override
	public int doEndTag() throws JspException {
		try {
			pageContext.getOut().append("</div>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	
	public void setItems(List<T> items) {
		this.items = items;
	}
	
	public void setItem(String item) {
		this.item = item;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
