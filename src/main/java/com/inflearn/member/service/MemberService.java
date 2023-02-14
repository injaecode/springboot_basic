package com.inflearn.member.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inflearn.member.Repository.MemberRepository;
import com.inflearn.member.Repository.MemoryMemberRepository;
import com.inflearn.member.domain.Member;


@Transactional
public class MemberService {
	private final MemberRepository memberRepository ;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository=memberRepository;
	}
	
	//회원가입
	public Long Join(Member member) {
		long start = System.currentTimeMillis();
		try {
		
			validateDuplicateMember(member); //중복 회원 검증
			memberRepository.save(member);
			
			return member.getId();
		 } finally {
		 
			 long finish = System.currentTimeMillis();
			 long timeMs = finish - start;
		 
			 System.out.println("join " + timeMs + "ms");
		 }
	}	
	
	//중복 회원 x
		private void validateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName())
			.ifPresent(m->{
			throw new IllegalStateException("이미 존재하는 회원입니다.");
			});
		}
	
	//전체 회원 조회
		public List<Member> findMembers(){
			long start = System.currentTimeMillis();
			try {
			
				return memberRepository.findAll();
			
			} finally {
				long finish = System.currentTimeMillis();
				long timeMs = finish - start;
				System.out.println("findMembers " + timeMs + "ms");
			}
		}
	//단일 회원 조회 	
		public Optional<Member> findOne(Long memberId){
			return memberRepository.findById(memberId);
		}

}
