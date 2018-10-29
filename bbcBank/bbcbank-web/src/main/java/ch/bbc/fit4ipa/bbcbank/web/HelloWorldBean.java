package ch.bbc.fit4ipa.bbcbank.web;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class HelloWorldBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public String getGreeting() {
		return "Hello World";
	}
	
}