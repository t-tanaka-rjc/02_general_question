package com.example.__general_question.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

/**
 * @author towa_tanaka
 */
public class MemberForm {
	/** ID */
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z0-9]{1,10}$")
	private String memberId;
	/** 
	 * 名前 
	 * 空白はOK 
	 */
	@NotEmpty
	@Length(max = 20)
	private String memberName;
	/** 年齢 */
	@NotNull
	@Min(0)
	@Max(150)
	private Integer age;
	/** 住所 */
	@NotEmpty
	@Length(max = 50)
	private String address;
	/** 性別 */
	@NotNull
	private Integer sex;
	/** mail */
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z0-9.@\\-_]{1,30}$")
	private String mail;
	/** 電話番号 */
	@NotBlank
	@Pattern(regexp = "^[0-9]{1,11}$")
	private String tel;
	/** 役職id */
	private String positionId;
	/** 役職名 */
	private String positionName;
	/** 事業所id */
	private String placeId;
	/** 事業所名 */
	private String placeName;

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
	 * @return 役職名
	 */
	public String getPositionName() {
	    return positionName;
	}
	/**
	 * 役職インスタンスを設定します。
	 * @param position 役職名
	 */
	public void setPositionName(String positionName) {
	    this.positionName = positionName;
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
	 * @return 事業所名
	 */
	public String getPlaceName() {
	    return placeName;
	}
	/**
	 * 事業所インスタンスを設定します。
	 * @param place 事業所名
	 */
	public void setPlaceName(String placeName) {
	    this.placeName = placeName;
	}
	
}
