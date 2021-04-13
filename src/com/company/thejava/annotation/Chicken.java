package com.company.thejava.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) // 이 Annotation 정보를 언제까지 유지할 것인가
// @Target(ElementType.TYPE_PARAMETER) // Parameter Type으로 사용 가능하게 해줌
@Target(ElementType.TYPE_USE) // Type이 들어가는 모든 곳에 사용 가능하게 해줌
public @interface Chicken {
}
