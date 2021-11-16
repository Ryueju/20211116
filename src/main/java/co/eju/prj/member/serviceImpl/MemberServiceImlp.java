package co.eju.prj.member.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.eju.prj.comm.DataSource;
import co.eju.prj.member.service.MemberMapper;
import co.eju.prj.member.service.MemberService;
import co.eju.prj.member.service.MemberVO;

public class MemberServiceImlp implements MemberService {
	private SqlSession sqlSession = DataSource.getDataSource().openSession(true);
																		//auto commit;
	private MemberMapper map = sqlSession.getMapper(MemberMapper.class);
	//mapper를 연결해줌 훨씬 더 다루기가 편하기때문
	
	@Override
	public List<MemberVO> memberSelectList() {
//		전체리스트 보기
		return map.memberSelectList();
	}

	@Override
	public MemberVO memberSelect(MemberVO vo) {
		// login처리 한 명의 데이터를 서치할 때 사용함
		return map.memberSelect(vo);
	}

	@Override
	public int memberInsert(MemberVO vo) {
		
		return map.memberInsert(vo);
	}

	@Override
	public int memberDelete(MemberVO vo) {
		
		return map.memberDelete(vo);
	}

	@Override
	public int memberUpdate(MemberVO vo) {
		
		return map.memberUpdate(vo);
	}

	@Override
	public boolean memberIdCheck(MemberVO vo) {
		
		return map.memberIdCheck(vo);
	}

}
