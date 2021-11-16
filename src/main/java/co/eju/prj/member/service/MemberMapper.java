package co.eju.prj.member.service;

import java.util.List;

public interface MemberMapper {
	List<MemberVO> memberSelectList();
	//한명의회원
	MemberVO memberSelect(MemberVO vo); //로그인체크와 1명의 데이터 가져오는 두가지 용도로
	int memberInsert(MemberVO vo);
	int memberDelete(MemberVO vo);
	int memberUpdate(MemberVO vo);
	boolean memberIdCheck(MemberVO vo); //id중복체크
	
}
