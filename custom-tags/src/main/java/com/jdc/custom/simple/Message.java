package com.jdc.custom.simple;

import java.io.IOException;
import java.io.StringWriter;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

public class Message extends SimpleTagSupport{
	
	private String message;
	
	private static final String TAG = """
			<div class="alert alert-primary">
				%s
				
				<div class="pt-2">
					%s
				</div>
			</div>	
			""";
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		
		var bodyContent = new StringWriter();
		getJspBody().invoke(bodyContent);
		
		System.out.println(bodyContent.getBuffer());
		
		getJspContext().getOut().append(TAG.formatted(message, bodyContent.getBuffer()));
		
	}

}
