package com.newgen.serg.pet_shop.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ErrorMessage {
	private HttpStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	List<String> errors;

	public ErrorMessage(List<String> errors, HttpStatus status) {
		this.errors = errors;
		this.status = status;
		timestamp = LocalDateTime.now();
	}
	
	public ErrorMessage(String error, HttpStatus status) {
		this.errors = new ArrayList<>();
		errors.add(error);
		this.status = status;
		timestamp = LocalDateTime.now();
	}
}
