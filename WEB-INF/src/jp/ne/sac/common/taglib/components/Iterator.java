package jp.ne.sac.common.taglib.components;

import java.io.Writer;

import org.apache.struts2.components.IteratorComponent;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * 拡張Iteratorコンポーネントクラス.
 * <p>
 * Iteratorを拡張したIteratorコンポーネントクラス。<br>
 *
 * @author m_hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
public class Iterator extends IteratorComponent {

    /**
     * コンストラクタ.
     *
     * @param stack ValueStack
     */
    public Iterator(ValueStack stack) {
        super(stack);
    }

    /*
     * (non-Javadoc)
     * @see org.apache.struts2.components.IteratorComponent#start(java.io.Writer)
     */
    @Override
    public boolean start(Writer writer) {
        ResultList resultList = (ResultList)this.findAncestor(ResultList.class);
        this.value = resultList.getValue();
        ResultList.PageContorol pageControl = resultList.getPageControl();
        if (pageControl != null) {
            if (pageControl.getEnd() > 0) {
                this.setBegin(String.valueOf(pageControl.getStart() - 1));
                this.setEnd(String.valueOf(pageControl.getEnd() - 1));
            }
        }

        return super.start(writer);
    }

}
