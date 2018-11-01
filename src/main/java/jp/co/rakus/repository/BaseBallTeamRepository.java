package jp.co.rakus.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.domain.Team;


/**
 * baseball_teamテーブルのレポジトリ.
 * @author wataru.saito
 *
 */
@Repository
public class BaseBallTeamRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private final static RowMapper<Team> TEAM_ROWMAPPER = (rs, i) -> {
		Team team = new Team();
		team.setId(rs.getInt("id"));
		team.setLeagueName(rs.getString("league_name"));
		team.setTeamName(rs.getString("team_name"));
		team.setHeadquarters(rs.getString("headquarters"));
		team.setInauguration(rs.getString("inauguration"));
		team.setHistory(rs.getString("history"));
		return team;
	};
	
	/**
	 * チーム情報を一件取り出すメソッド.
	 * @param id
	 * @return　取り出したチーム情報
	 */
	public Team load(Integer id) {
		String sql = "SELECT id , league_name , team_name, headquarters ,inauguration, history FROM baseball_teams where id = :id;";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		Team team = template.queryForObject(sql, param, TEAM_ROWMAPPER);
		return team;
	}
	
	/**
	 * すべてのチーム情報を呼び出すメソッド.
	 * @return　全チーム情報
	 */
	public List<Team> findAll() {
		String sql = "SELECT id , league_name , team_name, headquarters ,inauguration, history FROM baseball_teams ;";

		SqlParameterSource param = new MapSqlParameterSource();
		List<Team> teamList = 	new ArrayList<>();
		teamList = template.query(sql, param, TEAM_ROWMAPPER);
		return teamList;
	}
}
