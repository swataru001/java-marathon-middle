package jp.co.rakus.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.domain.Team;
import jp.co.rakus.repository.BaseBallTeamRepository;

/**
 * 球団一覧と球団情報を表す画面へ飛ばすためのクラス.
 * @author wataru.saito
 *
 */
@Controller
@RequestMapping("/BaseBallTeam")
public class BaseBallTeamController {
	
	@Autowired
	BaseBallTeamRepository repository;
	
	/**
	 * 球団一覧画面へ飛ぶメソッド.
	 * @param model
	 * @return 球団一覧
	 */
	@RequestMapping("/index")
	public String index(Model model) {
		List<Team> teamList = new ArrayList<>();
		teamList = repository.findAll();
		
		model.addAttribute("teamList", teamList);
		
		return "viewBaseBallTeam";
	}
	
	/**
	 * 球団情報をテーブルから取り出し出力するためのメソッド.
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/viewTeamDetail")
	public String viewTeamDetail(Integer id,Model model) {
		Team team = new Team();
		team = repository.load(id);
		
		model.addAttribute("team", team);
		
		return "viewTeamInfo";
	}
}
