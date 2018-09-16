package cu.uci.coj.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cu.uci.coj.dao.WbSiteDAO;
import cu.uci.coj.model.WbSite;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

/**
 *
 * @author Eddy Roberto Morales Perez
 */

@Repository("wbSiteDAO")
@Transactional
public class WbSiteDAOImpl extends BaseDAOImpl implements WbSiteDAO {

	@Override
	@Transactional(readOnly = true)
	public WbSite getSiteByUrl(String url) {
		return object("wbsite.by.url", WbSite.class, url);
	}

	@Override
	public void setCompleted(int sid, boolean completed) {
		dml("wbsite.update.completed", completed, sid);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getSiteId(String site) {
		return integer("wbsite.sid", site);
	}

	@Override
	@Transactional(readOnly = true)
	public List<WbSite> getSiteList() {
		return objects("wbsite.list.enabled", WbSite.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<WbSite> getSiteListFollowed(Integer uid) {
		return objects("wbsite.list.followed", WbSite.class, uid);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Integer> getIdSiteListFollowed(Integer uid) {
		return integers("wbsite.list.id.followed", uid);
	}

	@Override
	public void followSite(Integer uid, Integer sid) {
		dml("wbsite.follow", uid, sid);
	}

	@Override
	public void unfollowSite(Integer uid, Integer sid) {
		dml("wbsite.unfollow", uid, sid);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean getIfFollow(Integer uid, Integer sid) {
		return (integer(0, "wbsite.getiffollow", uid, sid) != 0);
	}

	@Override
	public void updateSiteListFollowed(Integer uid, List<Integer> followed) {
		dml("wbsite.delete.sites.followed", uid);
		if(followed != null) {
			for(int i = 0;i<followed.size();i++) {
				dml("wbsite.follow", uid, followed.get(i));
			}
		}
	}


	@Override
	public int insertSite(WbSite site) {
		return jdbcTemplate.queryForObject(getSql("wbsite.insert"), Integer.class,site.getSite(), site.getUrl(), site.getCode(), site.isCompleted(), site.isEnabled(), site.getTimeanddateid(), site.getTimezone());
	}

	@Override
	public void updateSite(WbSite site) {
		dml("wbsite.update", site.getSite(), site.getUrl(), site.getCode(), site.isCompleted(), site.isEnabled(), site.getTimeanddateid(), site.getTimezone(), site.getSid());
	}

	@Override
	public void deleteSite(Integer sid) {
		dml("wbsite.delete", sid);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isSiteEnabled(Integer sid) {
		return bool("wbsite.enable", sid);
	}

	@Override
	public void setSiteEnabled(Integer sid, Boolean enable) {
		dml("wbsite.enable.set", enable, sid);
	}

	@Override
	public IPaginatedList<WbSite> getPaginatedSiteList(PagingOptions options) {
		String sql = getSql("wbsite.list");
		int found = integer("count.wbsite.list");
		List<WbSite> list = new ArrayList<WbSite>();
		if (found > 0) {
			sql += " ORDER BY " + options.getSort() + " " + options.getDirection();
			list = objects(sql, WbSite.class);
		}
		return getPaginatedList(options, list, 25, found);
	}

	@Override
	public WbSite getSiteById(Integer sid) {
		return object("wbsite.by.sid", WbSite.class, sid);
	}

}
