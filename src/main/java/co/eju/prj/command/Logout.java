package co.eju.prj.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.eju.prj.comm.Command;

public class Logout implements Command {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		//로그아웃 처리
		//세션을 끊어주면 됨
		HttpSession session = request.getSession();
		session.invalidate(); //서버가 가지고 있는 세션 객체를 세션자체를 아예 없애버리는 것.
		return "home.do";
		
	}

}
