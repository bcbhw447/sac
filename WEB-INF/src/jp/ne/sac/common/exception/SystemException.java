package jp.ne.sac.common.exception;

/**
 * システム例外クラス.
 * <p>
 * アプリケーションで定義していない例外で使用するクラス。<br>
 *
 * @author m_hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
public class SystemException extends RuntimeException {

    /** serialVersionUID. */
    private static final long serialVersionUID = -8872560454071625885L;

    /**
     * コンストラクタ.
     */
    public SystemException() {
        super();
    }

    /**
     * コンストラクタ.
     *
     * @param message メッセージ
     */
    public SystemException(String message) {
        super(message);
    }

    /**
     * コンストラクタ.
     *
     * @param cause 例外
     */
    public SystemException(Throwable cause) {
        super(cause);
    }
}
