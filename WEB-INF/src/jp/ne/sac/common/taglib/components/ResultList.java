package jp.ne.sac.common.taglib.components;

import java.io.Writer;
import java.math.BigDecimal;
import java.util.List;
import java.util.MissingResourceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ne.sac.common.base.BaseCriteriaModel;
import jp.ne.sac.common.system.CommonProperties;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.components.ClosingUIBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * 表示一覧制御用コンポーネントクラス.
 * <p>
 * 表示一覧制御用のコンポーネントクラス。<br>
 *
 * @author m_hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
public class ResultList extends ClosingUIBean {

    /** 開始テンプレート名. */
    private static final String OPEN_TEMPLATE = "resultList";

    /** 終了テンプレート名. */
    private static final String TEMPLATE = "resultList-close";

    /** 最大件数. */
    // public int MAX_RECORDS_COUNT = PropertyMst.getInstance().getInt(KEY.MAXSEARCHNUM);
    public int MAX_RECORDS_COUNT = this.setMaxRecordsCount();

    /** 1ページ表示件数. */
    // public int PAGE_RECORDS_COUNT = PropertyMst.getInstance().getInt(KEY.DISPLAYNUM);
    public int PAGE_RECORDS_COUNT = this.setPageRecordsCount();

    /** 対象リスト. */
    private String value;

    /** ページ制御有無. */
    private String paging;

    /** 対象リスト. */
    private List<Object> list;

    /** 取得件数. */
    private String count;

    /** ページ制御情報. */
    private PageContorol pageControl;

    /**
     * コンストラクタ.
     *
     * @param stack ValueStack
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    public ResultList(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }

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
     * 対象リストを取得します.
     *
     * @return 対象リスト
     */
    public List<Object> getList() {
        return list;
    }

