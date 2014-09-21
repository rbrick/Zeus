package me.rbrick.zeus.annotations;

import javax.annotation.Nonnull;

public @interface SubCommand {

   @Nonnull String parent() default "";

    String[] aliases() default {};

}
