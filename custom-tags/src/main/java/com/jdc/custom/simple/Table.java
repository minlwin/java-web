package com.jdc.custom.simple;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

public class Table<T> extends SimpleTagSupport{

	private List<T> items;
	private String var;
	
	@Override
	public void doTag() throws JspException, IOException {
		
		var out = getJspContext().getOut();
		
		out.append("""
				<table class="table table-striped">
					<thead>
						<tr>
				""");
		getJspBody().invoke(out);
		out.append("""
						</tr>
					</thead>
					<tbody>
				""");
		
		// Loop Items
		if(null != items) {
			var itr = items.iterator();
			while(itr.hasNext()) {
				out.append("""
								<tr>
						""");

				getJspContext().setAttribute(var, itr.next());
				getJspBody().invoke(out);
				
				out.append("""
								</tr>
						""");
			}
		}
		
		out.append("""
					</tbody>
				</table>
				""");
	}
	
	public void setItems(List<T> items) {
		this.items = items;
	}
	
	public void setVar(String var) {
		this.var = var;
	}
}
