package br.com.caelum.livraria.util;

import java.util.Map;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.caelum.livraria.model.Usuario;

public class Autorizador implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		
		FacesContext context = event.getFacesContext();
		String pageName = context.getViewRoot().getViewId();
		
		if (!pageName.equals(PagesID.LOGIN.pageId)) {
			Usuario user = getUserFromSession(context);
			if (user == null) {
				redirectToLoginPage(context);
			}
		}
		
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
	
	private void redirectToLoginPage(FacesContext context) {
		NavigationHandler handler = context.getApplication().getNavigationHandler();
		handler.handleNavigation(context, null, "login?faces-redirect=true");
		context.renderResponse();
	}

	private Usuario getUserFromSession(FacesContext context) {
		Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
		Usuario user = (Usuario) sessionMap.get("user");
		return user;
	}

}
