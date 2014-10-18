package me.rbrick.zeus.annotations;

import javax.annotation.Nonnull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This code is copyrighted by rbrick and the BreakMC Network.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Command {
    @Nonnull String name() default "";

    String[] aliases() default {};

    String desc() default "";

    String usage() default "";

    String permission() default "";

    String permissionMsg() default "";

    int minArgs() default -1;

    int maxArgs() default -1;
}
