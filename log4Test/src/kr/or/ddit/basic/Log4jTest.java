package kr.or.ddit.basic;

import org.apache.log4j.Logger;

public class Log4jTest {
	// Logger 클래스의 인스턴스를 구한다.
										//클래스 정보를 보내줌
	static Logger logger = Logger.getLogger(Log4jTest.class);
	
	public static void main(String[] args) {
		//로그 기록 남기는 연습
	
		//형식) Logger객체.로그레벨명("출력할 메세지");
		logger.trace("이 로그 기록은 TRACE레벨의 로그 기록입니다.");
		logger.debug("이 로그 기록은 DEBUG레벨의 로그 기록입니다.");
		logger.info("이 로그 기록은 INFO레벨의 로그 기록입니다.");
		logger.warn("이 로그 기록은 WARN레벨의 로그 기록입니다.");
		logger.error("이 로그 기록은 ERROR레벨의 로그 기록입니다.");
		logger.fatal("이 로그 기록은 FATAL레벨의 로그 기록입니다.");
		
		System.out.println("작업 끝...");
		
	}
}
