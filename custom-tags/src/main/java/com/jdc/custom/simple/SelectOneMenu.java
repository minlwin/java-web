package com.jdc.custom.simple;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

public class SelectOneMenu<T> extends SimpleTagSupport {

	private List<T> items;
	private String var;

	private String name;
	private String label;
	
	@Override
	public void doTag() throws JspException, IOException {
		
		var out = getJspContext().getOut();
		out.append("""
				<div>
					<label class="form-label">%s</label>
					<select class="form-select" name="%s">
				""".formatted(label, name));
		
		if(null != items && !items.isEmpty()) {
			var iterator = items.iterator();
			
			while(iterator.hasNext()) {
				getJspContext().setAttribute(var, iterator.next());
				getJspBody().invoke(out);
			}
			
		}
		
		out.append("""
					</select>
				</div>	
				""");
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
