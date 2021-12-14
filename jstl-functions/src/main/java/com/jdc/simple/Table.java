package com.jdc.simple;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

public class Table<T> extends SimpleTagSupport{

	private List<T> items;
	private String var;
	
	public void setItems(List<T> items) {
		this.items = items;
	}
	
	public void setVar(String var) {
		this.var = var;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		
		// Header
		var out = getJspContext().getOut();
		out.write("""
				<table>
					<thead>
						<tr>
				""");
		getJspBody().invoke(out);
		out.write("""
						</tr>
					</thead>
					<tbody>
				""");
		if(null != items) {
			for(var item : items) {
				out.write("""
								<tr>
						""");
				getJspContext().setAttribute(var, item);
				getJspBody().invoke(getJspContext().getOut());
				out.write("""
								</tr>
						""");
			}
		}
		
		out.write("""
					</tbody>
				</table>	
				""");
	}
}
