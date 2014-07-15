package jp.ne.sac.common.system;

import jp.ne.sac.common.dao.DAO;

import org.apache.commons.lang3.StringUtils;

/**
 * 共通関数クラス.
 * <p>
 * 共通関数を定義したクラス。<br>
 *
 * @author m_hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
public final class Commons {

    /**
     * コンストラクタ.
     */
    private Commons() {
    }

    /**
     * 暗号化したパスワードを取得する処理.<br>
     *
     * @param dao DAO
     * @param password パスワード
     * @return String 暗号化したパスワード
     */
    public static String getEncryptStr(DAO dao, String password) {
        String result = "";

        if (StringUtils.isEmpty(password)) {
            return null;
        }

        result = (String)dao.selectOne("Common.getEncryptStr", password);

        return result;

    }

    /**
     * 暗号化されたパスワードを複合する処理.<br>
     * メモ：複合に『化』はつけないのが普通です。。。
     *
     * @param dao DAO
     * @param encPassword 暗号化されたパスワード
     * @return String 複合したパスワード
     */
    public static String getDecryptStr(DAO dao, String encPassword) {
        String result = "";

        if (StringUtils.isEmpty(encPassword)) {
            return null;
        }

        result = (String)dao.selectOne("Common.getDecryptStr", encPassword);

        return result;
    }

}
