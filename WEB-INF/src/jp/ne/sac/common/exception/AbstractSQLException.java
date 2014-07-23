package jp.ne.sac.common.exception;

/**
 * SQL例外基底クラス.
 * <p>
 * SQL例外の基底クラス。<br>
 *
 * @author m_hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
public abstract class AbstractSQLException extends ApplicationException {

    /** serialVersionUID. */
    private static final long serialVersionUID = 3767734658681960925L;

    /**
     * コンストラクタ.
     *
     * @param msgId MSGID
     */
    public AbstractSQLException(String msgId) {
        super(msgId);
    }
}
