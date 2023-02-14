package com.inflearn.member.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.inflearn.member.Repository.MemberRepository;
import com.inflearn.member.Repository.MemoryMemberRepository;
import com.inflearn.member.domain.Member;


public class MemoryMemberRepositoryTest {

	MemoryMemberRepository repository = new MemoryMemberRepository();

	//테스트는 상호 의존 관계 없이 설계 되어야 한다
	@AfterEach
	public void afterEach() {
		repository.clearStore();
	}

	@Test
	public void save() {
		Member member = new Member();
		member.setName("spring");
		
		repository.save(member);
		Member result = repository.findById(member.getId()).get();
	Assertions.assertEquals(member, result);
	
	}
//	
//	@Test
//	public void findByName() {
//		Member member1 = new Member();
//		member1.setName("spring1");
//		repository.save(member1);
//		
//		Member member2 = new Member();
//		member2.setName("spring2");
//		repository.save(member2);
//		
//		Member result=repository.findByName("spring1").get();
//		assertThat(result).isEqualTo(member1);
//	}
	
	@Test
	public void findAll() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		List<Member> result = repository.findAll();
		assertThat(result.size()).isEqualTo(2);
	}
	

}
