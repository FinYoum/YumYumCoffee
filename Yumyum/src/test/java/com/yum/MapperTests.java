package com.yum;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate.Param;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yum.domain.MemberDTO;
import com.yum.mapper.RegisterMapper;

@SpringBootTest
public class MapperTests {
	@Autowired
	private RegisterMapper registerMapper;
	
	@Test
	public void testRegisterMapper() {
		MemberDTO params = new MemberDTO();
		params.setName("김종주");
		params.setId("jongju");
		params.setPw("jong123");
		params.setTel("010-8012-7716");
		params.setEmail("jongjoo15@gmail.com");
		params.setBirth("1999-11-16");
		
		int result = registerMapper.insertMember(params);
		System.out.println("결과는 " + result + "입니다.");
	}
	@Test
	public void testMultipleInsert() {
		for (int i = 2; i <= 50; i++) {
			MemberDTO params = new MemberDTO();
			params.setName(i+" 이름");
			params.setId(i+" id");
			params.setPw(i+" pw");
			params.setTel(i+" 8210");
			params.setEmail(i+" email");
			params.setBirth(i+" 996");

			registerMapper.insertMember(params);
		}
	}
	
	@Test
	public void testOfSelectDetail() {
		MemberDTO member = registerMapper.selectMemberDetail((long) 1);
		try {
			//String boardJson = new ObjectMapper().writeValueAsString(board);
            String memberJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(member);

			System.out.println("=========================");
			System.out.println(memberJson);
			System.out.println("=========================");

		} catch (JsonProcessingException e) {
//			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void testOfUpdate() {
		MemberDTO params = new MemberDTO();
		params.setName("김아무개");
		params.setId("amugae");
		params.setPw("amugae123");
		params.setTel("010-1234-5678");
		params.setEmail("amugae@gmail.com");
		params.setBirth("1990-12-31");
		params.setUserNum((int) 2);

		int result = registerMapper.updateMember(params);
		if (result == 2) {
			MemberDTO member = registerMapper.selectMemberDetail((long) 2);
			try {
				//String boardJson = new ObjectMapper().writeValueAsString(board);
                String memberJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(member);

				System.out.println("=========================");
				System.out.println(memberJson);
				System.out.println("=========================");

			} catch (JsonProcessingException e) {
//				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testOfDelete() {
		int result = registerMapper.deleteMember((long) 50);
		if (result == 50) {
			MemberDTO member = registerMapper.selectMemberDetail((long) 50);
			try {
				//String boardJson = new ObjectMapper().writeValueAsString(board);
                String memberJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(member);

				System.out.println("=========================");
				System.out.println(memberJson);
				System.out.println("=========================");

			} catch (JsonProcessingException e) {
//				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testSelectList() {
		int memberTotalCount = registerMapper.selectMemberTotalCount();
		if (memberTotalCount > 0) {
			List<MemberDTO> memberList = registerMapper.selectMemberList();
			if (CollectionUtils.isEmpty(memberList) == false) {
				for (MemberDTO member : memberList) {
					System.out.println("=========================");
					System.out.println(member.getName());
					System.out.println(member.getId());
					System.out.println(member.getPw());
					System.out.println(member.getTel());
					System.out.println(member.getEmail());
					System.out.println(member.getBirth());
					System.out.println("=========================");
				}
			}
		}
	}
	
}
