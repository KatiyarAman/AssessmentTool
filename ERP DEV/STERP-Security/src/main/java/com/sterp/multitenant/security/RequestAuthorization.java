package com.sterp.multitenant.security;

import java.lang.annotation.*;

/**
 * @author Navinder Singh
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RequestAuthorization {
}
