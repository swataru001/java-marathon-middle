package jp.co.rakus.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.domain.Hotel;
import jp.co.rakus.form.MoneyForm;
import jp.co.rakus.repository.HotelRepository;



/**
 * ホテル情報検索画面を出力するためのクラス.
 * @author wataru.saito
 *
 */
@Controller
@RequestMapping("/HotelSearch")
public class HotelSearchController {
	
	@Autowired
	HotelRepository repository;
	
	@ModelAttribute
	public MoneyForm setUpForm() {
		return new MoneyForm();
	}
	/**
	 * 出力画面に行くメソッド.
	 * @return 出入力画面
	 */
	@RequestMapping("/index")
	public String index() {
		return "searchHotel";
	}
	
	/**
	 * 検索結果を出力画面に送るメソッド
	 * @param moneyForm
	 * @param model
	 * @return　出入力画面
	 */
	@RequestMapping("/searchResult")
	public String searchResult(MoneyForm moneyForm, Model model) {
		int money = moneyForm.getMoney();
		List<Hotel> hotelList = new ArrayList<>();
		hotelList = repository.findByMoney(money);
		
		model.addAttribute("hotelList",hotelList);
		
		return "searchHotel";
	}
}
