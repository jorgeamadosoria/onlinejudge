package cu.uci.coj.utils.parsing;

import java.util.List;

import cu.uci.coj.model.WbContest;
import cu.uci.coj.model.WbSite;
import cu.uci.coj.utils.ConnectionErrorException;

public abstract class WbParser {

	protected WbSite site;
	protected String siteUrl;

	public abstract void init();
	
	public abstract List<WbContest> parse()  throws ConnectionErrorException;
	
	public WbSite getSite() {
		return site;
	}

	public void setSite(WbSite site) {
		this.site = site;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}	
}