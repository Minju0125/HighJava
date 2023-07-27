package kr.or.ddit.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {
	private static SqlSessionFactory sqlSessionFactory;

	static {
		// myBatis 초기화 작업
		Reader rd = null;
		try {
			rd = Resources.getResourceAsReader("kr/or/ddit/mybatis/config/mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(rd);
			
		} catch (Exception e) {
			System.out.println("myBatis 초기화 실패!!");
			e.printStackTrace();
		} finally {
			if(rd!=null) try { rd.close(); }catch(IOException e) {}
		}
	}
	
	// SqlSession객체를 반환하는 메서드 작성
	public static SqlSession getSqlSession() {
		SqlSession session = sqlSessionFactory.openSession();
		return session;
	}
	
}
