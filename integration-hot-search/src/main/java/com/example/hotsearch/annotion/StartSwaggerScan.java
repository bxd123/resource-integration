package com.example.hotsearch.annotion;

import java.lang.annotation.*;

/**
 * @program: dyswagger
 * @description: Swagger扫描注解
 * @author: DingYongJun
 * @create: 2021-11-10 22:03
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Inherited
public @interface StartSwaggerScan {
}