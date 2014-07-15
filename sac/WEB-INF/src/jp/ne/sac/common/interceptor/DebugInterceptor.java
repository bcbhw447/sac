package jp.ne.sac.common.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * デバッグ用コメントを出力する.
 * <p>
 * デバッグ用コメント出力処理<br>
 *
 * @author m_hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
public class DebugInterceptor extends AbstractInterceptor {

    /** serialVersionUID. */
    private static final long serialVersionUID = 4816733204546064736L;

    /*
     * (non-Javadoc)
     * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
     */
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {

        System.out.println("*** DebugInterceptor **************************************************************");
        System.out.println("ActionName >>> " + invocation.getProxy().getActionName());
        System.out.println("MethodName >>> " + invocation.getProxy().getMethod());
        System.out.println("***********************************************************************************");

        return invocation.invoke();
    }

}
