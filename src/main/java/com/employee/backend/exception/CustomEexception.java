/**
 * 
 */
package com.employee.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author suryateja
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomEexception extends RuntimeException {


	private static final long serialVersionUID = 1L;
	
	public CustomEexception(String message) {
		super(message);
	}

}
