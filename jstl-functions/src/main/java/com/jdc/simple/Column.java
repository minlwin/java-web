package com.jdc.simple;

import java.io.IOException;
import java.io.StringWriter;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

public class Column extends SimpleTagSupport{

	private String name;
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		
		var content = new StringWriter();
		getJspBody().invoke(content);
		var buffer = new StringBuffer();
		
		if(content.getBuffer().length() > 0) {
			buffer.append("<td>").append(content.getBuffer()).append("</td>");
		} else {
			buffer.append("<th>").append(name).append("</th>");
		}
		
		getJspContext().getOut().append(buffer.toString());
	}

}
