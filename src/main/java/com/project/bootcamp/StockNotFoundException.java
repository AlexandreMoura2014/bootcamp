package com.project.bootcamp;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StockNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public StockNotFoundException(Long id) {
		super("Stock not found with ID " + id);
	}

}
