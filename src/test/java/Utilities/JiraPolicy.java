package Utilities;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Define custom annotation JiraPolicy
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) // Can be applied to methods (or steps in Cucumber)
public @interface JiraPolicy {
    boolean logTicketReady() default false;
}
