package com.hpe.training.aspects;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.hpe.training.dao.DaoException;

@Aspect
@Component
public class DaoExceptionDecorator {

	@AfterThrowing(value = "execution(* com.hpe..*Dao.*(..))", throwing = "t")
	public void convertToDaoException(Throwable t) throws DaoException {
		throw new DaoException("DaoException:: " + t.getMessage(), t);
	}

}
