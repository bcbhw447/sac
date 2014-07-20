package jp.ne.sac.common.taglib.components;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ne.sac.common.system.Constants;
import jp.ne.sac.common.system.bean.MultiLabelValueBean;

import org.apache.struts2.components.Select;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * 拡張Selectコンポーネントクラス.
 * <p>
 * Selectを拡張したSelectコンポーネントクラス。<br>
 *
 * @author m_hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
public class ExtSelect extends Select {

    /** 確認画面表示フラグ. */
    private String displayConfirm;

    /** セッションキー. */
    private String sessionKey;

    /**
     * 確認画面表示フラグを設定します.
     *
     * @param displayConfirm 確認画面表示フラグ
     */
    public void setDisplayConfirm(String displayConfirm) {
        this.displayConfirm = displayConfirm;
    }

    /**
     * セッションキーを設定します.
     *
     * @param sessionKey セッションキー
     */
    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    /**
     * コンストラクタ.
     *
     * @param stack ValueStack
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    public ExtSelect(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void evaluateExtraParams() {

        // 意図的に値を変更する
        this.setRequiredAttribute();

        super.evaluateExtraParams();
        if (emptyOption != null) {
            addParameter("emptyOption", findValue(emptyOption, Boolean.class));
        }
        if (multiple != null) {
            addParameter("multiple", findValue(multiple, Boolean.class));
        }
        if (size != null) {
            addParameter("size", findString(size));
        }
        if (headerKey != null && headerValue != null) {
            addParameter("headerKey", findString(headerKey));
            addParameter("headerValue", findString(headerValue));
        }
        if (displayConfirm != null) {
            addParameter("displayConfirm", findValue(displayConfirm, Boolean.class));
        }
        if (sessionKey != null) {
            addParameter("sessionKey", findString(sessionKey));
        }
    }

    /**
     * 属性設定処理.<br>
     */
    @SuppressWarnings("unchecked")
    private void setRequiredAttribute() {

        if (this.sessionKey == null) {
            // セッションキーが指定されていない場合、何も行わない
            return;
        }

        // セッションからリストを抽出する
        Map<String, Object> map = (Map<String, Object>)super.request.getSession().getAttribute(
                Constants.SES_SELECT_LIST);
        if (map.get(this.sessionKey) == null) {
            super.list = new MultiLabelValueBean();
        } else {
            super.list = map.get(this.sessionKey);
        }

        // 値を固定で設定する
        super.listKey = "value";

        if (Locale.US.equals(super.getStack().getContext().get(ActionContext.LOCALE))) {
            // ロケールが英語の場合、ラベルを英語にする
            super.listValue = "labelEn";
            return;
        }

        super.listValue = "labelJa";
    }
}
