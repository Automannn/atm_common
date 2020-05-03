package com.automannn.common.web.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

/**
 * @author automannn@163.com
 * @time 2020/5/3 20:10
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController
@RequestMapping
public @interface AtmController {

    @AliasFor(
            annotation = RequestMapping.class
    )
    String[] value() default {};

    @AliasFor(
            annotation = RequestMapping.class
    )
    String[] path() default {};

    @AliasFor(
            annotation = RequestMapping.class
    )
    String name() default "";

    @AliasFor(
            annotation = RequestMapping.class
    )
    RequestMethod[] method() default {};

    @AliasFor(
            annotation = RequestMapping.class
    )
    String[] params() default {};

    @AliasFor(
            annotation = RequestMapping.class
    )
    String[] headers() default {};

    @AliasFor(
            annotation = RequestMapping.class
    )
    String[] consumes() default {};

    @AliasFor(
            annotation = RequestMapping.class
    )
    String[] produces() default {};
}
