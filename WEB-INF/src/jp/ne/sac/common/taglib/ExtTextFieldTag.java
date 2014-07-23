package jp.ne.sac.common.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ne.sac.common.taglib.components.ExtTextField;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.TextFieldTag;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * 拡張TextFieldタグクラス.
 * <p>
 * 拡張TextFieldのタグクラス。<br>
 *
 * @author m_hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
public class ExtTextFieldTag extends TextFieldTag {

    /** serialVersionUID. */
    private static final long serialVersionUID = -22019391743263753L;

    /**
     * コンストラクタ.
     */
    public ExtTextFieldTag() {
    }

    /** 確認画面表示フラグ. */
    private String displayConfirm;

    /** フォーマットタイプ. */
    private String ftype;

    /** フォーマットパターン. */
    private String fpattern;

    /**
     * 確認画面表示フラグを設定します.
     *
     * @param displayConfirm 確認画面表示フラグ
     */
    public void setDisplayConfirm(String displayConfirm) {
        this.displayConfirm = displayConfirm;
    }

    /**
     * フォーマットタイプを設定します.
     *
     * @param ftype フォーマットタイプ
     */
    public void setFtype(String ftype) {
        this.ftype = ftype;
    }

    /**
     * フォーマットパターンを設定します.
     *
     * @param fpattern フォーマットパターン
     */
    public void setFpattern(String fpattern) {
        this.fpattern = fpattern;
    }

    /*
     * (non-Javadoc)
     * @see org.apache.struts2.views.jsp.ui.TextFieldTag#getBean(com.opensymphony.xwork2.util.ValueStack,
     * javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new ExtTextField(stack, req, res);
    }

    /*
     * (non-Javadoc)
     * @see org.apache.struts2.views.jsp.ui.TextFieldTag#populateParams()
     */
    @Override
    protected void populateParams() {
        super.populateParams();
        ExtTextField textField = (ExtTextField)component;
        textField.setMaxlength(maxlength);
        textField.setReadonly(readonly);
        textField.setSize(size);
        textField.setDisplayConfirm(displayConfirm);
        textField.setFtype(ftype);
        textField.setFpattern(fpattern);
    }

}
