package com.hpe.training.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// @Aspect
@Component
public class Profiler {

	@Pointcut("execution (* com.hpe..*Dao.get*Id(..))")
	public void pc1() {
	}

	@Pointcut("execution (* com.hpe..*Dao.count(..))")
	public void pc2() {
	}

	@Around("pc1() or pc2()")
	public Object findAndPrintExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
		long ms1 = System.currentTimeMillis();
		// ask the proxy to invoke the intenteded method(with args)
		Object obj = pjp.proceed();
		long ms2 = System.currentTimeMillis();
		System.out.println(
				"Total time taken  by " + pjp.getSignature().getName() + "(..)" + "  is " + (ms2 - ms1) + " millis.");

		return obj;
	}

}
