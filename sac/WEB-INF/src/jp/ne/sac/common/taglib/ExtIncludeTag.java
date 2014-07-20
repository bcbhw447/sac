package jp.ne.sac.common.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ne.sac.common.taglib.components.ExtInclude;

import org.apache.struts2.components.Component;
import org.apache.struts2.components.Include;
import org.apache.struts2.views.jsp.IncludeTag;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * 拡張Includeタグクラス.
 * <p>
 * 拡張Includeのタグクラス。<br>
 *
 * @author m_hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
public class ExtIncludeTag extends IncludeTag {

    /** serialVersionUID. */
    private static final long serialVersionUID = -22019391743263753L;

    /**
     * コンストラクタ.
     */
    public ExtIncludeTag() {
    }

    /** propertiesファイル定義. */
    private String name;

    /**
     * propertiesファイル定義を設定します.
     *
     * @param name propertiesファイル定義
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * (non-Javadoc)
     * @see org.apache.struts2.views.jsp.ui.TextFieldTag#getBean(com.opensymphony.xwork2.util.ValueStack,
     * javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new ExtInclude(stack, req, res);
    }

    /*
     * (non-Javadoc)
     * @see org.apache.struts2.views.jsp.ui.TextFieldTag#populateParams()
     */
    @Override
    protected void populateParams() {
        ActionSupport as = new ActionSupport();
        super.populateParams();
        String rootPath = as.getText("path.top.html");
        String value = as.getText(name);
        ((Include)component).setValue(rootPath + "/" + value);
    }

}
