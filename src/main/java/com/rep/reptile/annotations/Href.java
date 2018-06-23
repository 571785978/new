package com.rep.reptile.annotations;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Href {
    public String hrefSelector();
    public String attr() default "text";
}
