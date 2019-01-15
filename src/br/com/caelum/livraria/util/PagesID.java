package br.com.caelum.livraria.util;

public enum PagesID {
	
	LOGIN("/login.xhtml");

	public final String pageId;

	private PagesID(String pageId) {
		this.pageId = pageId;
	}
	
}
