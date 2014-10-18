package me.rbrick.zeus.annotations;

import javax.annotation.Nonnull;

/**
 * This code is copyrighted by rbrick and the BreakMC Network.
 */
@Retention(RetentionPolicy.RUNTIME)
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
