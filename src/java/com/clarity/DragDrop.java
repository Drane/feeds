package com.clarity;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.gnu.stealthp.rsslib.RSSItem;

@Named
@SessionScoped
public class DragDrop implements Serializable {
	@Inject private RSSFeed rssFeed;

	public DragDrop() {
	}
	
	public String getPayload() {
		// this method sends a string, that's a concatenation
		// of the saved item's titles, back to the client
		LinkedList<RSSItem> savedItems = rssFeed.getSavedItems();
		Iterator<RSSItem> it = savedItems.iterator();
		String s = "";
		
		while (it.hasNext()) {
			RSSItem item = it.next();
			s += item.getTitle() + " | ";
		}
		return s;
	}
	
	public void setPayload(String payload) {
		// payload was set in the drop event listener for the 
		// h5:drop component in /sections/feeds/menuLeft.xhtml
		StringTokenizer st = new StringTokenizer(payload);
		RSSItem item = new RSSItem();
		
		item.setTitle(st.nextToken("|"));
		st.nextToken(" ");
		item.setLink(st.nextToken(" "));
		
		if (rssFeed.getSavedItems().size() > 10)
			rssFeed.getSavedItems().clear();
		
		rssFeed.getSavedItems().add(item);
	}
}