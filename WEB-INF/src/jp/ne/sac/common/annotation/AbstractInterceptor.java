package jp.ne.sac.common.annotation;

import java.lang.annotation.Annotation;

import jp.ne.sac.common.system.Constants;
import jp.ne.sac.common.system.bean.SystemInfo;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;

/**
 * インターセプタの基底クラスです.
 * <p>
 *
 * @author m_hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
public abstract class AbstractInterceptor extends com.opensymphony.xwork2.interceptor.AbstractInterceptor {

    /** serialVersionUID. */
    private static final long serialVersionUID = 6026726149560894673L;

    /**
     * システムセッション情報を返します.
     *
     * @param invocation ActionInvocation
     * @return システムセッション情報
     */
    protected SystemInfo getSystemInfo(ActionInvocation invocation) {
        ActionContext context = invocation.getInvocationContext();
        return (SystemInfo)context.getSession().get(Constants.SES_SYS_INFO);
    }

    /**
     * メソッドのアノテーション宣言を取得します.
     *
     * @param <T> アノテーション
     * @param invocation ActionInvocation
     * @param annotation アノテーション
     * @return アノテーション
     */
    protected <T extends Annotation> T getAnnotation(ActionInvocation invocation, Class<T> annotation) {

        Class<? extends Object> action = invocation.getAction().getClass();
        String methodName = invocation.getProxy().getMethod();

        return AnnotationUtil.getAnnotation(action, methodName, annotation);
    }

}
