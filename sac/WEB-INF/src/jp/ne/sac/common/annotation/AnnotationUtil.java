package jp.ne.sac.common.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import jp.ne.sac.common.exception.SystemException;

/**
 * アノテーション用ユーティリティクラス.<p>
 *
 * @author m_hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
public final class AnnotationUtil {

    /**
     * コンストラクタ.
     */
    private AnnotationUtil() {
    }

    /**
     * メソッドアノテーションを取得します.
     * <p>
     * アノテーションが宣言されていない場合はnullを返します。
     * </p>
     *
     * @param <T1> クラス
     * @param <T2> アノテーションクラス
     * @param clazz クラス
     * @param methodName メソッド名
     * @param annotationClass アノテーションクラス
     * @return アノテーション
     */
    public static <T1, T2 extends Annotation> T2 getAnnotation(Class<T1> clazz, String methodName,
            Class<T2> annotationClass) {

        Method method = org.apache.commons.lang3.reflect.MethodUtils.getAccessibleMethod(clazz, methodName, new Class[0]);
        if (method == null) {
            throw new SystemException("method not found.(" + clazz.getClass() + "#" + methodName + ")");
        }

        return method.getAnnotation(annotationClass);
    }
}
