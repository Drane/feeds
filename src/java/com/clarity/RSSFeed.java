package com.clarity;

import java.io.Serializable;
import java.net.URL;
import java.util.LinkedList;

import org.gnu.stealthp.rsslib.RSSChannel;
import org.gnu.stealthp.rsslib.RSSHandler;
import org.gnu.stealthp.rsslib.RSSItem;
import org.gnu.stealthp.rsslib.RSSParser;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("rssFeed")
@SessionScoped
public class RSSFeed implements Serializable {
	private static final long serialVersionUID = 2L;
	
	private String feed, displayName;
	private RSSChannel channel;
	private LinkedList<RSSItem> savedItems = new LinkedList<RSSItem>();

	public void fetch(String f, String dn) {
		assert f != null;
		assert dn != null;

		feed = f;
		displayName = dn;

		RSSHandler handler = new RSSHandler();
		channel = handler.getRSSChannel();

		try {
			RSSParser.parseXmlFile(new URL(feed), handler, true);
		} catch (Exception e) {
			channel = null;
			e.printStackTrace();
		}
	}

	public LinkedList<RSSItem> getItems() {
		return channel == null ? null : channel.getItems();
	}

	public RSSChannel getChannel() { return channel; }
	public String getFeed() { return feed; }
	public String getDisplayName() { return displayName; }
	public LinkedList<RSSItem> getSavedItems() { return savedItems;	}
}