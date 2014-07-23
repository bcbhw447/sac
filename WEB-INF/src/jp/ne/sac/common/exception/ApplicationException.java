package jp.ne.sac.common.exception;

/**
 * アプリケーション例外クラス.
 * <p>
 * アプリケーションで定義している例外で使用するクラス。<br>
 *
 * @author m_hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
public class ApplicationException extends RuntimeException {

    /** serialVersionUID. */
    private static final long serialVersionUID = 641179551812076303L;

    /**
     * コンストラクタ.
     *
     * @param message メッセージ
     */
    public ApplicationException(String message) {
        super(message);
    }

    /**
     * コンストラクタ.
     *
     * @param cause 例外
     */
    public ApplicationException(Throwable cause) {
        super(cause);
    }
}
