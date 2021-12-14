package com.jdc.custom.simple;

import java.io.IOException;
import java.io.StringWriter;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

public class TableColumn extends SimpleTagSupport{

	private String name;
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		var bodyContent = new StringWriter();
		getJspBody().invoke(bodyContent);
		
		if(bodyContent.getBuffer().isEmpty()) {
			getJspContext().getOut().append("<th>").append(name).append("</th>");
		} else {
			getJspContext().getOut().append("<td>").append(bodyContent.getBuffer()).append("</td>");
		}
	}
}
