package jp.ne.sac.common.system;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;

/**
 * 共通チェック関数クラス.
 * <p>
 * 共通チェック関数を定義したクラス。<br>
 *
 * @author m_hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
public final class CommonCheck {

    /**
     * コンストラクタ.
     */
    private CommonCheck() {
    }

    /**
     * 共通必須入力チェック処理.<br>
     *
     * @param strInput 画面入力情報
     * @return boolean false(入力有)、true(未入力)
     */
    public static boolean nullCheck(String strInput) {

        if (StringUtils.isEmpty(strInput) || StringUtils.isEmpty(strInput.trim())) {
            return true;
        }
        return false;
    }

    /**
     * 共通半角数チェック処理.<br>
     *
     * @param strInput 画面入力情報
     * @return boolean false(半角数字のみ、もしくが空)、true(半角数字以外の文字有)
     */
    public static boolean numberCheck(String strInput) {

        if (StringUtils.isEmpty(strInput)) {
            return false;
        }

        if (strInput.matches("^[0-9]+$")) {
            return false;
        }

        return true;

    }

    /**
     * 共通半角英数チェック処理.<br>
     *
     * @param strInput 画面入力情報
     * @return boolean false(半角英数字のみ、もしくが空)、true(半角数字以外の文字有)
     */
    public static boolean singleByteCheck(String strInput) {

        if (StringUtils.isEmpty(strInput)) {
            return false;
        }

        if (strInput.matches("^[0-9A-Za-z_]+$")) {
            return false;
        }
        return true;
    }

    /**
     * 整数桁チェック処理.<br>
     *
     * @param strInput 画面入力情報
     * @param intLength 比較バイト長
     * @return boolean false(制限内もしくは空、もしくは数値以外の入力)、true(制限を越えた入力)
     */
    public static boolean intLengthCheck(String strInput, int intLength) {

        if (StringUtils.isEmpty(strInput)) {
            return false;
        }

        try {
            if (String.valueOf(Long.parseLong(strInput)).length() != intLength) {
                return true;
            }
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    /**
     * 整数最大桁チェック処理.<br>
     *
     * @param strInput 画面入力情報
     * @param intLength 比較バイト長
     * @return boolean false(制限内もしくは空、もしくは数値以外の入力)、true(制限を越えた入力)
     */
    public static boolean intMaxLengthCheck(String strInput, int intLength) {

        if (StringUtils.isEmpty(strInput)) {
            return false;
        }

        try {
            if (String.valueOf(Long.parseLong(strInput)).length() > intLength) {
                return true;
            }
        } catch (Exception e) {
            return true;
        }
        return false;
    }
    /**
     * 整数最小桁チェック処理.<br>
     *
     * @param strInput 画面入力情報
     * @param intLength 比較バイト長
     * @return boolean false(制限内もしくは空、もしくは数値以外の入力)、true(制限を越えた入力)
     */
    public static boolean intMinLengthCheck(String strInput, int intLength) {

        if (StringUtils.isEmpty(strInput)) {
            return false;
        }

        try {
            if (String.valueOf(Long.parseLong(strInput)).length() < intLength) {
                return true;
            }
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    /**
     * 整数桁チェック(カンマ除去を行った文字列の長さのチェック).<br>
     *
     * @param strInput 画面入力情報
     * @param length 比較バイト長
     * @return @return boolean false(制限内もしくは空、もしくは数値以外の入力)、true(制限を越えた入力)
     */
    public static boolean intLengthNoCommaCheck(String strInput, int length) {

        if (StringUtils.isEmpty(strInput)) {
            return false;
        }

        return intLengthCheck(strInput.replaceAll(",", StringUtils.EMPTY), length);
    }
    /**
     * 小数桁チェック.<br>
     *
     * @param strInput 画面入力情報
     * @param length1 比較バイト長(整数値)
     * @param length2 比較バイト長(少数値)
     * @return boolean false(制限内もしくは空、もしくは少数以外の入力)、true(制限を越えた入力)
     */
    public static boolean decimalLengthCheck(String strInput, int length1, int length2) {

        if (StringUtils.isEmpty(strInput)) {
            return false;
        }

        String[] inpStrList = strInput.split(".");
        if (inpStrList.length != 2) {
            return false;
        }
        try {

            if (String.valueOf(Long.parseLong(inpStrList[0])).length() > length1
                    || String.valueOf(Long.parseLong(inpStrList[1])).length() > length2) {
                return true;
            }
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    /**
     * 文字長チェック.<br>
     *
     * @param strInput 画面入力情報
     * @param length 比較バイト長
     * @return boolean false(制限内もしくは空)、true(制限を越えた入力)
     */
    public static boolean lengthCheck(String strInput, int length) {

        if (StringUtils.isEmpty(strInput)) {
            return false;
        }

        int cnt = 0;
        char[] charInput = strInput.toCharArray();
        for (int i = 0; i < charInput.length; i++) {
            if (String.valueOf(charInput[i]).getBytes().length >= 2) {
                cnt += 2;
            } else {
                cnt += 1;
            }
        }

        if (cnt != length) {
            return true;
        }
        return false;
    }

    /**
     * 最小文字長チェック.<br>
     *
     * @param strInput 画面入力情報
     * @param length 比較バイト長
     * @return boolean false(制限内もしくは空)、true(制限を越えた入力)
     */
    public static boolean minLengthCheck(String strInput, int length) {

        if (StringUtils.isEmpty(strInput)) {
            return false;
        }

        int cnt = 0;
        char[] charInput = strInput.toCharArray();
        for (int i = 0; i < charInput.length; i++) {
            if (String.valueOf(charInput[i]).getBytes().length >= 2) {
                cnt += 2;
            } else {
                cnt += 1;
            }
        }

        if (cnt < length) {
            return true;
        }
        return false;
    }

    /**
     * 最大文字長チェック.<br>
     *
     * @param strInput 画面入力情報
     * @param length 比較バイト長
     * @return boolean false(制限内もしくは空)、true(制限に満たない入力)
     */
    public static boolean maxLengthCheck(String strInput, int length) {

        if (StringUtils.isEmpty(strInput)) {
            return false;
        }

        int cnt = 0;
        char[] charInput = strInput.toCharArray();
        for (int i = 0; i < charInput.length; i++) {
            if (String.valueOf(charInput[i]).getBytes().length >= 2) {
                cnt += 2;
            } else {
                cnt += 1;
            }
        }

        if (cnt > length) {
            return true;
        }
        return false;
    }

    /**
     * 日付チェック.<br>
     *
     * @param strInput 画面入力情報
     * @param format 日付のフォーマット
     * @param setLenienFlg false:行わない true:日付の妥当性チェックを行う
     * @return boolean false (制限内もしくは空)、true(制限を越えた入力)
     */
    public static boolean dateFormatCheck(String strInput, String format, boolean setLenienFlg) {

        if (StringUtils.isEmpty(strInput)) {
            return false;
        }
        if (numberCheck(strInput.replaceFirst("/", "").replaceFirst("/", ""))) {
            return true;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.setLenient(!setLenienFlg);
            sdf.parse(strInput);
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    /**
     * 日付From～Toチェック.<br>
     *
     * @param strFromInput 画面入力情報(FROM)
     * @param strToInput 画面入力情報(TO)
     * @return boolean false (制限内もしくは空)、true(制限を越えた入力)
     */
    public static boolean dateFromToCheck(String strFromInput, String strToInput) {

        if (StringUtils.isEmpty(strFromInput) || StringUtils.isEmpty(strToInput)) {
            return false;
        }
        String[] to = strToInput.split("/");
        String[] from = strFromInput.split("/");
        Calendar toDate = new GregorianCalendar();
        toDate.set(Integer.parseInt(to[0]), Integer.parseInt(to[1]), Integer.parseInt(to[2]));
        Calendar fromDate = Calendar.getInstance();
        fromDate.set(Integer.parseInt(from[0]), Integer.parseInt(from[1]), Integer.parseInt(from[2]));
        if (fromDate.after(toDate)) {
            return true;
        }
        return false;
    }

    /**
     * 全角文字チェック.<br>
     *
     * @param strInput 画面入力情報
     * @return boolean false(文字列がすべて２バイトもしくは空)、true(2バイトより小さいバイト数の文字の入力)
     */
    public static boolean doubleByteCharCheck(String strInput) {

        if (StringUtils.isEmpty(strInput)) {
            return false;
        }

        char[] charInput = strInput.toCharArray();
        for (int i = 0; i < charInput.length; i++) {
            if (String.valueOf(charInput[i]).getBytes().length < 2) {
                return true;
            }
        }

        return false;
    }

    /**
     * 半角文字チェック.<br>
     *
     * @param strInput 画面入力情報
     * @return boolean false(文字列がすべて半角英数字記号、もしくは空)、true(全角文字の入力、もしくは機種依存文字)
     */
    public static boolean singleByteCharCheck(String strInput) {

        if (StringUtils.isEmpty(strInput)) {
            return false;
        }

        if (!strInput.matches("^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]+$")) {
            return true;
        }

        return false;
    }

    /**
     * 特殊文字チェック.<br>
     *
     * @param strInput 画面入力情報
     * @return boolean false(文字列に特殊文字を含まないもしくは空)、true(特殊文字を含む)
     */
    public static boolean illegalCharCheck(String strInput) {

        if (StringUtils.isEmpty(strInput)) {
            return false;
        }

        for (int i = 0; i < strInput.length(); i++) {
            String strOne = strInput.substring(i, i + 1);
            if (CommonProperties.getInstance().getString("illegalChar").indexOf(strOne) != -1) {
                return true;
            }
        }
        return false;
    }

    /**
     * メールアドレス妥当性チェック.<br>
     *
     * @param strInput 画面入力情報
     * @return boolean false(文字列が妥当なメールアドレス、もしくは空)、true(全角入力、不正入力、)
     */
    public static boolean mailAddressCheck(String strInput) {

        int atfirst = strInput.indexOf("@");
        int atlast = strInput.lastIndexOf("@");
        int dot = strInput.lastIndexOf(".");

        // 未入力の場合はチェックしない
        if (StringUtils.isEmpty(strInput)) {
            return false;
        }

        // "@"マークが存在しない場合はエラー
        if (atfirst == -1) {
            return true;
        }

        // 不正文字チェック
        char[] charInput = strInput.toCharArray();
        for (int i = 0; i < charInput.length; i++) {
            if (String.valueOf(charInput[i]).getBytes().length != 1) {
                return true;
            }
            if (CommonProperties.getInstance().getString("mailIllegalChar").indexOf(charInput[i]) != -1) {
                return true;
            }
        }

        if ((atfirst != -1) && (atfirst == atlast) && // @が1つだけあり
                (atfirst != 0) && // かつその位置が0ではなく
                (dot != -1) && // かつ.が存在し
                (dot > atfirst + 1) && // かつ@と.の間に文字が存在し
                (dot < strInput.length() - 1)) { // かつ.の後に文字が存在
            return false;
        }

        return true;
    }

    /**
     * 郵便番号妥当性チェック.<br>
     *
     * @param strInput1 住所3桁
     * @param strInput2 住所4桁
     * @return boolean false(文字列が妥当な場合、もしくは空)、true(全角入力、不正入力、)
     */
    public static boolean yubinNoCheck(String strInput1, String strInput2) {

        // 関連必須入力チェック
        if (nullCheck(strInput1) && !nullCheck(strInput2)) {
            return true;
        }
        if (!nullCheck(strInput1) && nullCheck(strInput2)) {
            return true;
        }

        if (!nullCheck(strInput1)) {
            if (numberCheck(strInput1)) {
                return true;
            }

            if (strInput1.length() != 3) {
                return true;
            }

            // 不正文字チェック
            char[] charInput = strInput1.toCharArray();
            for (int i = 0; i < charInput.length; i++) {
                if (String.valueOf(charInput[i]).getBytes().length != 1) {
                    return true;
                }
                if (CommonProperties.getInstance().getString("illegalChar").indexOf(charInput[i]) != -1) {
                    return true;
                }
            }
        }

        if (!nullCheck(strInput2)) {
            if (numberCheck(strInput2)) {
                return true;
            }

            if (strInput2.length() != 4) {
                return true;
            }

            // 不正文字チェック
            char[] charInput = strInput2.toCharArray();
            for (int i = 0; i < charInput.length; i++) {
                if (String.valueOf(charInput[i]).getBytes().length != 1) {
                    return true;
                }
                if (CommonProperties.getInstance().getString("illegalChar").indexOf(charInput[i]) != -1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 電話番号妥当性チェック.<br>
     *
     * @param strInput1 画面入力情報(市外局番オブジェクト)
     * @param strInput2 画面入力情報(市内局番オブジェクト)
     * @param strInput3 画面入力情報(局番オブジェクト)
     * @return boolean false(文字列が妥当な局番、もしくは空)、true(全角入力、不正入力、桁数不正)
     */
    public static boolean telNoCheck(String strInput1, String strInput2, String strInput3) {

        // 関連必須入力チェック
        if (nullCheck(strInput1) && (!nullCheck(strInput2) || !nullCheck(strInput3))) {
            return true;
        }
        if (nullCheck(strInput2) && (!nullCheck(strInput1) || !nullCheck(strInput3))) {
            return true;
        }
        if (nullCheck(strInput3) && (!nullCheck(strInput1) || !nullCheck(strInput2))) {
            return true;
        }

        if (!nullCheck(strInput1)) {
            // 文字種チェック
            if (!strInput1.matches("^[0-9+-]+$")) {
                return true;
            }
            // 桁数チェック
            if (strInput1.length() > 10) {
                return true;
            }
        }

        if (!nullCheck(strInput2)) {
            // 文字種チェック
            if (!strInput2.matches("^[0-9]+$")) {
                return true;
            }
            // 桁数チェック
            if (strInput2.length() > 4) {
                return true;
            }
        }

        if (!nullCheck(strInput3)) {
            // 文字種チェック
            if (!strInput3.matches("^[0-9]+$")) {
                return true;
            }
            // 桁数チェック
            if (strInput3.length() != 4) {
                return true;
            }
        }

        return false;
    }

    /**
     * パスワードチェック.
     *
     * @param strInput 入力文字列
     * @return 英小、英大、数字が混在しているもしくは空の場合はfalse、混在していない場合はtrue
     */
    public static boolean passwordCheck(String strInput) {
        // パスワードの入力可能パターン
        String pattern = "^(?=.{3,})(?=.*?[a-z]+)(?=.*?[A-Z]+)(?=.*?[0-9]+).+$";

        if (StringUtils.isEmpty(strInput)) {
            return false;
        }

        if (!strInput.matches(pattern)) {
            // パターンに一致していない場合
            return true;
        }

        return false;
    }
}
