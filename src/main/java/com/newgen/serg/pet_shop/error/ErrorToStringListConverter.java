package com.newgen.serg.pet_shop.error;

import org.springframework.validation.Errors;

import java.util.List;
import java.util.stream.Collectors;

public class ErrorToStringListConverter {
	public static List<String> convert(Errors errors){
		return errors.getAllErrors().stream()
		.map(x -> x.getDefaultMessage())
		.collect(Collectors.toList());
	}
}
