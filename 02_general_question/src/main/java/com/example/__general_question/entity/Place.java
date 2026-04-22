package com.example.__general_question.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.SQLRestriction;

/**
 * 事業所テーブルのデータを取得するクラス
 * 
 * @author towa_tanaka
 */
@Entity
@Table(name = "mst_place")
@SQLRestriction("delete_flg = '0'")
public class Place {

	//ID
	@Id
	@Column(name = "place_id")
	private String placeId;
	
	//事業所名
	@Column(name = "place_name")
	private String placeName;
	
	/**
     * IDを取得するGetter
     */
    public String getPlaceId() {
        return placeId;
    }
	
	//事業所Entityクラスに変換することはないためSetterは定義しない
	/**
	 * 事業所名を取得するGetter
	 */
	public String getPlaceName() {
		return placeName;
	}
}
