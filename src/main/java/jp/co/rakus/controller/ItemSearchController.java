package jp.co.rakus.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.rakus.domain.Shop;
import jp.co.rakus.form.ShopForm;
import jp.co.rakus.repository.ShopRepository;

@Controller
@RequestMapping("/itemSearch")
public class ItemSearchController {

	@Autowired
	ShopRepository repositry;

	@ModelAttribute
	public ShopForm setUpForm() {
		return new ShopForm();
	}

	/**
	 * 
	 * 商品検索ページへ飛ぶメソッド
	 * @param model
	 * @return　商品検索ページ
	 */
	@RequestMapping("/index")
	public String index(Model model) {

		Map<Integer, String> genderMap = new LinkedHashMap<>();
		genderMap.put(0, "男");
		genderMap.put(1, "女");
		model.addAttribute("genderMap", genderMap);

		Map<Integer, String> colorMap = new LinkedHashMap<>();
		colorMap.put(0, "赤");
		colorMap.put(1, "青");
		colorMap.put(2, "黄");
		colorMap.put(3, "白");

		model.addAttribute("colorMap", colorMap);

		return "searchitems";
	}

	/**
	 * 
	 * 検索結果を取得し、検索ページ以上に表示させるメソッド.
	 * @param shopForm
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("/searchResult")
	public String searchResult(ShopForm shopForm,RedirectAttributes redirectAttributes) {
		List<Shop> itemList = new ArrayList<>();
		itemList = repositry.findByGenderAndColor(shopForm);
		redirectAttributes.addFlashAttribute("itemList", itemList);

		return "redirect:/itemSearch/index";
	}
}
