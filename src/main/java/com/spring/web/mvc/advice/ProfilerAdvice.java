package com.spring.web.mvc.advice;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class ProfilerAdvice {

	/**
	   * In around advice do not forget to mention return type!
	   * @param joinPoint
	   * @return
	   * @throws Throwable
	   */
	//here my around advice is associated with @Pusher
	@Around("@annotation(com.spring.web.mvc.advice.Pusher)")
	public Object findTimeDifference(ProceedingJoinPoint joinPoint) throws Throwable {
		
		//Below four lines are used to read annotation value
		MethodSignature signature = (MethodSignature)joinPoint.getSignature();
		Method method = signature.getMethod();
		Pusher myAnnotation = method.getAnnotation(Pusher.class);
		System.out.println("Value pass with annotation = "+myAnnotation.mcode());
		
		long startTime = System.currentTimeMillis();
		String methodName = joinPoint.getSignature().getName();
		System.out.println("method "+methodName+" execution starts at " +startTime);
	     //Call actual method
	    Object returnValue=joinPoint.proceed(); //continue on the intercepted method
	    long endTime=System.currentTimeMillis();
	    System.out.println("method "+methodName+" execution ends at " +endTime);
	    System.out.println("Time taken by the method "+methodName+" is "+(endTime-startTime));
	    return returnValue;
	}
}
