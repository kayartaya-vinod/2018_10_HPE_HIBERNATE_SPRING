package com.hpe.training.aspects;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

// An aspect (concern we want to address)
// @Aspect
@Component // auto loading by spring
public class LoggerAspect {

	// an advice (solution to a problem)
	// 4 types of advices -> Before, After, Around, AfterThrowing
	@Before("execution(* com.hpe..*Dao.*(..))")
	public void logMethodInfo(JoinPoint jp) {
		System.out.println(">>> going to invoke " + jp.getSignature().getName());
		System.out.println("... with arguments: " + Arrays.toString(jp.getArgs()));
	}
}
