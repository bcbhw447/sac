package jp.ne.sac.common.system;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * プロパティクラス.
 * <p>
 * 共通プロパティを格納するクラス。<br>
 *
 * @author m_hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
public final class CommonProperties {

    /** プロパティファイル名. */
    private static final String FILE_NAME = "common";

    /** CommonPropertiesインスタンス. */
    private static CommonProperties instance;

    /**
     * プライベートコンストラクタ.
     */
    private CommonProperties() {
        // 何もしない
    }

    /**
     * CommonPropertiesインスタンス取得処理.<br>
     *
     * @return CommonPropertiesインスタンス
     */
    public static synchronized CommonProperties getInstance() {
        if (instance == null) {
            instance = new CommonProperties();
        }
        return instance;
    }

    /**
     * 設定値取得処理.<br>
     *
     * @param key キー値
     * @return 設定値
     */
    public String getString(String key) {
        return ResourceBundle.getBundle(FILE_NAME).getString(key);
    }

    /**
     * 設定値取得処理(ロケール対応).<br>
     *
     * @param key キー値
     * @param locale ロケール
     * @return 設定値
     */
    public String getString(String key, Locale locale) {
        if (locale == null) {
            return  getString(key);
        } else {
            return ResourceBundle.getBundle(FILE_NAME, locale).getString(key);
        }
    }

    /**
     * 設定値取得処理.<br>
     *
     * @param key キー
     * @return 設定値
     */
    public int getInt(String key) {
        return Integer.parseInt(this.getString(key));
    }
}
