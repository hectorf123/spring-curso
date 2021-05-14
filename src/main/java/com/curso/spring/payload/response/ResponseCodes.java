package com.curso.spring.payload.response;

public class ResponseCodes {
	
	private Integer code;
	private String message;

	public ResponseCodes(Integer code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}



	public void setCode(Integer code) {
		this.code = code;
	}



	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
