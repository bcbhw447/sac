package jp.ne.sac.common.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ne.sac.common.taglib.components.ExtCheckboxList;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.CheckboxListTag;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * 拡張CheckboxListタグクラス.
 * <p>
 * 拡張CheckboxListのタグクラス。<br>
 *
 * @author m_hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
public class ExtCheckboxListTag extends CheckboxListTag {

    /** serialVersionUID. */
    private static final long serialVersionUID = 2179103789046047854L;

    /**
     * コンストラクタ.
     */
    public ExtCheckboxListTag() {
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
     * @see org.apache.struts2.views.jsp.ui.CheckboxListTag#getBean(com.opensymphony.xwork2.util.ValueStack,
     * javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new ExtCheckboxList(stack, req, res);
    }

    /*
     * (non-Javadoc)
     * @see org.apache.struts2.views.jsp.ui.AbstractRequiredListTag#populateParams()
     */
    @Override
    protected void populateParams() {
        super.populateParams();
        ExtCheckboxList checkBoxList = (ExtCheckboxList)component;
        checkBoxList.setDisplayConfirm(displayConfirm);
        checkBoxList.setSessionKey(sessionKey);
    }

}
