package com.kh.forest.common;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.kh.forest.common.Member;


@Repository
@Aspect
public class LoginLogging {
	private static final Logger logger = LoggerFactory.getLogger(LoginLogging.class);
	
	@Pointcut("execution(* com.kh.tsp..*DaoImpl.login*(..))")
	public void loginPointcut(){}
	
	@AfterReturning(pointcut="loginPointcut()", returning="returnObj")
	public void loginLig(JoinPoint jp, Object returnObj){
		if(returnObj instanceof Member){
			Member m = (Member)returnObj;
			logger.info(new Date() + " : $" + m.getUserId() + "$님이 접속했습니다.");
			
		}
	}
	
}











