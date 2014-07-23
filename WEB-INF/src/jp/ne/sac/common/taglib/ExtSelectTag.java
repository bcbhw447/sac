package jp.ne.sac.common.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ne.sac.common.taglib.components.ExtSelect;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.SelectTag;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * 拡張Selectタグクラス.
 * <p>
 * 拡張Selectのタグクラス。<br>
 *
 * @author m_hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
public class ExtSelectTag extends SelectTag {

    /** serialVersionUID. */
    private static final long serialVersionUID = -1869499932578032511L;

    /**
     * コンストラクタ.
     */
    public ExtSelectTag() {
    }

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

    /*
     * (non-Javadoc)
     * @see org.apache.struts2.views.jsp.ui.SelectTag#getBean(com.opensymphony.xwork2.util.ValueStack,
     * javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new ExtSelect(stack, req, res);
    }

    /*
     * (non-Javadoc)
     * @see org.apache.struts2.views.jsp.ui.SelectTag#populateParams()
     */
    @Override
    protected void populateParams() {
        super.populateParams();
        ExtSelect select = (ExtSelect)component;
        select.setEmptyOption(emptyOption);
        select.setHeaderKey(headerKey);
        select.setHeaderValue(headerValue);
        select.setMultiple(multiple);
        select.setSize(size);
        select.setDisplayConfirm(displayConfirm);
        select.setSessionKey(sessionKey);
    }

}
