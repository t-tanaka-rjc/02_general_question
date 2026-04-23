package com.example.__general_question.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.example.__general_question.entity.Place;
import com.example.__general_question.repository.PlacesRepository;

/**
 * 事業所サービスクラス
 * 
 * @author towa_tanaka
 */
@Service
public class PlacesService {

	@Autowired
	private PlacesRepository placesRepository;
	
	/**
	 * @return 事業所テーブルのデータ一覧を取得する（EntityクラスのフィールドにはIDと名前しか定義のみ）
	 */
	public List<Place> getAll() {
		
		return placesRepository.findAll();
	}

	/**
	 * @return 事業所Idに紐づく事業所テーブルのデータを返す
	 */
	public Place getById(String id) throws NotFoundException {
		Optional<Place> place = placesRepository.findById(id);
		
		//memberを対象にOptional型のisEmptyメソッドを実行し、
		//値がなければ(true)例外をスロー、ある場合はgetメソッドを実行し値を取得
		//値をDtoクラスに変換する
		if (place.isEmpty()) {
			throw new NotFoundException();
		} else {
			//getメソッドの返り値はEntityクラス
			return place.get();
		}
	} 
	
	/**
	 * 入力値（事業所Id）に紐づく事業所名を取得する。IDがNull、または空文字の場合は空文字を返す。
	 */
	public String getPlaceName(String id) throws NotFoundException {
		
		//IdがNullまたは空文字の場合は空文字を返す
		if (id == null || id.isEmpty()) {
	        return ""; 
	    }
		
		// try-catchを書かなくても、getById自体がthrows宣言しているので、
	    // 例外発生時はControllerに例外が飛ぶ
	    return getById(id).getPlaceName();
	}
}
