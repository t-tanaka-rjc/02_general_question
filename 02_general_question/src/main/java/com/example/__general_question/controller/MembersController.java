package com.example.__general_question.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.__general_question.dto.MemberDto;
import com.example.__general_question.helper.AttributeMessageHelper;
import com.example.__general_question.service.MembersService;

/**
 * メンバー機能コントローラークラス
 * 
 * @author towa_tanaka
 */
@Controller
public class MembersController {
	@Autowired
	private MembersService membersService;
	
	@Autowired
	private AttributeMessageHelper attributeMessageHelper;
	
	/**
	 * メニュー画面
	 */
	@GetMapping("/")
	private String index() {
		return "index";
	}
	
	/**
	 * List(一覧画面)
	 * 
	 * 一覧画面は初期表示の場合、リクエストURLのパラメーターは無いので
	 * 3つともパラメーター無しを許可する
	 * 
	 * @param model
	 * @return list（一覧）画面を返す
	 */
	@GetMapping("/list")
	private String list(Model model) {
		
		List<MemberDto> membersDtoList = membersService.getAll();
		//list画面にデータ一覧を渡す
		model.addAttribute("members", membersDtoList);
		
		return "list";
	}
	
	/**
	 * 詳細画面
	 * 
	 * @param id URLの値を取得する
	 */
	@GetMapping("/detail/{id}")
	private String detail(@PathVariable(value = "id") String id, Model model,
			RedirectAttributes redirAttrs) {
		
		MemberDto memberDto;
		try {
			memberDto = membersService.getById(id);
		} catch (NotFoundException e) {
			redirAttrs.addFlashAttribute("error", attributeMessageHelper.getPropertieMessage("targetInvalidError"));
			return "redirect:/";
		}
		
		model.addAttribute("member", memberDto);
		return "detail";
	}
}
