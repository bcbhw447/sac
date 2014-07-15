package jp.ne.sac.common.interceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jp.ne.sac.common.annotation.AbstractInterceptor;
import jp.ne.sac.common.annotation.SkipLoginCheck;
import jp.ne.sac.common.base.BaseAction;
import jp.ne.sac.common.system.Constants;
import jp.ne.sac.common.system.bean.SystemInfo;

import org.slf4j.MDC;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;

/**
 * ログインチェックを行なうインターセプタクラス.
 * <p>
 * ログインセッションのチェックを行ないます。<br>
 * セッションが存在しない場合は、"session_expired"で処理を終了します。<br>
 *
 * @author m_hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
public class LoginCheckInterceptor extends AbstractInterceptor {

    /** serialVersionUID. */
    private static final long serialVersionUID = -2345048259848892652L;

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("rawtypes")
    public String intercept(ActionInvocation invocation) throws Exception {

        // セッションからシステムの情報を取得する
        ActionContext context = invocation.getInvocationContext();
        SystemInfo sys = (SystemInfo)context.getSession().get(Constants.SES_SYS_INFO);

        BaseAction action = (BaseAction)invocation.getAction();

        if (this.getAnnotation(invocation, SkipLoginCheck.class) == null) {
            // SkipLoginCheckアノテーションがない場合はログインチェックを行なう

            if (sys == null || sys.getUserInfo() == null) {
                // システム情報またはユーザ情報が存在しない場合は、セッション期限切れエラーを返す
                HashMap<String, List<String>> map = new HashMap<String, List<String>>();
                List<String> errMsg = new ArrayList<String>();
                errMsg.add(action.getText("ERR1001"));
                map.put("errMsg", errMsg);

                action.setScreenTitle(action.getText("scr.title.sessionTimeOut"));
                action.setFieldErrors(map);

                return "session_expired";
            }

        }

        try {
            if (sys != null) {
                if (sys.getUserInfo() != null) {
                    MDC.put("loginInfo.userId", sys.getUserInfo().getUserId());
                }
            }

            // 次のインターセプタを実行する
            return invocation.invoke();
        } finally {
            MDC.remove("loginInfo.userId");
        }

    }

}
