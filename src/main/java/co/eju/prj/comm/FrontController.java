package co.eju.prj.comm;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.eju.prj.command.HomeCommand;
import co.eju.prj.command.LoginCommand;
import co.eju.prj.command.LoginForm;
import co.eju.prj.command.Logout;
import co.eju.prj.command.MemberList;


//컨트롤러!
//모든 요청은 얘를 거치도록 하기 위해 web.xml에서 이 서블릿을 정의해줌
//*.do url에서 어떤 요청이 들어오건 무조건 마지막에 .do가 들어오면 꼭 FrontController를 타게끔 해라!
//web.xml에서 서블릿 정의할때 servlet이랑 servlet-mapping항상 두개 다 적어야함!!
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Command> map = new HashMap<String, Command>();
  
    public FrontController() {
        super();
       
    }

	public void init(ServletConfig config) throws ServletException { 
		//초기값 설정("/login.do, new LoginCommand()) key,value로 -> hashmap필요
		//login.do요청하면 new LoginCommand()를 실행시켜라.
		map.put("/home.do", new HomeCommand()); //홈 페이지를 보여주는 Command
		//map은 메모리에 저장되어있는거라
		map.put("/login.do", new LoginCommand()); //로그인처리
		map.put("/memberList.do", new MemberList());//멤버 목록보기
		map.put("/loginForm.do", new LoginForm()); //로그인 폼호출
		map.put("/logout.do", new Logout()); //로그아웃 처리
		
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//요청을 분석하고 처리하는 부분
		request.setCharacterEncoding("UTF-8"); //한글깨짐 방지처리
		//요청을 분석,처리하기 위해 사용자가 요청하는 페이지를 구해야 한다.
		String uri = request.getRequestURI(); //1. 우선 URI를 구한다.
		String contextPath = request.getContextPath(); //2. contextPath를 구한다.
		String page = uri.substring(contextPath.length()); //3. 실제요청페이지를 구한다.(uri에서 contextpath를 제외한 부분이 실제요청페이지니까.)
	
		
		//처리할 명령어 작성
		Command command = map.get(page); //요청에 대해 수행할 command를 찾기.
		String viewPage = command.run(request, response);
		
		//WEB-INF에 접근할 수 있도록 viewResolve를 만듦.
		
		if(!viewPage.endsWith(".do")) { //viewpage는 string. endswith는 그 문자열 마지막에 매개값으로 들어온 값이 포함되어 있느냐
			viewPage = "WEB-INF/views/"+viewPage+".jsp";
		}
		
		//응답을 처리한다.
		//a가 서버에게 요청-b도 서버에게 요청(a가 보낸 데이터를 함께 실어서)-c(a가 보낸 데이터와 응답을 a에게 전달)
		//a가 b한테 위임한 것을(a가 요청들어왔는데 b에게 위임했으니 a,b각각에게 response와request를 만들어줌)
		//근데 requestdispatcher는 b의 request만들때 a때 만든 request를 싣고 가서 만들어주기때문에 a의 요청이 일관성있게 처리될 수 있음
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage); 
		dispatcher.forward(request, response);
		
	}
	//우선 web.xml에 설정해놓은 것처럼 

}
