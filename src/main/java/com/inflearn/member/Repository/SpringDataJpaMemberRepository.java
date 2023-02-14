package com.inflearn.member.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inflearn.member.domain.Member;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{

	
	Optional<Member> findByName(String name);
}
