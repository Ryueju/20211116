package co.eju.prj.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.eju.prj.comm.Command;
import co.eju.prj.member.service.MemberService;
import co.eju.prj.member.serviceImpl.MemberServiceImlp;

public class MemberList implements Command {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		//멤버목록보기
		MemberService memberDao = new MemberServiceImlp();
		//멤버리스트 필요
		request.setAttribute("members", memberDao.memberSelectList());
		
		//request객체에다가 members라는 이름으로 ㄴ결과를 담겠다
		
		
		return "member/memberList";
	}

}
