package jp.ne.sac.common.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ne.sac.common.taglib.components.ExtRadio;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.RadioTag;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * 拡張Radioタグクラス.
 * <p>
 * 拡張Radioのタグクラス。<br>
 *
 * @author m_hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
public class ExtRadioTag extends RadioTag {

    /** serialVersionUID. */
    private static final long serialVersionUID = -4324455042995004L;

    /**
     * コンストラクタ.
     */
    public ExtRadioTag() {
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
     * @see org.apache.struts2.views.jsp.ui.RadioTag#getBean(com.opensymphony.xwork2.util.ValueStack,
     * javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new ExtRadio(stack, req, res);
    }

    /*
     * (non-Javadoc)
     * @see org.apache.struts2.views.jsp.ui.AbstractRequiredListTag#populateParams()
     */
    @Override
    protected void populateParams() {
        super.populateParams();
        ExtRadio radio = (ExtRadio)component;
        radio.setDisplayConfirm(displayConfirm);
        radio.setSessionKey(sessionKey);
    }

}