    /**
     * 対象リストを設定します.
     *
     * @param list 対象リスト
     */
    public void setList(List<Object> list) {
        this.list = list;
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

    /**
     * ページ制御情報を取得します.
     *
     * @return ページ制御情報
     */
    public PageContorol getPageControl() {
        return pageControl;
    }

    /**
     * ページ制御情報を設定します.
     *
     * @param pageControl ページ制御情報
     */
    public void setPageControl(PageContorol pageControl) {
        this.pageControl = pageControl;
    }

    /*
     * (non-Javadoc)
     * @see org.apache.struts2.components.ClosingUIBean#getDefaultOpenTemplate()
     */
    @Override
    public String getDefaultOpenTemplate() {
        return OPEN_TEMPLATE;
    }

    /*
     * (non-Javadoc)
     * @see org.apache.struts2.components.UIBean#getDefaultTemplate()
     */
    @Override
    protected String getDefaultTemplate() {
        return TEMPLATE;
    }

    /*
     * (non-Javadoc)
     * @see org.apache.struts2.components.ClosingUIBean#start(java.io.Writer)
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean start(Writer writer) {
        // 対象リストを取得する

        this.list = (List<Object>)this.findValue(this.value);

        boolean result = super.start(writer);

        if ((this.list == null) || (this.list.size() == 0)) {
            return false;
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * @see org.apache.struts2.components.UIBean#evaluateExtraParams()
     */
    @Override
    protected void evaluateExtraParams() {
        super.evaluateExtraParams();

        this.addParameter("list", this.list);
        int count = 0;
        // 最大件数チェックを行う
        if ((this.list != null)) {
            if (StringUtils.isNotEmpty(this.count)) {
                count = Integer.parseInt(findString(this.count));
            } else {
                count = this.list.size();
            }
            if (count == 0) {
                // 該当データがない場合
                this.addParameter("noResult", Boolean.TRUE);

            } else if (count > this.MAX_RECORDS_COUNT) {
                // 最大件数を超えている場合はエラー処理を行う
                this.addParameter("maxOver", Boolean.TRUE);
                count = this.MAX_RECORDS_COUNT;
            }
        }

        // Criteriaを取得する
        BaseCriteriaModel.BaseCriteria criteria = (BaseCriteriaModel.BaseCriteria)this.stack.findValue("criteria",
                BaseCriteriaModel.BaseCriteria.class);
        String pPage = null;
        String pSort = null;
        if ((criteria != null)) {
            pPage = String.valueOf(criteria.getPage());
            pSort = criteria.getSort();

            if (StringUtils.isNotEmpty(criteria.getAction())) {
                // アクションが空でない(ページ切替またはソート)場合は最大件数チェックは無効とする
                this.addParameter("maxOver", Boolean.FALSE);
            }

            // ページ制御
            Boolean bPaging = (Boolean)this.findValue(this.paging, Boolean.class);
            if ((bPaging != null) && bPaging.booleanValue()) {
                // ページ制御を行う
                if (StringUtils.isEmpty(criteria.getAction())) {
                    // アクションが空の場合は1ページ目を表示する
                    criteria.setPage(1);
                }
                this.pageControl = new PageContorol(criteria.getPage(), this.PAGE_RECORDS_COUNT, count);
                this.addParameter("paging", this.pageControl);
            }
        }
        this.addParameter("page", StringUtils.defaultString(pPage));
        this.addParameter("sort", StringUtils.defaultString(pSort));
    }

    /**
     * 最大件数設定処理.<br>
     *
     * @return 最大件数
     */
    private int setMaxRecordsCount() {

        try {
            // 画面固有の値が設定されていた場合、そちらを優先する
            return CommonProperties.getInstance().getInt(
                    "count.max." + this.stack.findString(ActionContext.ACTION_NAME));
        } catch (MissingResourceException e) {
            // 何も行わない
        }

        return CommonProperties.getInstance().getInt("count.max.default");
    }

    /**
     * 1ページ表示件数設定処理.<br>
     *
     * @return 1ページ表示件数
     */
    private int setPageRecordsCount() {

        try {
            // 画面固有の値が設定されていた場合、そちらを優先する
            return CommonProperties.getInstance().getInt(
                    "count.page." + this.stack.findString(ActionContext.ACTION_NAME));
        } catch (MissingResourceException e) {
            // 何も行わない
        }

        return CommonProperties.getInstance().getInt("count.page.default");
    }

    /**
     * ページ制御情報保持クラス.
     * <p>
     * ページ制御情報を保持するクラス。<br>
     *
     * @author TAKT 濱岡
     * @version 1.0
     * @since 2011/05/24
     */
    public static class PageContorol {

        /** 現在ページの前nページを表示する. */
        private static final int DISPLAY_PAGES_PREV = 4;

        /** 現在ページの後nページを表示する. */
        private static final int DISPLAY_PAGES_NEXT = 4;

        /** 件数. */
        private final int count;

        /** 最大ページ番号. */
        private int maxPage;

        /** 表示開始ページ番号. */
        private int startPage;

        /** 表示終了ページ番号. */
        private int endPage;

        /** 現在のページ番号. */
        private int page;

        /** 表示開始行番号. */
        private int start;

        /** 表示終了行番号. */
        private int end;

        /** 表示開始行番号(画面表示用). */
        private int startNum;

        /** 表示終了行番号(画面表示用). */
        private int endNum;

        /**
         * コンストラクタ.
         *
         * @param page 現在のページ番号
         * @param pageCount 1ページ表示件数
         * @param count 件数
         */
        public PageContorol(int page, int pageCount, int count) {

            this.page = page;
            this.count = count;

            // 最大ページを求める
            BigDecimal bCount = new BigDecimal(count);
            BigDecimal bPageCount = new BigDecimal(pageCount);
            // 最大ページ = (件数 - 1) / 1ページ表示件数 + 1
            BigDecimal bMaxPage = bCount.subtract(BigDecimal.ONE).divide(bPageCount, 0, BigDecimal.ROUND_DOWN);
            bMaxPage = bMaxPage.add(BigDecimal.ONE);
            this.maxPage = bMaxPage.intValue();
            if (this.maxPage < 1) {
                this.maxPage = 1;
            }

            // 現在ページ番号を調整する
            if (this.page < 1) {
                this.page = 1;
            } else if (this.page > this.maxPage) {
                this.page = this.maxPage;
            }

            // 開始ページを求める
            this.startPage = page - DISPLAY_PAGES_PREV;
            if (this.startPage < 1) {
                this.startPage = 1;
            }
            // 終了ページを求める
            this.endPage = this.page + DISPLAY_PAGES_NEXT;
            if (this.endPage > this.maxPage) {
                // 最大ページを超える場合は最大ページを終了ページとする
                this.endPage = this.maxPage;
            }

            // 開始行、終了行を求める
            if (count == 0) {
                this.start = 0;
                this.end = 0;
            } else {
                this.start = 1;
                this.end = this.page * pageCount;
                if (this.end > count) {
                    this.end = pageCount - (pageCount * page - count);
                } else {
                    this.end = pageCount;
                }
            }

            // 表示用の行番号を求める
            this.startNum = (this.page - 1) * pageCount + 1;
            this.endNum = this.page * pageCount;
            if (this.endNum > count) {
                this.endNum = count;
            }
        }

        /**
         * 件数を取得します.
         *
         * @return 件数
         */
        public int getCount() {
            return count;
        }

        /**
         * 最大ページ番号を取得します.
         *
         * @return 最大ページ番号
         */
        public int getMaxPage() {
            return maxPage;
        }

        /**
         * 最大ページ番号を設定します.
         *
         * @param maxPage 最大ページ番号
         */
        public void setMaxPage(int maxPage) {
            this.maxPage = maxPage;
        }

        /**
         * 表示開始ページ番号を取得します.
         *
         * @return 表示開始ページ番号
         */
        public int getStartPage() {
            return startPage;
        }

        /**
         * 表示開始ページ番号を設定します.
         *
         * @param startPage 表示開始ページ番号
         */
        public void setStartPage(int startPage) {
            this.startPage = startPage;
        }

        /**
         * 表示終了ページ番号を取得します.
         *
         * @return 表示終了ページ番号
         */
        public int getEndPage() {
            return endPage;
        }

        /**
         * 表示終了ページ番号を設定します.
         *
         * @param endPage 表示終了ページ番号
         */
        public void setEndPage(int endPage) {
            this.endPage = endPage;
        }

        /**
         * 現在のページ番号を取得します.
         *
         * @return 現在のページ番号
         */
        public int getPage() {
            return page;
        }

        /**
         * 現在のページ番号を設定します.
         *
         * @param page 現在のページ番号
         */
        public void setPage(int page) {
            this.page = page;
        }

        /**
         * 表示開始行番号を取得します.
         *
         * @return 表示開始行番号
         */
        public int getStart() {
            return start;
        }

        /**
         * 表示開始行番号を設定します.
         *
         * @param start 表示開始行番号
         */
        public void setStart(int start) {
            this.start = start;
        }

        /**
         * 表示終了行番号を取得します.
         *
         * @return 表示終了行番号
         */
        public int getEnd() {
            return end;
        }

        /**
         * 表示終了行番号を設定します.
         *
         * @param end 表示終了行番号
         */
        public void setEnd(int end) {
            this.end = end;
        }

        /**
         * 表示開始行番号(表示用)を取得します.
         *
         * @return 表示開始行番号(表示用)
         */
        public int getStartNum() {
            return startNum;
        }

        /**
         * 表示開始行番号(表示用)を設定します.
         *
         * @param startNum 表示開始行番号(表示用)
         */
        public void setStartNum(int startNum) {
            this.startNum = startNum;
        }

        /**
         * 表示終了行番号(表示用)を取得します.
         *
         * @return 表示終了行番号(表示用)
         */
        public int getEndNum() {
            return endNum;
        }

        /**
         * 表示終了行番号(表示用)を設定します.
         *
         * @param endNum 表示終了行番号(表示用)
         */
        public void setEndNum(int endNum) {
            this.endNum = endNum;
        }
    }

}
