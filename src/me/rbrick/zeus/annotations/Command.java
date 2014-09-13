package me.rbrick.zeus.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Command {

    String name() default "";

    String[] aliases() default {};

    String desc() default "";

    String usage() default "";

    String permission() default "";

}
