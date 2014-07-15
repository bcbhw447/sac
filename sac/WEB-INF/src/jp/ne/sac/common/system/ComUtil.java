package jp.ne.sac.common.system;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import jp.ne.sac.common.exception.SystemException;

import org.apache.commons.lang.StringUtils;

/**
 * 共通関数クラス.
 * <p>
 * 共通関数を定義したクラス。<br>
 *
 * @author m_hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
public final class ComUtil {

    /**
     * コンストラクタ.
     */
    private ComUtil() {
    }

    /**
     * NULL変換処理.<br>
     *
     * @param str 文字列
     * @return String 文字列がNULLだった場合空文字、それ以外はそのまま返す.
     */
    public static String chgNullToBlank(String str) {
        if (str == null) {
            return "";
        } else {
            return str.trim();
        }
    }

    /**
     * 日付文字列をYYYY年MM月DD日フォーマットの文字列に変換し返す.<br>
     *
     * @param str 日付
     * @return 変換された文字列
     */
    public static String convDateToYMD(String str) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }

        return ComUtil.convDate2String(ComUtil.convString2Date(str),
                CommonProperties.getInstance().getString("fmt.date.datetoymd"));
    }

    /**
     * YYYYMMDD日付文字列をYYYY/MM/DDフォーマットの文字列に変換し返す.<br>
     *
     * @param str 文字列
     * @return 変換日付
     */
    public static String conv2YmdSlash(String str) {

        if (StringUtils.isEmpty(str)) {
            // 対象文字列が空の場合、何もしない
            return str;
        }

        return ComUtil.convDate2String(ComUtil.convString2Date(str),
                CommonProperties.getInstance().getString("fmt.date.ymdWithSlash"));
    }

    /**
     * 開催時間（nn:nn～nn:nn）文言を変換する.<br>
     *
     * @param locale ロケール
     * @param oldString 元文字列
     * @return nn:nn～nn:nn
     * @throws Exception Exception
     */
    public static String convKaisaiJikanChar(String locale, String oldString) throws Exception {
        // 基準を～として置換する（DBレコード登録時～なので）
        char oldChar = '～';
        char newChar = CommonProperties.getInstance().getString("fmt.kaisaijikanchar").charAt(0);
        if (locale.equals(Locale.US)) {
            return oldString.replace(oldChar, newChar);
        }
        return oldString;
    }

    /**
     * 曜日文言を変換する.<br>
     *
     * @param locale ロケール
     * @param oldWeek 元文字列
     * @return 曜日
     * @throws Exception Exception
     */
    public static String convWeekChar(Locale locale, String oldWeek) throws Exception {

        String[] weekday = {"日", "月", "火", "水", "木", "金", "土"}; // 基準を漢字として順序をもとに設定ファイルから取得（DBレコード登録時　曜日値は漢字）
        String[] weekdayUs = CommonProperties.getInstance().getString("fmt.weekdays").split(",");
        if (locale.equals(Locale.US)) {
            for (int i = 0; i < weekday.length; i++) {
                if (weekday[i].equals(oldWeek)) {
                    return weekdayUs[i];
                }
            }
        }
        return oldWeek;
    }

    /**
     * カンマ編集.<br>
     *
     * @param money : 金額文字列
     * @return String : 金額文字列にカンマ編集を行ったものを返す.
     */
    public static String attachComma(long money) {
        // 変数宣言
        String strMoney = null; // 金額編集用文字列

        // ====================================
        // 引数を String 型にキャストする.
        // ====================================
        strMoney = Long.toString(money);

        return attachComma(strMoney);
    }

    /**
     * カンマ編集.<br>
     *
     * @param money : 金額文字列
     * @return String : 金額文字列にカンマ編集を行ったものを返す.
     */
    public static String attachComma(String money) {
        return attachComma(money, Locale.JAPAN);
    }

    /**
     * カンマ編集.<br>
     *
     * @param money : 金額文字列
     * @param locale : ロケール
     * @return String : 金額文字列にカンマ編集を行ったものを返す.
     */
    public static String attachComma(long money, Locale locale) {
        // 変数宣言
        String strMoney = null; // 金額編集用文字列

        // ====================================
        // 引数を String 型にキャストする.
        // ====================================
        strMoney = Long.toString(money);

        return attachComma(strMoney, locale);
    }

    /**
     * カンマ編集.<br>
     *
     * @param money : 金額文字列
     * @param locale : ロケール
     * @return String : 金額文字列にカンマ編集を行ったものを返す.
     */
    public static String attachComma(String money, Locale locale) {
        // 変数宣言
        String sResult = null; // 返却文字列
        BigDecimal bigDecimal = null; // 一時変数
        NumberFormat numberFormat = null;

        try {
            // nullまたは空の場合.
            if (StringUtils.isEmpty(money)) {
                return "";
            }

            // ====================================
            // カンマ編集処理
            // ====================================
            // 文字列の変換処理を行う.
            bigDecimal = new BigDecimal(money);

            // 円貨で変換するがカンマのみなので日英処理はしない　
            // ロケール(日本)のNumberFormatを生成.
            numberFormat = NumberFormat.getInstance(locale);

            // 少数位置を設定.
            numberFormat.setMaximumFractionDigits(bigDecimal.scale());
            numberFormat.setMinimumFractionDigits(bigDecimal.scale());

            // 書式を設定する.
            if (Locale.JAPAN.equals(locale)) {
                sResult = "\\" + numberFormat.format(bigDecimal.doubleValue());
            } else {
                sResult = numberFormat.format(bigDecimal.doubleValue()) + "Yen";
            }

        } catch (NumberFormatException nfe) {
            throw nfe;
        }
        return sResult;
    }

    /**
     * カンマ編集.<br>
     *
     * @param money : 数値
     * @return String : 数値にカンマ編集を行ったものを返す.
     */
    public static String attachCommaNumber(long number) {
        // 変数宣言
        String sResult = null; // 返却文字列
        BigDecimal bigDecimal = null; // 一時変数
        NumberFormat numberFormat = null;

        try {
            // ====================================
            // カンマ編集処理
            // ====================================
            // 文字列の変換処理を行う.
            bigDecimal = new BigDecimal(number);

            // ロケール(日本)のNumberFormatを生成.
            numberFormat = NumberFormat.getInstance(Locale.JAPAN);

            // 少数位置を設定.
            numberFormat.setMaximumFractionDigits(bigDecimal.scale());
            numberFormat.setMinimumFractionDigits(bigDecimal.scale());

            // 書式を設定する.
            sResult = numberFormat.format(bigDecimal.doubleValue());

        } catch (NumberFormatException nfe) {
            throw nfe;
        }
        return sResult;
    }

    /**
     * 年学期表示変換処理.<br>
     *
     * @param str 対象年月
     * @return 年学期表示年月
     */
    public static String convDateToYMGetsuki(String str) {

        if (StringUtils.isEmpty(str)) {
            return str;
        }

        return convDate2String(convString2Date(str), CommonProperties.getInstance().getString("fmt.date.getsuki"));
    }

    /**
     * 年学期表示変換処理.<br>
     *
     * @param str 対象年月
     * @param locale ロケール
     * @return 年学期表示年月
     */
    public static String convDateToYMGetsuki(String str, Locale locale) {

        if (locale == null) {
            return convDateToYMGetsuki(str);
        }

        if (StringUtils.isEmpty(str)) {
            return str;
        }

        return convDate2String(convString2Date(str), CommonProperties.getInstance().getString("fmt.date.getsuki", locale));

    }

    /**
     * 日付フォーマット変換処理.<br>
     *
     * @param date 日付
     * @param pattern フォーマット
     * @return フォーマット形式の文字列
     */
    public static String convDate2String(Date date, String pattern) {

        if (date == null || StringUtils.isEmpty(pattern)) {
            // 日付, フォーマットパターンが空または空文字の場合
            return null;
        }

        try {
            // SimpleDateFormatクラス生成
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            // 日付を指定した形式に設定する
            return simpleDateFormat.format(date);

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 日付型変換処理.<br>
     *
     * @param str 対象文字列
     * @return 日付型オブジェクト
     */
    public static Date convString2Date(String str) {

        try {
            return convString2Calendar(str).getTime();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 文字列Calendar型変換処理.<br>
     *
     * @param str 日付文字列
     * @return Calendar
     */
    public static Calendar convString2Calendar(String str) {

        if (StringUtils.isEmpty(str)) {
            return null;
        }

        int year = 0;
        int month = 0;
        int day = 0;
        int hour = 0;
        int minute = 0;
        int second = 0;

        switch (str.length()) {
        case 6:
            year = Integer.parseInt(str.substring(0, 4));
            month = Integer.parseInt(str.substring(4, 6));
            day = 1;
            break;

        case 7:
            year = Integer.parseInt(str.substring(0, 4));
            month = Integer.parseInt(str.substring(5, 7));
            day = 1;
            break;

        case 8:
            year = Integer.parseInt(str.substring(0, 4));
            month = Integer.parseInt(str.substring(4, 6));
            day = Integer.parseInt(str.substring(6, 8));
            break;

        case 10:
            year = Integer.parseInt(str.substring(0, 4));
            month = Integer.parseInt(str.substring(5, 7));
            day = Integer.parseInt(str.substring(8, 10));
            break;

        case 13:
            year = Integer.parseInt(str.substring(0, 4));
            month = Integer.parseInt(str.substring(5, 7));
            day = Integer.parseInt(str.substring(8, 10));
            hour = Integer.parseInt(str.substring(11, 13));
            break;

        case 16:
            year = Integer.parseInt(str.substring(0, 4));
            month = Integer.parseInt(str.substring(5, 7));
            day = Integer.parseInt(str.substring(8, 10));
            hour = Integer.parseInt(str.substring(11, 13));
            minute = Integer.parseInt(str.substring(14, 16));
            break;

        case 19:
            year = Integer.parseInt(str.substring(0, 4));
            month = Integer.parseInt(str.substring(5, 7));
            day = Integer.parseInt(str.substring(8, 10));
            hour = Integer.parseInt(str.substring(11, 13));
            minute = Integer.parseInt(str.substring(14, 16));
            second = Integer.parseInt(str.substring(17, 19));
            break;

        default:
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(year, month - 1, day, hour, minute, second);
        return calendar;
    }

    /**
     * <p>処理名 :外部ファイル読み込みメソッド</p>
     * <p>解説   :外部ファイルを読み込み、String 型の文字列として返却する.</p>
     * <p>備考   :</p>
     * @param filePath 外部ファイル格納パス
     * @return 外部ファイル
     */
    public static String getOuterFile(String filePath) {
        // 変数宣言
        String retString = "";          // 返却する文字列のオブジェクトの宣言.
        String wkCharctor;                      // 一文字ずつ読み込む時に使用するワーク変数.

        InputStreamReader isr = null;
        BufferedReader inBuffer = null;

        try {
            //--------------------------------------------------
            // ファイル読み込み処理
            //--------------------------------------------------
            // FileReaderオブジェクト入力ファイルを生成.
            //FileReader inFile = new FileReader(filePath);
            FileInputStream fis = new FileInputStream(filePath);
            isr = new InputStreamReader(fis, "UTF-8");

            // BufferedReaderオブジェクト入力バッファを生成.
            // BufferedReader inBuffer = new BufferedReader(inFile);
            inBuffer = new BufferedReader(isr);

            // 読み込みデータがなくなるまで、読み込み処理を行う.
            while ((wkCharctor = inBuffer.readLine()) != null) {
                // 返却文字列にデータを書きこむ.
                retString = retString + wkCharctor;
            }
        } catch (FileNotFoundException e) {
            // ファイルが見つからなかった場合は何も表示しない
            return "";
        } catch (UnsupportedEncodingException e) {
            throw new SystemException(e);
        } catch (IOException e) {
            throw new SystemException(e);
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    throw new SystemException(e);
                }
            }
            if (inBuffer != null) {
                try {
                    inBuffer.close();
                } catch (IOException e) {
                    throw new SystemException(e);
                }
            }
        }
        // 取得した文字列を返却する.
        return retString;
    }

    /**
     * 改行コードを&lt;BR&gt;に変換する.
     *
     * @param str 変換前文字列
     * @return 変換後文字列
     */
    public static String changeNewLine2Br(String str) {
        String retString = "";

        // "\n"の置換
        retString = StringUtils.replace(str, "\n", "<BR>");

        // "\r"の置換
        retString = StringUtils.replace(str, "\r", "<BR>");

        // "\r\n"の置換
        retString = StringUtils.replace(str, "\r\n", "<BR>");

        return retString;
    }

    /**
     * 曜日コードを文字列に変換する.
     *
     * @param cd 曜日コード
     * @param locale ロケール
     * @return 曜日
     */
    public static String changeYobiCd2Str(String cd, Locale locale) {
        String yobi = "";
        CommonProperties comprop = CommonProperties.getInstance();
        if ("2".equals(cd)) { //月
            yobi = comprop.getString("fmt.weekday.monday", locale);
        } else if ("3".equals(cd)) { //火
            yobi = comprop.getString("fmt.weekday.tuesday", locale);
        } else if ("4".equals(cd)) { //水
            yobi = comprop.getString("fmt.weekday.wednesday", locale);
        } else if ("5".equals(cd)) { //木
            yobi = comprop.getString("fmt.weekday.thursday", locale);
        } else if ("6".equals(cd)) { //金
            yobi = comprop.getString("fmt.weekday.friday", locale);
        } else if ("7".equals(cd)) { //土
            yobi = comprop.getString("fmt.weekday.saturday", locale);
        } else if ("1".equals(cd)) { //日
            yobi = comprop.getString("fmt.weekday.sunday", locale);
        }
        return yobi;
    }

    /**
     * 日付をもとに曜日を取得します.
     *
     * @param inYyyyMMdd 日付
     * @param locale ロケール
     * @return 曜日
     * @throws ParseException
     */
    public static String changeYobiStrE(String inYyyyMMdd, Locale locale)  {

        if (StringUtils.isEmpty(inYyyyMMdd) || inYyyyMMdd.trim().length() <= 7) {
            return "";
        }

        String yyyyMMdd = inYyyyMMdd.replaceAll("/", "");
        String yobi = "";
        GregorianCalendar cal1 = new GregorianCalendar(
        Integer.parseInt(yyyyMMdd.substring(0, 4)),
        Integer.parseInt(yyyyMMdd.substring(4, 6)) - 1,
        Integer.parseInt(yyyyMMdd.substring(6, 8)));

        String[] weekday = {"日", "月", "火", "水", "木", "金", "土"}; // 基準を漢字として順序をもとに設定ファイルから取得（DBレコード登録時　曜日値は漢字）
        if (locale.equals(Locale.US)) {
            weekday = null;
            weekday = "Sun,Mon,Tue,Wed,Thu,Fri,Sat".split(",");
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
        DateFormat foramt = new SimpleDateFormat("yyyyMMdd");
        try {
            java.util.Date date = foramt.parse(yyyyMMdd);
            sdf1.format(date);
        } catch (Exception e) {
            return "";
        }

        switch (cal1.get(Calendar.DAY_OF_WEEK)) {  // 現在の曜日を取得
            case Calendar.SUNDAY: yobi = weekday[0]; break;
            case Calendar.MONDAY: yobi = weekday[1]; break;
            case Calendar.TUESDAY: yobi = weekday[2]; break;
            case Calendar.WEDNESDAY: yobi = weekday[3]; break;
            case Calendar.THURSDAY: yobi = weekday[4]; break;
            case Calendar.FRIDAY: yobi = weekday[5]; break;
            case Calendar.SATURDAY: yobi = weekday[6]; break;
            default: break;
        }
        return yobi;
    }

    /**
     * 日付の項目に対してロケーションをもとに表示変換を行う.
     *
     * @param inYyyyMMdd 日付
     * @param locale ロケール
     * @return 日本語(yyyy/mm/dd) 英語(MMM dd, YYYY)
     * @throws ParseException
     */
    public static String changeBiStrE(String inYyyyMMdd, Locale locale)  {
        SimpleDateFormat sdf1 = new SimpleDateFormat("MMM dd, yyyy", locale);
        DateFormat foramt = new SimpleDateFormat("yyyyMMdd");
        if (StringUtils.isEmpty(inYyyyMMdd) || inYyyyMMdd.trim().length() <= 7) {
            return "";
        }
        String yyyyMMdd = inYyyyMMdd.replaceAll("/", "");
        String result = "";
        try {
            java.util.Date date = foramt.parse(yyyyMMdd);
            result = sdf1.format(date);
        } catch (Exception e) {
            return "";
        }
        return result;
    }


    /**
    *
    * ファイル拡張子取得部品.<br>
    * @param fileName ファイル名
    * @return extension
    */
   public static String getFileExtension(String fileName) {
       int lastDotPosition = fileName.lastIndexOf(".");
       // 『 . 』が存在する場合は、『 . 』以降を返します。
       if (lastDotPosition != -1) {
           return "." + fileName.substring(lastDotPosition + 1);
       } else {
           return "";
       }
   }

   /**
   *
   * 電話番号/FAX番号を「-」で分割.
   * @param telNo 電話番号/FAX番号
   * @return extension
   */
  public static String[] getNoSplit(String telNo) {
      String value = telNo;

      String[] result = {"", "", ""};                     // 分割値格納配列
      int lastPosition;

      if (StringUtils.isEmpty(value)) {
          return result;
      }

      int i = 2;
      while (value.lastIndexOf("-") >= 0) {
          if (i == 0) {
              break;
          }
          lastPosition = value.lastIndexOf("-");

          // 分割値格納
          result[i] = value.substring(lastPosition + 1);
          value = value.substring(0, lastPosition);
          i--;
      }

      // 配列の先頭に値を格納する
      result[0] = value;

      return result;
  }

  /**
   * 最大ファイルサイズを整形します.
   *
   * @param fileSizeStr ファイルサイズ(文字列)
   * @return fileSize
   */
  public static String formatFileSizeStr(String fileSizeStr) {
      StringBuilder sb = new StringBuilder();

      BigDecimal fileSize = new BigDecimal(fileSizeStr);

      // byte ⇒ MBに(1000,000で割る)
      fileSize = fileSize.divide(new BigDecimal(1000000), 0, BigDecimal.ROUND_DOWN);

      // カンマ区切りに編集
      DecimalFormat df = new DecimalFormat("###,###");
      sb.append(df.format(fileSize.doubleValue()));

      return sb.toString();
  }

  /**
   * 日付文字列に曜日を追加します.
   *
   * @param dateStr 日付文字列
   * @param locale
   * @return 曜日追加後日付文字列
   */
  public static String formatDateAddYobiStr(String dateStr, Locale locale) {
      String yobiStr = changeYobiStrE(dateStr, locale);
      // 曜日が取得できない場合は引数の文字列をそのまま返す
      if (StringUtils.isEmpty(yobiStr)) {
          return dateStr;
      }
      // 曜日の前後に括弧を追加する
      return dateStr + "(" + yobiStr + ")";
  }


  /**
   *  0埋め処理の追加
   */
	public static String formatNumber(String num) {
		int new_syohinNo = Integer.parseInt(num);
		//0詰め4桁にするフォーマット
		DecimalFormat df = new DecimalFormat("0000");
		return df.format(new_syohinNo);
	}
}
