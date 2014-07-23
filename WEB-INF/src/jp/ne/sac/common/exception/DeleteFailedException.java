package jp.ne.sac.common.exception;

/**
 * 削除例外クラス.
 * <p>
 * SQLのDELETE文で削除できなかった場合に発生する例外クラス。<br>
 *
 * @author m_hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
public class DeleteFailedException extends AbstractSQLException {

    /** serialVersionUID. */
    private static final long serialVersionUID = -1579066905599227909L;

    /**
     * コンストラクタ.
     *
     * @param msgId MSGID
     */
    public DeleteFailedException(String msgId) {
        super(msgId);
    }
}
