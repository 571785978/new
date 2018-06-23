package com.rep.reptile.annotations;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Selector {

    public enum type{ID,CLASS,TAG_NAME}

    /**
     * 类型
     * @return
     */
    public type type() default type.ID;

    /**
     * 值
     * @return
     */
    public String value();

    /**
     * 返回值类型
     * @return
     */
    public Class valueType();

    public String attr() default "text";

}
