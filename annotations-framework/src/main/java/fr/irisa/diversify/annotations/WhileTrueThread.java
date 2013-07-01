package fr.irisa.diversify.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.CONSTRUCTOR})
public @interface WhileTrueThread {

	
	public int numberOfThread() default 1;

}
