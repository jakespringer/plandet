package edu.catlin.springerj.g2e.web;

import java.util.ArrayList;
import java.util.List;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractManager;

public class WebManager extends AbstractManager {
	private List<WebEntity> web;

	public WebManager() {
		super();
		
		web = new ArrayList<WebEntity>();
	}
	
	@Override
	public void initialize() {
		initialized = false;
	}

	@Override
	public void run() {
		
	}
	
	/**
	 * Adds entity to all groups that have a rule that accepts entity.
	 */
	public WebManager add(AbstractEntity ent) {
		for (WebEntity w : web) {
			w.autojoin(ent);
		}
		
		return this;
	}
	
	/**
	 * Forces entity to be added to the Web(s) that are named whatever is stored in the Web name parameter.
	 */
	public WebManager add(AbstractEntity ent, String webname) {
		for (WebEntity w : web) {
			if (w.getName().equals(webname)) add(ent, w);
		}
		
		return this;
	}
	
	/**
	 * Forces entity to be added to the Web provided.
	 */
	public AbstractEntity add(AbstractEntity ent, WebEntity we) {
		return we.add(ent);
	}
	
	public List<AbstractEntity> get(WebEntity we) {
		return we.getEntities();
	}
	
	public List<AbstractEntity> get(String name) {
		for (WebEntity w : web) {
			if (w.getName().equals(name)) return get(w);
		}
		
		return null;
	}
	
	public void register(WebEntity group) {
		web.add(group);
		
		AbstractManager prev = this, next;
		for (next = prev.getManager(); next != null; next = prev.getManager()) prev = next; // get top-level manager
		for (AbstractEntity e : prev.getEntities()) group.autojoin(e); 
	}
	
	public void remove(WebEntity group) {
		web.remove(group);
	}
	
	public void remove(String webname) {
		for (int i=0; i<web.size(); i++) {
			if (web.get(i).getName().equals(webname)) { web.remove(i); return; }
		}
	}
}
