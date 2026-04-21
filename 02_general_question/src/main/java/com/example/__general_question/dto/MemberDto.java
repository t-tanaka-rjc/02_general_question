package com.example.__general_question.dto;

import com.example.__general_question.entity.Member;

public class MemberDto {
	/** ID */
	private String memberId;
	/** 名前 */
	private String memberName;
	/** 年齢 */
	private String age;
	/** 住所 */
	private String address;
	/** 性別 */
	private String sex;
	/** mail */
	private String mail;
	/** 電話番号 */
	private String tel;
	/** 役職id */
	private String positionId;
	/** 役職名 */
	private String positionName;
	/** 事業所id */
	private String placeId;
	/** 事業所名 */
	private String placeName;
	/** 登録日 */
	private String regist;

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
	public String getAge() {
	    return age;
	}
	/**
	 * 年齢を設定します。
	 * @param age 年齢
	 */
	public void setAge(String age) {
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
	public String getSex() {
	    return sex;
	}
	/**
	 * 性別を設定します。
	 * @param sex 性別
	 */
	public void setSex(String sex) {
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
	/**
	 * 登録日を取得します。
	 * @return 登録日
	 */
	public String getRegist() {
	    return regist;
	}
	/**
	 * 登録日を設定します。
	 * @param regist 登録日
	 */
	public void setRegist(String regist) {
	    this.regist = regist;
	}
	
	/**
	 * Member(Entity)をMemberDtoに変換する
	 * 
	 * @param member 変換元
	 * @return MemberDto
	 */
	public static final MemberDto convertEntityToDto(Member member) {
		MemberDto memberDto = new MemberDto();
		
		memberDto.setMemberId(member.getMemberId());
		memberDto.setMemberName(member.getMemberName());
		memberDto.setAge(member.getAge());
		memberDto.setAddress(member.getAddress());
		memberDto.setSex(member.getSex());
		memberDto.setMail(member.getMail());
		memberDto.setTel(member.getTel());
		memberDto.setPositionId(member.getPositionId());
		//設計では新入社員は役職IDや事業所IDは付与しない。
		//Memberクラス内のPositionクラスのインスタンスを取得するgetPosition()は
		//positionIDを比較して取得するからそもそもIDが存在しなければNullになる。
		//member.getPosition()がNullの場合は取得せずに、新入社員という文字列をpositionNameにセットする
		if (member.getPosition() != null) {
			memberDto.setPositionName(member.getPosition().getPositionName());
		} else {
			memberDto.setPositionName("新入社員");
		}
		
		memberDto.setPlaceId(member.getPlaceId());
		
		if (member.getPlace() != null) {
			memberDto.setPlaceName(member.getPlace().getPlaceName());
		} else {
			memberDto.setPlaceName("未所属");
		}
		
		memberDto.setRegist(member.getRegist());
		
		return memberDto;
	}
}
