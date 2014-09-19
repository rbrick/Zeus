package me.rbrick.zeus.annotations;

import javax.annotation.Nonnull;

public @interface SubCommand {
    
    @Nonnull String parent() default ""; // The parent command e.g. /example would be the parent command.
    
    @Nonnull String name() default ""; // The actual command e.g. /example demostrate. demostrate would be the subcommand.
}
