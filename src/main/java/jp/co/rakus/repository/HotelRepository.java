package jp.co.rakus.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.domain.Hotel;



/**
 * hotelsテーブルのレポジトリ.
 * @author wataru.saito
 *
 */
@Repository
public class HotelRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private final static RowMapper<Hotel> HOTEL_ROWMAPPER = (rs, i) -> {
		Hotel hotel = new Hotel();
		hotel.setId(rs.getInt("id"));
		hotel.setAreaName(rs.getString("area_name"));
		hotel.setHotelName(rs.getString("hotel_name"));
		hotel.setAddress(rs.getString("address"));
		hotel.setNearestStation(rs.getString("nearest_station"));
		hotel.setPrice(rs.getInt("price"));
		hotel.setParking(rs.getString("parking"));
		return hotel;
		
	};
	
	/**
	 * 
	 * 入力された金額以下のホテル情報を検索するメソッド.
	 * @param price
	 * @return
	 */
	public List<Hotel> findByMoney(Integer price) {
		String sql = "SELECT id , area_name, hotel_name, address, nearest_station, price , parking FROM hotels where price <= :price order by price;";

		SqlParameterSource param = new MapSqlParameterSource().addValue("price", price);
			List<Hotel> hotelList = new ArrayList<>();
			hotelList = template.query(sql, param, HOTEL_ROWMAPPER);
		return hotelList;
	}
	
}
