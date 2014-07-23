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
public final class PropertyUtil {

	/** プロパティファイル名. */
	private static String fileName = "common";

	/** CommonPropertiesインスタンス. */
	private static PropertyUtil instance;

	/**
	 * プライベートコンストラクタ.
	 */
	private PropertyUtil() {
		// 何もしない
	}

	/**
	 * デフォルトコンストラクタ.
	 *
	 * @param fileName
	 */
	private PropertyUtil(String fileName) {
		PropertyUtil.fileName = fileName;
	}

	/**
	 * CommonPropertiesインスタンス取得処理.<br>
	 *
	 * @return CommonPropertiesインスタンス
	 */
	public static synchronized PropertyUtil getInstance() {
		if (instance == null) {
			instance = new PropertyUtil();
		}
		return instance;
	}

	/**
	 * CommonPropertiesインスタンス取得処理.<br>
	 *
	 * @param fileName ファイル名
	 * @return CommonPropertiesインスタンス
	 */
	public static synchronized PropertyUtil getInstance(String fileName) {
		if (instance == null) {
			instance = new PropertyUtil(fileName);
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
		return ResourceBundle.getBundle(fileName).getString(key);
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
			return getString(key);
		} else {
			return ResourceBundle.getBundle(fileName, locale).getString(key);
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
