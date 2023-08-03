package kr.or.ddit.memberList.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.memberList.vo.MemberVO;
import kr.or.ddit.util.MybatisUtil;

public class MemberDaoImpl implements IMemberDao{
	
	private static MemberDaoImpl dao;
	
	private MemberDaoImpl() {}
	
	public static MemberDaoImpl getInstance() {
		if(dao==null) dao = new MemberDaoImpl();
		return dao;
	}
	

	@Override
	public List<MemberVO> getAllMember() {
		SqlSession session = null;
		List<MemberVO> list = null;
		
		try {
			session = MybatisUtil.getSqlSession();
			list = session.selectList("member.getAllMember");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) session.close();
		}
		return list;
	}

	@Override
	public int insertMember(MemberVO memvo) {
		SqlSession session = null;
		int cnt= 0;
		
		try {
			session = MybatisUtil.getSqlSession();
			cnt = session.insert("member.insertMember");
			session.commit();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) session.close();
		}
		
		return cnt;
	}

}
