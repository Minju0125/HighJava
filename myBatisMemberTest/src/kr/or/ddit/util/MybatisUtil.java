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
		Reader rd = null;

		try {
			// 파일을 읽어올 스트림 객체 생성
			rd = Resources.getResourceAsReader("kr/or/ddit/mybatis/config/mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(rd);

		} catch (Exception e) {
			System.out.println("myBatis 초기화 실패!!!");
			e.printStackTrace();
		} finally {
			if (rd != null)	try {rd.close();} catch (IOException e) {}
		}
	}
	
	//SqlSession 객체를 반환하는 메서드
	public static SqlSession getSqlSession() {
		SqlSession session = sqlSessionFactory.openSession();
		return session;
	}
}
