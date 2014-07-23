package jp.ne.sac.common.system;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * パスワード暗号化処理.
 * <p>
 * パスワード暗号化します。<br>
 *
 * @author M.Hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
public class DefaultPasswordEncoder implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = 2947515482265744272L;

    /**
     * HEX_DIGITS.
     */
    private static final char[] HEX_DIGITS =
        {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     *
     */
    private final String encodingAlgorithm;

    /**
     * パスワードを暗号化します.
     *
     * @param password パスワード
     * @return 暗号化されたパスワード
     */
    public String encode(final String password) {

        if (password == null) {
            return null;
        }

        try {
            MessageDigest messageDigest = MessageDigest.getInstance(this.encodingAlgorithm);
            messageDigest.update(password.getBytes());

            final byte[] digest = messageDigest.digest();

            return getFormattedText(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new SecurityException(e);
        }
    }

    /**
     * @param bytes bytes
     * @return String
     */
    private String getFormattedText(byte[] bytes) {
        final StringBuilder buf = new StringBuilder(bytes.length * 2);

        for (int j = 0; j < bytes.length; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }

    /**
     * @param encodingAlgorithm encodingAlgorithm
     */
    public DefaultPasswordEncoder(final String encodingAlgorithm) {
        this.encodingAlgorithm = encodingAlgorithm;
    }
}
