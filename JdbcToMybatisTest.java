package kr.or.ddit.basic;
//JdbcTest05 클래스의 기능을 Mybatis용으로 변경하시오.

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/*
LPROD테이블에 새로운 데이터 추가하기

Lprod_gu와 Lprod_nm은 직접 입력 받아서 처리하고,
Lprod_id는 현재의 Lprod_id 중에서 제일 큰 값보다 1 크게 한다.

입력받은 Lprod_gu가 이미 등록되어 있으면 다시 입력 받아서 처리한다.

ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

mapper파일명 : jdbc-mapper.xml
*/

public class JdbcToMybatisTest {
	public static void main(String[] args) {
		Reader rd=null;
		SqlSessionFactory sqlSessionFactory = null;
		
		try {
			//파일을 읽어올 스트림 객체 생성
			rd=Resources.getResourceAsReader("kr/or/ddit/mybatis/config/mybatis-config.xml");
			
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(rd);
			
		}catch (Exception e) {
			System.out.println("myBatis 초기화 실패!!!");
			e.printStackTrace();
		} finally {
			if(rd!=null)try{rd.close();}catch(IOException e) {};
			
		}
		
		
		
		
		
		
	}
	
	
	
}
