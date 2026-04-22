package com.example.__general_question.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.hibernate.annotations.SQLRestriction;


/**
 * メンバーテーブルのデータを取得するEntityクラス
 * 
 * @author towa_tanaka
 */

@Entity
@Table(name = "tbl_member")
//下記は削除されていないデータだけを取得する。WHERE句
@SQLRestriction("delete_flg = '0'")
public class Member {

	// ID
	@Id
	//下記はID（主キー）の採番をDBに完全に任せるアノテーションなので
	//ME00000037のような文字列を手動で登録する場合は不要
	//手動で登録するのに付けていたら更新だと勘違いして例外が発生する
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private String memberId;
	
	// メンバー名
	@Column(name = "member_name")
	private String memberName;
	
	// 年齢
	@Column(name = "age")
	private Integer age;
	
	//性別コード（0:男性、1:女性）
	@Column(name = "sex_flg")
	private Integer sex;
	
	//住所
	@Column(name = "address")
	private String address;
	
	//電話番号
	@Column(name = "telephone")
	private String tel;
	
	//メールアドレス
	@Column(name = "mail")
	private String mail;
	
	//役職ID
	@Column(name = "position_id")
	private String positionId;
	
	//役職名：多くのコードに対してひとつの役職名が紐づく
	@ManyToOne
	//下記は中身を覗くだけ。保存更新はしないようにするコード
	//nameは結合条件
	@JoinColumn(name = "position_id", insertable = false, updatable = false)
	//下記でメンバーテーブルの役職IDに紐づく役職Entityクラスを取得できる
	private Position position;
	
	//事業所ID
	@Column(name = "place_id")
	private String placeId;
	
	//メンバーテーブルの事業所IDと事業所テーブルの事業所IDが同じ場合の事業所名が取得できる
	@ManyToOne
	@JoinColumn(name = "place_id", insertable = false, updatable = false)
	private Place place;

	/**
	 * IDを取得します。
	 * @return ID
	 */
	public String getMemberId() {
	    return memberId;
	}
	/**
	 * IDを設定します。
	 * @param memberId ID
	 */
	public void setMemberId(String memberId) {
	    this.memberId = memberId;
	}
	/**
	 * 名前を取得します。
	 * @return 名前
	 */
	public String getMemberName() {
	    return memberName;
	}
	/**
	 * 名前を設定します。
	 * @param name 名前
	 */
	public void setMemberName(String memberName) {
	    this.memberName = memberName;
	}
	/**
	 * 年齢を取得します。
	 * @return 年齢
	 */
	public Integer getAge() {
	    return age;
	}
	/**
	 * 年齢を設定します。
	 * @param age 年齢
	 */
	public void setAge(Integer age) {
	    this.age = age;
	}
	/**
	 * 住所を取得します。
	 * @return 住所
	 */
	public String getAddress() {
	    return address;
	}
	/**
	 * 住所を設定します。
	 * @param address 住所
	 */
	public void setAddress(String address) {
	    this.address = address;
	}
	/**
	 * 性別を取得します。
	 * @return 性別
	 */
	public Integer getSex() {
	    return sex;
	}
	/**
	 * 性別を設定します。
	 * @param sex 性別
	 */
	public void setSex(Integer sex) {
	    this.sex = sex;
	}
	/**
	 * mailを取得します。
	 * @return mail
	 */
	public String getMail() {
	    return mail;
	}
	/**
	 * mailを設定します。
	 * @param mail mail
	 */
	public void setMail(String mail) {
	    this.mail = mail;
	}
	/**
	 * 電話番号を取得します。
	 * @return 電話番号
	 */
	public String getTel() {
	    return tel;
	}
	/**
	 * 電話番号を設定します。
	 * @param tel 電話番号
	 */
	public void setTel(String tel) {
	    this.tel = tel;
	}
	/**
	 * 役職idを取得します。
	 * @return 役職id
	 */
	public String getPositionId() {
	    return positionId;
	}
	/**
	 * 役職idを設定します。
	 * @param positionId 役職id
	 */
	public void setPositionId(String positionId) {
	    this.positionId = positionId;
	}
	/**
	 * 役職インスタンスを取得します。
	 * @return 役職インスタンス
	 */
	public Position getPosition() {
	    return position;
	}
	/**
	 * 役職インスタンスを設定します。
	 * @param position 役職インスタンス
	 */
	public void setPosition(Position position) {
	    this.position = position;
	}
	/**
	 * 事業所idを取得します。
	 * @return 事業所id
	 */
	public String getPlaceId() {
	    return placeId;
	}
	/**
	 * 事業所idを設定します。
	 * @param placeId 事業所id
	 */
	public void setPlaceId(String placeId) {
	    this.placeId = placeId;
	}
	/**
	 * 事業所インスタンスを取得します。
	 * @return 事業所インスタンス
	 */
	public Place getPlace() {
	    return place;
	}
	/**
	 * 事業所インスタンスを設定します。
	 * @param place 事業所インスタンス
	 */
	public void setPlace(Place place) {
	    this.place = place;
	}
}
