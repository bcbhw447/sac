package jp.ne.sac.common.system;

/**
 * 定数クラス.
 * <p>
 * 定数定義クラス。<br>
 *
 * @author m_hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
public final class Constants {

    /**
     * コンストラクタ.
     */
    private Constants() {
    }

    /** パッケージ名. */
    public static final String PARENT_PACKAGE = "sac";

    /** セレクトボックスリスト. */
    public static final String SES_SELECT_LIST = "session.select_list";

    /** エラー画面用CSSファイル. */
    public static final String CSS_FILE_ERROR = "/common/error.css";

    /** システム情報. */
    public static final String SES_SYS_INFO = "sac";

    /** ユーザ情報. */
    public static final String SES_USER_INFO = "user";

    /**
     * セレクトボックスリスト.
     * <p>
     * セレクトボックスリストの列挙型クラス。<br>
     *
     * @author TAKT 濱岡
     * @version 1.0
     * @since 2011/06/13
     */
    public static enum SELECT_LIST {

        // 共通定義マスタ以外のリスト
    	/** カテゴリリスト. */
    	CATEGORY("category", ""),

    	/** 種別リスト. */
    	TYPE("type", "");

        /**
         * セレクトボックスリスト名.
         */
        private final String listName;

        /**
         * マスタコード.
         */
        private final String masterCd;

        /**
         * コンストラクタ.
         *
         * @param listName セレクトボックス名
         * @param masterCd マスタコード
         */
        private SELECT_LIST(String listName, String masterCd) {
            this.listName = listName;
            this.masterCd = masterCd;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return this.listName;
        }

        /**
         * マスタコードを返します.
         *
         * @return マスタコード
         */
        public String getMasterCd() {
            return this.masterCd;
        }
    }

    /**
     * メール関連定数.
     * <p>
     * メール関連の列挙型クラス。<br>
     *
     * @author TAKT 濱岡
     * @version 1.0
     * @since 2011/06/21
     */
    public static enum MAIL {

        /** 送信先設定用. */
        MAIL_TO("TO"),

        /** 送信先設定用. */
        MAIL_CC("CC"),

        /** 送信先設定用. */
        MAIL_BCC("BCC"),

        /** Fromヘッダ設定用. */
        MAIL_FROM("FROM"),

        /** 件名設定用. */
        MAIL_SUBJECT("SUBJECT"),

        /** 本文設定用. */
        MAIL_TEXT("TEXT"),

        /** 置換文字START. */
        MAIL_CHG_START("${"),

        /** 置換文字END. */
        MAIL_CHG_END("}$"),

        /** 置換文字ループ用START. */
        MAIL_CHG_LOOP_START("@${"),

        /** 置換文字ループ用END. */
        MAIL_CHG_LOOP_END("}$@"),

        /** メール内置換用改行無マーク. */
        MAIL_CHG_NO_CR("$<NO_CR>$"),

        /** メール内置換用改行マーク. */
        MAIL_CHG_CR("$<CR>$"),

        /** メール内置換用 NOT NULL比較. */
        MAIL_CHG_IS_NOT_NULL("$<IS_NOT_NULL:"),

        /** メール内置換用 NULL比較. */
        MAIL_CHG_IS_NULL("$<IS_NULL:"),

        /** メール内置換用 特別キー閉じタグ. */
        MAIL_CHG_SPECIAL_KEY_SUF(">$"),

        /** 改行コード. */
        CODE_CR("\r\n"),

        /** ロケール指定. */
        MAIL_LOCAL("LOCALE");

        /**
         * メール関連コード名.
         */
        private final String text;

        /**
         * コンストラクタ.
         *
         * @param text メール関連項目
         */
        private MAIL(String text) {
            this.text = text;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return this.text;
        }
    }

    // ===============================================================
    // 共通定数マスタ(定数名)
    // ===============================================================
    /** SMTPサーバ名. */
    public static final String CONST_MEI_SMTP_SERVER = "SMTP_SERVER";
    /** SMTPポート名. */
    public static final String CONST_MEI_SMTP_PORT = "SMTP_PORT";
    /** プロキシサーバ名. */
    public static final String CONST_MEI_PROXY_NAME_HTTP = "HTTP_PROXY_NAME";
    /** プロキシサーバ ポート番号. */
    public static final String CONST_MEI_PROXY_PORT_HTTP = "HTTP_PROXY_PORT";
    /** プロキシサーバ名. */
    public static final String CONST_MEI_PROXY_NAME_HTTPS = "HTTPS_PROXY_NAME";
    /** プロキシサーバ ポート番号. */
    public static final String CONST_MEI_PROXY_PORT_HTTPS = "HTTPS_PROXY_PORT";

    /** アクセス可能IPアドレス. */
    public static final String CONST_ACCESSABLE_IP_ADDRESS = "ACCESSABLE_IP_ADDRESS%";

    /** SSO用Cookieのドメイン. */
    public static final String CONST_DOMAIN_FOR_COOKIE = "DOMAIN_FOR_COOKIE";

    /** 講師スケジュール画面 検索対象年の(TO値)設定 */
    public static final String CONST_SHEDULE_SELLIST_YEAR = "SHEDULE_SELLIST_YEAR";
    /** スケジュール登録開始年 */
    public static final String CONST_SHEDULE_START_YEAR = "SHEDULE_START_YEAR";

    //----------------------------------------------------------------
    // アップロードファイル関連
    //----------------------------------------------------------------
    /** 支払書アップロード **/
    /** ファイルPATH. */
    public static final String CONST_SHIHARAISYO_FILE_PATH = "SHIHARAISYO_FILE_PATH";
    /** ファイルMAXサイズ. */
    public static final String CONST_SHIHARAISYO_FILE_MAX_SIZE = "SHIHARAISYO_FILE_MAX_SIZE";
    /** ファイル拡張子. */
    public static final String CONST_SHIHARAISYO_FILE_EXTENSION = "SHIHARAISYO_FILE_EXTENSION";

    /** 配布資料アップロード **/
    /** 一時保存ファイルPATH. */
    public static final String CONST_HAIFU_SHIRYO_FILE_TEMP_PATH = "HAIFU_SHIRYO_FILE_TEMP_PATH";
    /** ファイルPATH. */
    public static final String CONST_HAIFU_SHIRYO_FILE_PATH = "HAIFU_SHIRYO_FILE_PATH";
    /** ファイルMAXサイズ. */
    public static final String CONST_HAIFU_SHIRYO_FILE_MAX_SIZE = "HAIFU_SHIRYO_FILE_MAX_SIZE";
    /** ファイル拡張子. */
    public static final String CONST_HAIFU_SHIRYO_FILE_EXTENSION = "HAIFU_SHIRYO_FILE_EXTENSION";
    /** ダウンロード時ファイル名ヘッダ. */
    public static final String CONST_HAIFU_SHIRYO_FILE_NAME_HEADER = "配布資料";

    /** 領収書アップロード **/
    /** 一時保存ファイルPATH. */
    public static final String CONST_RYOSYUSYO_FILE_TEMP_PATH = "RYOSYUSYO_FILE_TEMP_PATH";
    /** ファイルPATH. */
    public static final String CONST_RYOSYUSYO_FILE_PATH = "RYOSYUSYO_FILE_PATH";
    /** ファイルMAXサイズ. */
    public static final String CONST_RYOSYUSYO_FILE_MAX_SIZE = "RYOSYUSYO_FILE_MAX_SIZE";
    /** ファイル拡張子. */
    public static final String CONST_RYOSYUSYO_FILE_EXTENSION = "RYOSYUSYO_FILE_EXTENSION";
    /** ダウンロード時ファイル名ヘッダ. */
    public static final String CONST_RYOSYUSYO_FILE_NAME_HEADER = "領収書";

    // ===============================================================
    // 共通定数
    // ===============================================================
    //----------------------------------------------------------------
    // フラグ値
    //----------------------------------------------------------------
    /** フラグON. */
    public static final int FLG_ON = 1;
    /** フラグOFF. */
    public static final int FLG_OFF = 0;

    /** フラグON(String). */
    public static final String FLG_ON_STRING = "1";
    /** フラグOFF(String). */
    public static final String FLG_OFF_STRING = "0";

    /** フラグtrue. */
    public static final boolean FLG_TRUE = true;
    /** フラグfalse. */
    public static final boolean FLG_FALSE = false;

    /** フラグTRUE(String). */
    public static final String FLG_TRUE_STRING = Boolean.TRUE.toString();
    /** フラグFALSE(String). */
    public static final String FLG_FALSE_STRING = Boolean.FALSE.toString();

    //----------------------------------------------------------------
    // パスワード区分
    //----------------------------------------------------------------
    /** パスワード区分：一般. */
    public static final String PASSWORD_KBN_IPPAN = "1";
    /** パスワード区分：GCE管理者. */
    public static final String PASSWORD_KBN_GCE_ADMIN = "2";
    /** パスワード区分：GMS管理者. */
    public static final String PASSWORD_KBN_GMS_ADMIN = "3";
    /** パスワード区分：管理者. */
    public static final String PASSWORD_KBN_ADMIN = "9";

    //----------------------------------------------------------------
    // デリミタ
    //----------------------------------------------------------------
    /** デリミタ1. */
    public static final String DELIMITER1 = ".";
    /** デリミタ2. */
    public static final String DELIMITER2 = ":";
    /** デリミタ3. */
    public static final String DELIMITER3 = "-";

    //----------------------------------------------------------------
    // 日英区分
    //----------------------------------------------------------------
    /** 日英区分 日. */
    public static final String NICHIE_KBN_JPN = "0";
    /** 日英区分 英. */
    public static final String NICHIE_KBN_ENG = "1";

    //----------------------------------------------------------------
    // ファイル区分
    //----------------------------------------------------------------
    /** ファイル区分 配布資料. */
    public static final String FILE_KBN_HAIFU_SHIRYO = "01";
    /** ファイル区分 支払書. */
    public static final String FILE_KBN_SHIHARAISYO = "02";
    /** ファイル区分 領収書. */
    public static final String FILE_KBN_RYOSYUSYO = "03";

    /** DBに登録するText型の最大バイト数 */
    public static final int TEXT_MAX_LENGTH = 1000;

    // ===============================================================
    // CSV出力関連
    // ===============================================================
    /** CSV出力エンコード */
    public static final String ENCODING = "Windows-31J";
    /** CSV改行コード */
    public static final String NEW_LINE = "\r\n";
    /** CSVカラム区切り文字 */
    public static final String SEPARATE = ",";
    /** CSVカラムの囲み文字列 */
    public static final String ENCLOSE_CHAR = "\"";
    /** CSVカラムの囲み文字列エスケープ */
    public static final String ESCAPE_ENCLOSE_CHAR = "\"\"";

    // ===============================================================
    // BuySmartFlexコード関連
    // ===============================================================
    /** 事業担当区分 株式会社グロービス. */
    public static final String COMMON_JIGYO_TANTO_KBN_KABU = "01";
    /** 事業担当区分 学校法人グロービス. */
    public static final String COMMON_JIGYO_TANTO_KBN_GAKU = "02";

    /** カードの有効期限が切れています. */
    public static final String CODE_CARDRES_DATE_ERR = "101";
    /** 短期間の間に同一注文で既に処理されたものがあります. */
    public static final String CODE_CARDRES_ALREADY_SHORT_TERM_ERR = "106";
    /** 同一注文で既に処理されたものがあります. */
    public static final String CODE_CARDRES_ALREADY_ERR = "106";
    /** 分割支払コード. */
    public static final String CODE_CARD_BUNKATSU = "61C";

    // ===============================================================
    // 採番コード
    // ===============================================================
    /** 精算管理ID. */
    public static final String SAIBAN_CD_SEISAN = "001";
    /** ファイル管理ID. */
    public static final String SAIBAN_CD_FILE = "011";

    // ===============================================================
    // カード種別（株式会社向け）
    // ===============================================================
    /** カード種別：VISA/MASTER. */
    public static final String SHINPAN_CD_VISA_MASTER_K = "C01";
    /** カード種別：JCB. */
    public static final String SHINPAN_CD_JCB_K = "C02";
    /** カード種別：AMEX. */
    public static final String SHINPAN_CD_AMEX_K = "C04";
    /** カード種別：ORICO. */
    public static final String SHINPAN_CD_ORICO_K = "C06";

    // ===============================================================
    // カード種別（学校法人向け）
    // ===============================================================
    /** カード種別：AMEX. */
    public static final String SHINPAN_CD_AMEX = "C54";
    /** カード種別：JCB. */
    public static final String SHINPAN_CD_JCB = "C52";
    /** カード種別：VISA/MASTER. */
    public static final String SHINPAN_CD_VISA_MASTER = "C51";
    /** カード種別：ORICO. */
    public static final String SHINPAN_CD_ORICO = "C56";


    // ===============================================================
    // テンプレートメールアドレス
    // ===============================================================
    //----------------------------------------------------------------
    // プロフィール変更
    //----------------------------------------------------------------
    /** メール種別：講師プロフィール変更受付メール. */
    public static final String MAIL_SBT_PROFILE = "F1";

    //-----------------------------------------------------------------
    // 外部ファイル分類
    //-----------------------------------------------------------------
    /** 外部ファイル分類：マスタコード. */
    public static final String COMMON_HTML_KBN = "007";
    /** 外部ファイル分類：各トップページ. */
    public static final String OUTER_HTML_KBN_TOP = "01";

    //-----------------------------------------------------------------
    // オンライン決済区分
    //-----------------------------------------------------------------
    /** オンライン決済区分：不要. */
    public static final String WEB_KESSAI_KBN_FUYO = "00";
    /** オンライン決済区分：必須. */
    public static final String WEB_KESSAI_KBN_HISSU = "01";
    /** オンライン決済区分：選択可. */
    public static final String WEB_KESSAI_KBN_SENTAKU = "02";

    //-----------------------------------------------------------------
    // 実施区分
    //-----------------------------------------------------------------
    /** 実施区分：未実施. */
    public static final String ACTION_KB_NOT = "00";
    /** 実施区分：実施済み. */
    public static final String ACTION_KB_DONE = "01";

    //-----------------------------------------------------------------
    // 講師スケジュール分類
    //-----------------------------------------------------------------
    /** 講師スケジュール分類：GMSクラス */
    public static final String BUNRUI_GMS = "GMSクラス";
    /** 講師スケジュール分類：研修 */
    public static final String BUNRUI_KENSYU = "研修";
    /** 講師スケジュール分類：アサイン不可 */
    public static final String BUNRUI_FUKA = "アサイン不可";

    /** イメージファイルパス：GMSクラス */
    public static final String IMG_PATH_GMS = "/img/gms.png";
    /** イメージファイルパス：GMSクラス(英) */
    public static final String IMG_PATH_GMS_E = "/img/gms_e.png";
    /** イメージファイルパス：研修 */
    public static final String IMG_PATH_KENSYU = "/img/gce.png";
    /** イメージファイルパス：研修(英) */
    public static final String IMG_PATH_KENSYU_E = "/img/gce_e.png";
    /** イメージファイルパス：アサイン不可 */
    public static final String IMG_PATH_FUKA = "/img/fuka.png";
    /** イメージファイルパス：アサイン不可(英) */
    public static final String IMG_PATH_FUKA_E = "/img/fuka_e.png";

    //-----------------------------------------------------------------
    // 立替費用精算区分
    //-----------------------------------------------------------------
    /** 立替費用精算区分：企業研修 */
    public static final String TATEKAE_KBN_KENSYU = "01";
    /** 立替費用精算区分：その他 */
    public static final String TATEKAE_KBN_OTHER = "02";

}

