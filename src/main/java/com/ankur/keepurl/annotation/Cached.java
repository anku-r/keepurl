package com.ankur.keepurl.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Applicable on method. 
 * Puts returned value in a cache, using one of string arguments as key.
 * Expects input keyArgumentIndex, starting from 0, to pick argument as key.
 * 
 * Throws IllegalArgumentException if keyArgumentIndex is less than 0, or 
 * method has no argument, or picked key/argument is not String type or is empty.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cached {

    int keyArgumentIndex();
}


