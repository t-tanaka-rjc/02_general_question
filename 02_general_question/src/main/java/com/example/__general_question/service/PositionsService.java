package com.example.__general_question.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.example.__general_question.entity.Position;
import com.example.__general_question.repository.PositionsRepository;

/**
 * 役職サービスクラス
 * 
 * @author towa_tanaka
 */
@Service
public class PositionsService {
	
	@Autowired
	private PositionsRepository positionsRepository;
	
	/**
	 * @return 役職テーブルのデータ一覧を返す（EntityクラスのフィールドにはIDと名前しか定義していない）
	 */
	public List<Position> getAll() {
		return positionsRepository.findAll();
	}
	
	/**
	 * @return 役職Idに紐づく役職テーブルのデータを返す
	 */
	public Position getById(String id) throws NotFoundException {
		Optional<Position> position = positionsRepository.findById(id);
		
		//memberを対象にOptional型のisEmptyメソッドを実行し、
		//値がなければ(true)例外をスロー、ある場合はgetメソッドを実行し値を取得
		if (position.isEmpty()) {
			throw new NotFoundException();
		} else {
			//getメソッドの返り値はEntityクラス
			return position.get();
		}
	}
	
	/**
	 * 入力値（役職Id）に紐づく役職名を取得する。IDがNull、または空文字の場合は空文字を返す。
	 */
	public String getPositionName(String id) throws NotFoundException {
		
		//IdがNullまたは空文字の場合は空文字を返す
		if (id == null || id.isEmpty()) {
	        return ""; 
	    }
		
		// try-catchを書かなくても、getById自体がthrows宣言しているので、
	    // 例外発生時はControllerに例外が飛ぶ
	    return getById(id).getPositionName();
	}
}
