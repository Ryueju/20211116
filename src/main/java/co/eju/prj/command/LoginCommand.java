package co.eju.prj.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.eju.prj.comm.Command;
import co.eju.prj.member.service.MemberService;
import co.eju.prj.member.service.MemberVO;
import co.eju.prj.member.serviceImpl.MemberServiceImlp;

public class LoginCommand implements Command {

		//먼저 db에 다녀와야함

	
	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		// 로그인을 처리하는 과정
		MemberService memberDao = new MemberServiceImlp();
		MemberVO vo = new MemberVO();
		HttpSession session = request.getSession();//세션 객체를 가져오겠다는 뜻
		
		vo.setId(request.getParameter("id"));
		vo.setPassword(request.getParameter("password"));
		vo = memberDao.memberSelect(vo);
		
		String message = null;
		
		if(vo !=null) {
			//vo객체가 값이 존재한다면
			session.setAttribute("id", vo.getId()); 
			//session에다가 id라는 변수를만들고 vo가 가지고있는 id를 담아줌
			session.setAttribute("name", vo.getName());
			session.setAttribute("authour", vo.getAuthor());
			//session객체..서버가 브라우저에서 요청이 들어오면 request객체,response객체, session객체를 만들어줌
			//server에서 보관하고 응답을해주게 됨, session객체가 생성될때 (unique)고유한id가 생성되는데, 
			//이 브라우저로 들어온놈들은 각각의 id가 잇어서 다음에 들어올떄 session 객체를 만들어주지않음
			//index.jsp > home.do > 다시들어올때 request,respones객체만 가게 됨.
			message = vo.getName()+ "님 환영합니다!";
			
		} else {
			message = "아이디 또는 패스워드를 확인하세요..";
		}								//들어갈 값
		request.setAttribute("message", message); //결과를 담아 보낼 때
							//ㄴ jsp페이지에서사용할 변수명
		return "member/memberLogin";
	}

}
