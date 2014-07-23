package jp.ne.sac.common.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ne.sac.common.taglib.components.Iterator;

import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * 拡張Iteratorタグクラス.
 * <p>
 * 拡張Iteratorのタグクラス。<br>
 *
 * @author m_hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
public class IteratorTag extends org.apache.struts2.views.jsp.IteratorTag {

    /** serialVersionUID. */
    private static final long serialVersionUID = -1432930044670764534L;

    /*
     * (non-Javadoc)
     * @see org.apache.struts2.views.jsp.IteratorTag#getBean(com.opensymphony.xwork2.util.ValueStack,
     * javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new Iterator(stack);
    }

}
