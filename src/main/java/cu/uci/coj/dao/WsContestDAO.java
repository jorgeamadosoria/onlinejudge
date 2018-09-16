package cu.uci.coj.dao;

import java.util.List;

import cu.uci.coj.model.WsContest;

public interface WsContestDAO {
	List<WsContest> getComingContests();
}
