package com.curso.spring.exception;

import java.sql.SQLException;
//import java.util.logging.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.curso.spring.payload.response.ResponseCodes;


@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

	//private static final Logger log = Logger.getLogger(HandlerException.class.getName());
	
	/*@ExceptionHandler(value = {Unauthorized.class})
	public ResponseEntity<String> lanzarExcepcionInvalidInput(Unauthorized ex){
		log.setLevel(Level.INFO);
		log.log(Level.INFO, "Usted no está autorizado para acceder a estos recuersos"+ ex.getMessage());
		
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<String> larzarExcepcion(Exception ex){
		log.setLevel(Level.WARNING);
		log.log(Level.WARNING, "Exception"+ ex.getMessage());
		
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}*/
	
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<ResponseCodes> sqlExcepcion(SQLException ex){
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseCodes(417, "Error En base de datos"));
	}
	
	
}
