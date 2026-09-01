package com.rays.common;

import org.springframework.dao.DataAccessResourceFailureException;
import org.hibernate.exception.JDBCConnectionException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ApplicationExceptionHandlerCtl {

	@ExceptionHandler({ CannotCreateTransactionException.class, DataAccessResourceFailureException.class,
			JDBCConnectionException.class })
	public ResponseEntity<ORSResponse> handleDatabaseException(Exception e) {

		ORSResponse res = new ORSResponse(false);
		res.addMessage("Database server down!! Please try again later.");

		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(res);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ORSResponse> handleRuntimeException(RuntimeException e) {

		ORSResponse res = new ORSResponse(false);
		res.addMessage(e.getMessage());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
	}
}