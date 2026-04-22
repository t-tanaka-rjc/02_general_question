package com.example.__general_question.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.example.__general_question.dto.MemberDto;
import com.example.__general_question.entity.Member;
import com.example.__general_question.repository.MembersRepository;

@Service
public class MembersService {

	@Autowired
	private MembersRepository membersRepository;
	
	/** 
	 * @return メンバテーブルの情報を全件返す
	 */
	public List<MemberDto> getAll() {
		// 下記のメソッドはテーブルのデータを全件List型で受け取れる（SELECT分を発行）
		//指定していないので並び順がバラバラかも
		//Entityクラス（DBのデータそのもの）のデータを受け取る
		List<Member> memberList = membersRepository.findAll();
		
		//Dtoクラスに変換(Controllerに渡すため)		
		List<MemberDto> memberDtoList = memberList.stream()
				.map(MemberDto::convertEntityToDto)
				.collect(Collectors.toList());
		
		return memberDtoList;
	}

	/**
	 * メンバーIDに紐づくメンバ情報を表示。詳細画面で使用
	 * 
	 * @param id memberId
	 * @return Dtoに変換したメンバ一覧情報を返す
	 * @throws NotFoundException
	 */
	public MemberDto getById(String id) throws NotFoundException {
		Optional<Member> member = membersRepository.findById(id);
		
		//memberを対象にOptional型のisEmptyメソッドを実行し、
		//値がなければ(true)例外をスロー、ある場合はgetメソッドを実行し値を取得
		//値をDtoクラスに変換する
		if (member.isEmpty()) {
			throw new NotFoundException();
		} else {
			//getメソッドの返り値はEntityクラス
			return MemberDto.convertEntityToDto(member.get());
		}
	}

	/**
	 * データをDBに登録
	 * 
	 * @param dto FormクラスからDtoクラスに変換された入力値
	 * @return 
	 */
	public Member insert(MemberDto dto) {

		Member member = MemberDto.convertDtoToEntity(dto);
		
		return membersRepository.save(member);
	}

}
