package com.example.__general_question.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.__general_question.dto.MemberDto;
import com.example.__general_question.form.MemberForm;
import com.example.__general_question.helper.AttributeMessageHelper;
import com.example.__general_question.service.MembersService;
import com.example.__general_question.service.PlacesService;
import com.example.__general_question.service.PositionsService;

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
	private PositionsService positionsService;
	
	@Autowired
	private PlacesService placesService;
	
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
	 * insert（登録）画面
	 * 
	 * @return insert　登録画面を返す
	 */
	@GetMapping("/insert")
	private String insert(Model model) {
		
		model.addAttribute("member", new MemberForm());
		//役職テーブルのデータ一覧（Idと名前のみ）取得しセレクトボックスに表示
		model.addAttribute("positions", positionsService.getAll());
		//事業所テーブルのデータ一覧（Idと名前のみ）取得しセレクトボックスに表示
		model.addAttribute("places", placesService.getAll());
		
		return "insert";
	}
	
	/**
	 * 入力値確認
	 * 
	 * @return 登録確認画面を返す
	 */
	@PostMapping("/insertConf")
	private String insertErrorCheck(@Valid @ModelAttribute("member") MemberForm form, BindingResult result,
			RedirectAttributes redirAttrs, Model model) {
		//画面入力時にエラーがある場合は処理を中断して登録画面を再表示する
		if (result.hasErrors()) {
			model.addAttribute("positions", positionsService.getAll());
			//事業所テーブルのデータ一覧（Idと名前のみ）取得しセレクトボックスに表示
			model.addAttribute("places", placesService.getAll());
			return "insert";
		}
		
		//確認画面ではpositionIdに紐づくpositionNameを表示する
		try {
			//画面表示用の役職名をサービスクラスから取得してFormクラスのフィールドにセットする
			//そうすることで違うキー名と役職名を紐づけて画面に渡さなくても、FormクラスのpositionNameフィールドに
			//代入することで上記のmemberキーで呼び出せる
			form.setPositionName(positionsService.getPositionName(form.getPositionId()));
			
			form.setPlaceName(placesService.getPlaceName(form.getPlaceId()));
		} catch (NotFoundException e) {
			redirAttrs.addFlashAttribute("error", attributeMessageHelper.getPropertieMessage("dataMissingError"));

			//リダイレクトは指定したURLでアクセスしなおすから、登録画面のメソッドが呼ばれる
			return "redirect:/insert";
		}
		
		return "insertConf";
		
	}

	/**
	 * 登録処理
	 * 
	 * @return 登録完了画面を返す（リダイレクト）
	 */
	@PostMapping("/insertComp")
	private String addInsert(@ModelAttribute("member") MemberForm form, RedirectAttributes redirAttrs) {
		//FormからDtoに変換する
		MemberDto dto = MemberDto.convertFormToDto(form);
		
		//メンバー登録処理を行う
		membersService.insert(dto);
		
		
		
		try {
	
			form.setPositionName(positionsService.getPositionName(form.getPositionId()));
			
			form.setPlaceName(placesService.getPlaceName(form.getPlaceId()));
			//リダイレクトの場合は指定したURLでアクセスしなおすからModelではデータの保持はできない
			//レスポンスを返したらModelの中身は消えるから
			//確認完了画面に登録値（入力値）のデータを渡す
			redirAttrs.addFlashAttribute("member", form);
			
		} catch (NotFoundException e) {
			
			redirAttrs.addFlashAttribute("error", attributeMessageHelper.getPropertieMessage("dataMissingError"));

			//リダイレクトは指定したURLでアクセスしなおすから、登録画面のメソッドが呼ばれる
			return "redirect:/insert";
		}
		
		//登録処理の後に画面を表示する場合は、必ずリダイレクト
		return "redirect:/insertComp";
	}
	
	/**
	 * 登録完了画面
	 * 
	 * @return 登録完了画面を返す
	 */
	@GetMapping("/insertComp")
	private String insertComp() {
		//登録処理メソッド内で、完了画面に表示するデータをもらっているからなにもしなくていい
		return "insertComp";
	}
	
	/**
	 * 詳細画面
	 * 
	 * @param id URLの値を取得する
	 */
	@GetMapping("/show/{id}")
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
	
	/**
	 * 更新画面
	 * 
	 * @return 更新画面を返す
	 */
	@GetMapping("/update/{id}")
	private String update(@PathVariable(value = "id") String id, Model model,
			RedirectAttributes redirAttrs) {
		
		MemberDto memberDto;
		try {
			memberDto = membersService.getById(id);
		} catch (NotFoundException e) {
			redirAttrs.addFlashAttribute("error", attributeMessageHelper.getPropertieMessage("targetInvalidError"));
			return "redirect:/";
		}
		
		model.addAttribute("member", memberDto);
		
		//役職テーブルのデータ一覧（Idと名前のみ）取得しセレクトボックスに表示
		model.addAttribute("positions", positionsService.getAll());
		//事業所テーブルのデータ一覧（Idと名前のみ）取得しセレクトボックスに表示
		model.addAttribute("places", placesService.getAll());
		
		return"update";
	}
	
	/**
	 * 入力値確認(更新画面)
	 * 
	 * @return 更新確認画面を返す
	 */
	@PostMapping("/updateConf")
	private String updateErrorCheck(@Valid @ModelAttribute("member") MemberForm form, BindingResult result,
			RedirectAttributes redirAttrs, Model model) {
		//画面入力時にエラーがある場合は処理を中断して登録画面を再表示する
		if (result.hasErrors()) {
			model.addAttribute("positions", positionsService.getAll());
			//事業所テーブルのデータ一覧（Idと名前のみ）取得しセレクトボックスに表示
			model.addAttribute("places", placesService.getAll());
			return "update";
		}
		
		try {
			
			form.setPositionName(positionsService.getPositionName(form.getPositionId()));
			
			form.setPlaceName(placesService.getPlaceName(form.getPlaceId()));
		} catch (NotFoundException e) {
			redirAttrs.addFlashAttribute("error", attributeMessageHelper.getPropertieMessage("dataMissingError"));

			//リダイレクトは指定したURLでアクセスしなおすから、登録画面のメソッドが呼ばれる
			return "redirect:/insert";
		}
		
		return "updateConf";
		
	}

	/**
	 * 更新処理
	 * 
	 * @return 更新完了画面を返す（リダイレクト）
	 */
	@PostMapping("/updateComp")
	private String addUpdate(@ModelAttribute("member") MemberForm form, RedirectAttributes redirAttrs) {
		//FormからDtoに変換する
		MemberDto dto = MemberDto.convertFormToDto(form);
		
		//メンバー登録処理を行う
		membersService.insert(dto);
		
		try {
			
			form.setPositionName(positionsService.getPositionName(form.getPositionId()));
			
			form.setPlaceName(placesService.getPlaceName(form.getPlaceId()));

			//リダイレクトの場合は指定したURLでアクセスしなおすからModelではデータの保持はできない
			//レスポンスを返したらModelの中身は消えるから
			//確認完了画面に登録値（入力値）のデータを渡す
			redirAttrs.addFlashAttribute("member", form);
		} catch (NotFoundException e) {
			
			redirAttrs.addFlashAttribute("error", attributeMessageHelper.getPropertieMessage("dataMissingError"));

			//リダイレクトは指定したURLでアクセスしなおすから、登録画面のメソッドが呼ばれる
			return "redirect:/insert";
		}
		
		
		
		//登録処理の後に画面を表示する場合は、必ずリダイレクト
		return "redirect:/insertComp";
	}
	
	/**
	 * 更新完了画面
	 * 
	 * @return 登録完了画面を返す
	 */
//	@GetMapping("/insertComp")
//	private String insertComp() {
//		//登録処理メソッド内で、完了画面に表示するデータをもらっているからなにもしなくていい
//		return "insertComp";
//	}
}
