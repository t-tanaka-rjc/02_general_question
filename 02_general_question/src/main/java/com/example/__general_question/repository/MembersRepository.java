package com.example.__general_question.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.__general_question.entity.Member;

/**
 * メンバー機能のリポジトリクラス
 * 
 * @author towa_tanaka 
 */
public interface MembersRepository extends JpaRepository<Member, String>{

}
