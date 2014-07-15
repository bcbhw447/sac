package jp.ne.sac.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * スキップログインチェックアノテーションクラス.
 * <p>
 * ログインチェックを行なわないメソッドで使用します。<br>
 *
 * @author m_hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SkipLoginCheck {

    // 空実装。

}
