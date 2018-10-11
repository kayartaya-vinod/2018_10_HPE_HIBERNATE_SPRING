package com.hpe.training.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataTransformer {

	@Around("execution (* com.hpe..ProductDao.*(java.lang.Double, java.lang.Double))")
	public Object swapArgs(ProceedingJoinPoint pjp) throws Throwable {
		Object[] args = pjp.getArgs();
		Double min = (Double) args[0];
		Double max = (Double) args[1];
		if (min > max) {
			args = new Object[] { max, min };
		}
		return pjp.proceed(args); // proceed to intended function with new args
	}
}
