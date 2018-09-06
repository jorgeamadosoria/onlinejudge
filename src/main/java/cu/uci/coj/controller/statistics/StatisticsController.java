package cu.uci.coj.controller.statistics;

import java.security.Principal;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.BaseDAO;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.Contest;
import cu.uci.coj.model.Language;
import cu.uci.coj.model.Stats;
import cu.uci.coj.model.UserClassificationStats;

@Controller
@RequestMapping(value = "/")
public class StatisticsController extends BaseController {

	private static final long serialVersionUID = 5068203635059987095L;
	@Resource
	private BaseDAO baseDAO;
	@Resource
	private UserDAO userDAO;
	@Resource
	private ContestDAO contestDAO;

	private Stats createDataset(String query) {
		return baseDAO.object(query, Stats.class);
	}
	//
	// private Map<String, ?> createDataset(int cid) {
	// Map<String, ?> map = baseDAO.map("statistics.contest.status", cid);
	// Set<String> set = map.keySet();
	// for (String key : set) {
	// if (!key.equals("total")) {
	// double val = (Long) map.get(key);
	// val /= (Long) map.get("total");
	// val *= 100;
	// val = (double) Math.round(val * 100) / 100;
	// map.addValue(val, "status", key.toLowerCase());
	// }
	// }
	// return map;
	// }

	@RequestMapping(value = "/24h/statistics.xhtml", method = RequestMethod.GET)
	public String TrainingModuleStatistics(Model model) {
		model.addAttribute("stat", baseDAO.object("statistics.total.status", Stats.class));
		model.addAttribute("statistics", baseDAO.objects("training.statistics", Language.class));

		model.addAttribute("graph", createDataset("statistics.status"));

		UserClassificationStats ucs = userDAO.getTotalClassifications();
		model.addAttribute("classif", ucs);
		return "/24h/statistics";
	}

	@RequestMapping(value = "/contest/cstatistics.xhtml", method = RequestMethod.GET)
	public String ContestStatistics(Principal principal, Model model, @RequestParam("cid") Integer cid) {
		Contest contest = contestDAO.loadContest(cid);
		Integer uid = getUid(principal);
		boolean uidInContest = baseDAO.bool("exist.user.in.contest", uid, cid);
		model.addAttribute("showStats", (uidInContest && contest.isShow_stats()) || contest.isShow_stats_out());
		contestDAO.unfreezeIfNecessary(contest);
		model.addAttribute("stat", baseDAO.object("statistics.contest.total.status", Stats.class, contest.getCid()));
		model.addAttribute("statistics", baseDAO.objects("statistics.contest", Language.class, contest.getCid()));
		contest.setUsers(userDAO.loadContestUsers(contest.getCid()));
		model.addAttribute("contest", contest);
		model.addAttribute("cuser", contest.getUsers().size());
		model.addAttribute("ccountry", contestDAO.getCantCountry(cid));
		model.addAttribute("cinst", contestDAO.getCantInst(cid));
		model.addAttribute("cuserguest", contestDAO.getCantUserGuest(cid, contest.getGuestGroup()));
		model.addAttribute("ccountryguest", contestDAO.getCantCountryGuest(cid, contest.getGuestGroup()));
		model.addAttribute("cinstguest", contestDAO.getCantInstGuest(cid, contest.getGuestGroup()));
		return "/contest/cstatistics";
	}

	/*
	 * RF97 Ver estadísticas de concursos
	 */
	@RequestMapping(value = "/contest/globalstatistics.xhtml", method = RequestMethod.GET)
	public String GlobalContestStatistics(Model model) {
		model.addAttribute("stat", baseDAO.object("statistics.global.total.status", Stats.class));
		model.addAttribute("statistics", baseDAO.objects("statistics.global", Language.class));
		model.addAttribute("graph", createDataset("statistics.global.total.status"));
		return "/contest/globalstatistics";
	}

	@RequestMapping(value = "/practice/virtualstatistics.xhtml", method = RequestMethod.GET)
	public String VirtualStatistics(Model model) {
		model.addAttribute("stat", baseDAO.object("statistics.virtual.total.status", Stats.class));
		model.addAttribute("statistics", baseDAO.objects("statistics.virtual", Language.class));
		model.addAttribute("graph", createDataset("statistics.virtual.status"));
		return "/practice/virtualstatistics";
	}

}
