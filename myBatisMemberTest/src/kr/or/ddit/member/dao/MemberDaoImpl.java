package kr.or.ddit.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao {

	private static MemberDaoImpl instance;

	private MemberDaoImpl() {
	}

	public static MemberDaoImpl getInstance() {
		if (instance == null)
			instance = new MemberDaoImpl();
		return instance;
	}

	SqlSession session = null;

	@Override
	public int insertMember(MemberVO member) {
		int cnt = 0;
		try {
			session = MybatisUtil.getSqlSession();

			cnt = session.delete("member.insertMember", member);

			if (cnt > 0) {
				session.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();

		}

		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		int cnt = 0;

		try {
			session = MybatisUtil.getSqlSession();
			cnt = session.delete("member.deleteMember", memId);

			if (cnt > 0) {
				session.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

		return cnt;
	}

	@Override
	public int updateMember(MemberVO member) {
		int cnt = 0;
		try {
			session = MybatisUtil.getSqlSession();
			cnt = session.update("member.updateMember", member);

			if (cnt > 0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		List<MemberVO> list = null;
		try {
			session = MybatisUtil.getSqlSession();
			list = session.selectList("member.getAllMember");

		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			if (session != null)
				session.close();
		}
		return list;
	}

	@Override
	public int getMemberCount(String memId) {
		int cnt = 0;

		try {
			session = MybatisUtil.getSqlSession();
			cnt = session.selectOne("member.getMemberCount", memId);

			if (cnt > 0) {
				session.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

		return cnt;
	}

	@Override
	public int updateMember2(Map<String, String> paraMap) {
		int cnt = 0;
		try {
			session = MybatisUtil.getSqlSession();
			cnt = session.update("member.updateMember2", paraMap);
			if (cnt > 0) {
				session.commit();
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (session != null)
				session.close();
		}
		return cnt;
	}

}
