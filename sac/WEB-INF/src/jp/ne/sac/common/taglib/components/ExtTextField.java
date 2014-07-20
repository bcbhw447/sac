package jp.ne.sac.common.taglib.components;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.TextField;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * 拡張TextFieldコンポーネントクラス.
 * <p>
 * TextFieldを拡張したTextFieldコンポーネントクラス。<br>
 *
 * @author m_hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
public class ExtTextField extends TextField {

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

    /**
     * コンストラクタ.
     *
     * @param stack ValueStack
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    public ExtTextField(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }

    /*
     * (non-Javadoc)
     * @see org.apache.struts2.components.TextField#evaluateExtraParams()
     */
    @Override
    protected void evaluateExtraParams() {
        super.evaluateExtraParams();
        if (size != null) {
            addParameter("size", findString(size));
        }
        if (maxlength != null) {
            addParameter("maxlength", findString(maxlength));
        }
        if (readonly != null) {
            addParameter("readonly", findValue(readonly, Boolean.class));
        }
        if (displayConfirm != null) {
            addParameter("displayConfirm", findValue(displayConfirm, Boolean.class));
            addParameter("viewValue", this.getViewValue());
        }
    }

    /**
     * 画面表示値取得処理.<br>
     *
     * @return 画面表示値
     */
    private Object getViewValue() {

        Object nameValue = this.getNameValue();

        if (this.ftype == null || nameValue == null || ("").equals(nameValue)) {
            // フォーマットタイプが指定なしまたはフォーマット対象値が何も入力されていない場合
            return nameValue;
        }

        if ("number".equals(this.ftype)) {
            // フォーマットタイプが数値の場合
            try {
                // 数値変換を行う
                BigDecimal bg = new BigDecimal(nameValue.toString());
                DecimalFormat df = new DecimalFormat("#,###");
                if (this.fpattern != null) {
                    df.applyPattern(this.fpattern);
                }
                return df.format(bg);

            } catch (NumberFormatException e) {
                // 変換失敗時は元の値をそのまま渡す
                return nameValue;
            }
        }

        return nameValue;
    }

    /**
     * VALUE値取得処理.<br>
     *
     * @return VALUE値
     */
    @SuppressWarnings("rawtypes")
    private Object getNameValue() {

        Object values = null;

        if (parameters.containsKey("value")) {
            // valueが指定されていた場合、value値を優先する
            values = parameters.get("value");

        } else if (evaluateNameValue()) {
            // valueが指定されていない場合
            Class valueClazz = getValueClassType();
            if (valueClazz != null) {
                if (value != null) {
                    values = findValue(value, valueClazz);
                } else if (name != null) {
                    String expr = completeExpressionIfAltSyntax(name);
                    values = findValue(expr, valueClazz);
                }
            } else if (value != null) {
                values = findValue(value);
            } else if (name != null) {
                values = findValue(name);
            }
        }

        return values;
    }

}
