package cn.ctoedu.miaosha.access;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @Author missli
 * @Description 基于AOP 使用 Redis 实现 n 秒内可访问的最大次数maxCount
 * key = uri_userId_seccond
 * value 进行调用次数自增
 * 过期时间设置为最大秒数
 *
 * 校验是否需要登录
 * @Date 2019/6/14 14:53
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface AccessLimit {
	int seconds();
	int maxCount();
	boolean needLogin() default true;
}
