package jp.ne.sac.common.interceptor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
import java.util.ResourceBundle;

import net.sf.oval.Validator;
import net.sf.oval.localization.message.ResourceBundleMessageResolver;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.oval.interceptor.OValValidationInterceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.util.AnnotationUtils;

/**
 * Validationインターセプター.
 * <p>
 * Validationのインターセプター。OValValidationInterceptorの不具合を修正したもの。<br>
 *
 * @author m_hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
public class ValidationInterceptor extends OValValidationInterceptor {

    /** serialVersionUID. */
    private static final long serialVersionUID = 2789471878833304167L;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        Object action = invocation.getAction();
        if (action != null) {
            Method method = this.getActionMethod(action.getClass(), invocation.getProxy().getMethod());
            Collection<Method> annotatedMethods = AnnotationUtils.getAnnotatedMethods(action.getClass(),
                    SkipValidation.class);
            if (annotatedMethods.contains(method)) {
                return invocation.invoke();
            }
            // check if method overwites an annotated method
            Class clazz = action.getClass().getSuperclass();

            // カスタマイズしたアノテーションチェック用メッセージを追加
            Locale locale = invocation.getInvocationContext().getLocale();
            ResourceBundleMessageResolver resolver = (ResourceBundleMessageResolver)Validator.getMessageResolver();
            resolver.addMessageBundle(ResourceBundle.getBundle("common", locale));
            resolver.addMessageBundle(ResourceBundle.getBundle("screen", locale));
            resolver.addMessageBundle(ResourceBundle.getBundle("annotationMessage", locale));

            while (clazz != null) {
                annotatedMethods = AnnotationUtils.getAnnotatedMethods(clazz, SkipValidation.class);
                if (annotatedMethods != null) {
                    for (Method annotatedMethod : annotatedMethods) {
                        if (annotatedMethod.getName().equals(method.getName())
                                && Arrays.equals(annotatedMethod.getParameterTypes(), method.getParameterTypes())
                                && Arrays.equals(annotatedMethod.getExceptionTypes(), method.getExceptionTypes())) {
                            return invocation.invoke();
                        }
                    }
                }
                clazz = clazz.getSuperclass();
            }
        }

        return super.doIntercept(invocation);

    }

    /**
     * アクションメソッド取得処理.<br>
     *
     * @param actionClass アクションクラス
     * @param methodName メソッド名
     * @return メソッドオブジェクト
     * @throws NoSuchMethodException 該当メソッド名が取得できなかった場合
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private Method getActionMethod(Class actionClass, String methodName) throws NoSuchMethodException {
        Method method;
        try {
            method = actionClass.getMethod(methodName, new Class[0]);
        } catch (NoSuchMethodException e) {
            // hmm -- OK, try doXxx instead
            try {
                String altMethodName = "do" + methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
                method = actionClass.getMethod(altMethodName, new Class[0]);
            } catch (NoSuchMethodException e1) {
                // throw the original one
                throw e;
            }
        }
        return method;
    }

}
