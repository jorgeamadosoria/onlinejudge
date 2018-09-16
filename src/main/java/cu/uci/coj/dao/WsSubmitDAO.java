package cu.uci.coj.dao;

import java.util.List;

import cu.uci.coj.model.WsSubmit;

public interface WsSubmitDAO {
	WsSubmit getSubmit(int sid);
	List<WsSubmit> getSubmits(int from, int to);
	List<WsSubmit> getSubmits(String user);
}
