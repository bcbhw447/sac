package jp.ne.sac.common.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ne.sac.common.taglib.components.ResultList;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * 表示一覧制御用タグクラス.
 * <p>
 * 表示一覧制御用のタグクラス。<br>
 *
 * @author m_hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
public class ResultListTag extends ComponentTagSupport {

    /** serialVersionUID. */
    private static final long serialVersionUID = 4799272144567814591L;

    /** 対象リスト. */
    private String value;

    /** ページ制御有無. */
    private String paging;

    /** 取得件数. */
    private String count;

    /**
     * 対象リストを取得します.
     *
     * @return 対象リスト
     */
    public String getValue() {
        return value;
    }

    /**
     * 対象リストを設定します.
     *
     * @param value 対象リスト
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * ページ制御有無を取得します.
     *
     * @return ページ制御有無
     */
    public String getPaging() {
        return paging;
    }

    /**
     * ページ制御有無を設定します.
     *
     * @param paging ページ制御有無
     */
    public void setPaging(String paging) {
        this.paging = paging;
    }

    /**
     * 取得件数を取得します.
     *
     * @return 取得件数
     */
    public String getCount() {
        return count;
    }

    /**
     * 取得件数を設定します.
     *
     * @param count 取得件数
     */
    public void setCount(String count) {
        this.count = count;
    }
    /*
     * (non-Javadoc)
     * @see org.apache.struts2.views.jsp.ComponentTagSupport#getBean(com.opensymphony.xwork2.util.ValueStack,
     * javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public Component getBean(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        return new ResultList(stack, request, response);
    }

    /*
     * (non-Javadoc)
     * @see org.apache.struts2.views.jsp.ComponentTagSupport#populateParams()
     */
    @Override
    protected void populateParams() {
        super.populateParams();

        ResultList resultList = (ResultList)this.getComponent();
        resultList.setValue(this.value);
        resultList.setPaging(this.paging);
        resultList.setCount(this.count);
    }

}
