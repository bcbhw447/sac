package jp.ne.sac.common.exception;

/**
 * 更新例外クラス.
 * <p>
 * SQLのUPDATE文で更新できなかった場合に発生する例外クラス。<br>
 *
 * @author m_hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
public class UpdateFailedException extends AbstractSQLException {

    /** serialVersionUID. */
    private static final long serialVersionUID = -3936516360729798325L;

    /**
     * コンストラクタ.
     *
     * @param msgId SQLID
     */
    public UpdateFailedException(String msgId) {
        super(msgId);
    }
}
