package com.spring.web.mvc.advice;

import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

@Service //@Service - annotation was missing
@Aspect //hey this is advice for the Aspect
public class GreetDaoAdvice {
	
	public GreetDaoAdvice(){
		System.out.println(")))))))GreetDaoAdvice))))))))");
		System.out.println(")))))))GreetDaoAdvice))))))))");
		System.out.println(")))))))GreetDaoAdvice))))))))");
		System.out.println(")))))))GreetDaoAdvice))))))))");
		System.out.println(")))))))GreetDaoAdvice))))))))");
		System.out.println(")))))))GreetDaoAdvice))))))))");
		System.out.println(")))))))GreetDaoAdvice))))))))");

	}
	
	//Here write common code which you want to inject before every method
	//execution(* com.employee.database.dao.*.*(..)) -Expression for  Join Point
	@Before("execution(* com.spring.web.mvc.dao.*.*(..))")
	public void greetLogger(JoinPoint joinPoint) {
		System.out.println("Name of the method which is invoked......" + joinPoint.getSignature().getName());
		System.out.println("******>>> Method inputs are - "+Arrays.asList(joinPoint.getArgs()));
		System.out.println("Hey this is method is called at "+new Date());
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("+++++++++++++++++++END END END+++++++++++++++++++++++++++++");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");

	}
	
	//Here write common code which you want to inject before every method
	//execution(* com.employee.database.dao.*.*(..)) -Expression for  Join Point
	@After("execution(* com.spring.web.mvc.dao.*.*(..))")
	public void end(JoinPoint joinPoint) {
		System.out.println("hey ....." + joinPoint.getSignature().getName()+" is completed!!!!!!!!!!!!!!!!!!!");
	}


	@AfterThrowing(pointcut = "execution(* com.spring.web.mvc.dao.*.*(..))", throwing = "error")
	public void afterThrowingAdvice(JoinPoint jp, Throwable error){
	   System.out.println("Method Signature: "  + jp.getSignature());  
	   System.out.println("Exception: "+error);  
	}
}
