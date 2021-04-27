package com.czg.xmind;

import jdk.nashorn.internal.objects.annotations.Function;
import org.apache.commons.cli.Option;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Commander {
    String opt();

    String argName();

    String desc();

    String longOpt();
}
