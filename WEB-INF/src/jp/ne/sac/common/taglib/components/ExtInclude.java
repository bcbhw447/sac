package jp.ne.sac.common.taglib.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * 拡張Includeコンポーネントクラス.
 * <p>
 * Includeを拡張したIncludeコンポーネントクラス。<br>
 *
 * @author m_hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
public class ExtInclude extends org.apache.struts2.components.Include {

    /**
     * コンストラクタ.
     *
     * @param stack ValueStack
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    public ExtInclude(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }

}
