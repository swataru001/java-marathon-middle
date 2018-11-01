package jp.co.rakus.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.domain.Shop;
import jp.co.rakus.form.ShopForm;

/**
 * 商品情報をテーブルから取り出すレポジトリ.
 * 
 * @author wataru.saito
 *
 */
@Repository
public class ShopRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private final static RowMapper<Shop> SHOP_ROWMAPPER = (rs, i) -> {
		Shop shop = new Shop();
		shop.setId(rs.getInt("id"));
		shop.setCategory(rs.getString("category"));
		shop.setGenre(rs.getString("genre"));
		shop.setGender(rs.getInt("gender"));
		shop.setColor(rs.getString("color"));
		shop.setPrice(rs.getInt("price"));
		shop.setSize(rs.getString("size"));
		return shop;

	};

	/**
	 * 
	 * 性別と色情報をもとに商品を検索するメソッド.
	 * 
	 * @param form
	 * @return テーブルから上記の情報をもとに検索した結果が入ったリスト.
	 */
	public List<Shop> findByGenderAndColor(ShopForm form) {
		int genderNum = form.getGender();
		String colorName = null;
		int color = form.getColor();
		switch (color) {
		case 0:
			colorName = "赤";
			break;

		case 1:
			colorName = "青";
			break;

		case 2:
			colorName = "黄";
			break;

		case 3:
			colorName = "白";
			break;
		}

		String sql = "SELECT id , category, genre, gender, color, price , size FROM shops where gender = :gender and color = :color;";

		SqlParameterSource param = new MapSqlParameterSource().addValue("gender", genderNum).addValue("color",colorName);
		List<Shop> itemList = new ArrayList<>();
		itemList = template.query(sql, param, SHOP_ROWMAPPER);
		return itemList;
	}
}
