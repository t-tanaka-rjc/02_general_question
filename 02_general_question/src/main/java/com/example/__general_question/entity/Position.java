package com.example.__general_question.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.SQLRestriction;

/**
 * 役職テーブルのデータを取得するEntityクラス
 */
@Entity
@Table(name = "mst_position")
@SQLRestriction("delete_flg = '0'")
public class Position {

	//ID
	@Id  
	@Column(name = "position_id")
	private String positionId;
	
	//役職名
	@Column(name = "position_name")
	private String positionName;
	
	/**
     * IDを取得するGetter
     */
    public String getPositionId() {
        return positionId;
    }
	
	//役職Entityクラスに変換することはないためSetterは定義しない
	/**
	 * 役職名を取得するGetter
	 */
	public String getPositionName() {
		return positionName;
	}
	
}
