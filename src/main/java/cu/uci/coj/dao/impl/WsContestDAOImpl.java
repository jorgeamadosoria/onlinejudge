package cu.uci.coj.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cu.uci.coj.dao.WsContestDAO;
import cu.uci.coj.model.WsContest;
import cu.uci.coj.model.entities.Contest;

@Repository("wsContestDAO")
public class WsContestDAOImpl extends BaseDAOImpl implements WsContestDAO {

	@Override
	public List<WsContest> getComingContests() {
		return objects("ws.coming.contests", WsContest.class);
	}
}
