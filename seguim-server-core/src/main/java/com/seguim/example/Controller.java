package com.seguim.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Date: 12/12/14.
 * Time: 14:17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Controller {

	//Indicates the mapping of the controller, "" by default.
	String value() default "";

	//Indicates the method of the request, "GET" by default.
	String[] methods() default "GET";
}
