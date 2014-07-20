package jp.ne.sac.common.taglib.components;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Param.UnnamedParametric;
import org.apache.struts2.components.UIBean;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * 拡張FieldErrorコンポーネントクラス.
 * <p>
 * FieldErrorを拡張したFieldErrorコンポーネントクラス。<br>
 *
 * @author m_hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
public class ExtFieldError extends UIBean implements UnnamedParametric {

    /**
     * コンストラクタ.
     *
     * @param stack ValueStack
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    public ExtFieldError(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
        errorFieldNames = new ArrayList<String>();
        escape = true;
    }

    /**
     * テンプレート名を取得します.
     *
     * @return テンプレート名
     */
    protected String getDefaultTemplate() {
        return "extfielderror";
    }

    /**
     * パラメータセット.
     */
    protected void evaluateExtraParams() {
        super.evaluateExtraParams();
        if (errorFieldNames != null) {
            addParameter("errorFieldNames", errorFieldNames);
        }
        addParameter("escape", Boolean.valueOf(escape));
    }

    /**
     * エラー項目リストを追加します.
     *
     * @param value エラー項目
     */
    public void addParameter(Object value) {
        if (value != null) {
            errorFieldNames.add(value.toString());
        }
    }

    /**
     * エラー項目リストを取得します.
     *
     * @return エラー項目リスト
     */
    public List<String> getFieldErrorFieldNames() {
        return errorFieldNames;
    }

    /**
     * エラー項目リストを設定します.
     *
     * @param fieldName エラー項目リスト
     */
    public void setFieldName(String fieldName) {
        addParameter(fieldName);
    }

    /**
     * エスケープフラグを設定します.
     *
     * @param escape エスケープフラグ
     */
    public void setEscape(boolean escape) {
        this.escape = escape;
    }

    /** エラー項目リスト. */
    private List<String> errorFieldNames;

    /** エスケープフラグ. */
    private boolean escape;

    /** テンプレート. */
    @SuppressWarnings("unused")
	private static final String TEMPLATE = "extfielderror";

}
