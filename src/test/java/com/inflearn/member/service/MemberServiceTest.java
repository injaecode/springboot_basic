package com.inflearn.member.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import 	org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import	org.junit.jupiter.api.Test;

import com.inflearn.member.Repository.MemoryMemberRepository;
import com.inflearn.member.domain.Member;

public class MemberServiceTest {


	MemberService memberService;
	MemoryMemberRepository memberRepository;
	
	@BeforeEach
	public void beforeEach(){
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}
	
	@AfterEach
	public void afterEach() {
		memberRepository.clearStore();
	}
	
	@Test
	public void testJoin() {
		//given
		Member member = new Member();
		member.setName("hello");
		//when
		Long saveId = memberService.Join(member);
		//then
		Member findMember = memberService.findOne(saveId).get();
		Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
	}
	
	@Test
	public void 중복회원예외() {
		//given
		Member member1 = new Member();
		member1.setName("spring");
		
		Member member2 = new Member();
		member2.setName("spring");
		//when
		memberService.Join(member1);
		IllegalStateException e = 
				assertThrows(IllegalStateException.class, () -> memberService.Join(member2));
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//		try {
//			memberService.Join(member2);
//			fail();
//		}catch(IllegalStateException e) {
//			assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니당");
//		}
		//then
		
	}
//	@Test
//	public void testFindMembers() {
//		throw new RuntimeException("not yet implemented");
//	}
//
//	@Test
//	public void testFindOne() {
//		throw new RuntimeException("not yet implemented");
//	}

}
