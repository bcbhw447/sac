package jp.ne.sac.common.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ne.sac.common.taglib.components.ExtFieldError;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.AbstractUITag;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * 拡張FieldErrorタグクラス.
 * <p>
 * 拡張FieldErrorのタグクラス。<br>
 *
 * @author m_hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
public class ExtFieldErrorTag extends AbstractUITag {

    /**
     * コンストラクタ.
     */
    public ExtFieldErrorTag() {
        escape = true;
    }

    /**
     * クラスインスタンス作成.
     *
     * @param stack ValueStack
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @return ExtFieldError
     */
    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new ExtFieldError(stack, req, res);
    }

    /**
     * 項目名を設定します.
     */
    protected void populateParams() {
        super.populateParams();
        ExtFieldError fieldError = (ExtFieldError)component;
        fieldError.setFieldName(fieldName);
        fieldError.setEscape(escape);
    }

    /**
     * 項目名を設定します.
     *
     * @param fieldName 項目名
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * エスケープフラグを設定します.
     *
     * @param escape エスケープフラグ
     */
    public void setEscape(boolean escape) {
        this.escape = escape;
    }

    /** serialVersionUID. */
    private static final long serialVersionUID = -182532967507726323L;

    /** 項目名. */
    protected String fieldName;

    /** エスケープフラグ. */
    protected boolean escape;
}

/*
 * DECOMPILATION REPORT Decompiled from: C:\workspace-gmba\portal\WEB-INF\lib\struts2-core-2.2.1.1.jar Total time: 65 ms
 * Jad reported messages/errors: Exit status: 0 Caught exceptions:
 */
