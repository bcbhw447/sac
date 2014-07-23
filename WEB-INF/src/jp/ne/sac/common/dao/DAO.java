package jp.ne.sac.common.dao;

import java.util.List;
import java.util.Map;

/**
 * DAOインターフェイス.
 * <p>
 * DAOインターフェイス。<br>
 *
 * @author m_hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
public interface DAO {

    /**
     * 登録処理.<br>
     *
     * @param key SQLID
     * @param parameterObject パラメータークラス
     * @return 登録件数
     */
    int insert(String key, Object parameterObject);

    /**
     * 更新処理.<br>
     *
     * @param key SQLID
     * @param parameterObject パラメータークラス
     * @return 更新件数
     */
    int update(String key, Object parameterObject);

    /**
     * 更新処理.<br>
     *
     * @param key SQLID
     * @param parameterObject パラメータークラス
     * @param throwExceptionFlg trueの場合は、更新件数が0件の時にExceptionをスローします
     * @return 登録件数
     */
    int update(String key, Object parameterObject, boolean throwExceptionFlg);

    /**
     * 削除処理.<br>
     *
     * @param key SQLID
     * @param parameterObject パラメータークラス
     * @return 削除件数
     */
    int delete(String key, Object parameterObject);

    /**
     * 削除処理.<br>
     *
     * @param key SQLID
     * @param parameterObject パラメータークラス
     * @param throwExceptionFlg trueの場合は、削除件数が0件の時にExceptionをスローします
     * @return 削除件数
     */
    int delete(String key, Object parameterObject, boolean throwExceptionFlg);

    /**
     * 検索処理(1件).<br>
     *
     * @param key SQL ID
     * @param parameterObject パラメータ
     * @return 検索結果
     */
    Object selectOne(String key, Object parameterObject);

    /**
     * 検索処理(1件).<br>
     *
     * @param key SQL ID
     * @return 検索結果
     */
    Object selectOne(String key);

    /**
     * 検索処理(N件).<br>
     *
     * @param <T> ジェネリクス
     * @param key SQL ID
     * @param parameterObject パラメータ
     * @return 検索結果
     */
    <T> List<T> selectList(String key, Object parameterObject);

    /**
     * 検索処理(N件).<br>
     *
     * @param <T> ジェネリクス
     * @param key SQL ID
     * @return 検索結果
     */
    <T> List<T> selectList(String key);

    /**
     * 検索処理(N件).<br>
     *
     * @param <T> ジェネリクス
     * @param key SQL ID
     * @param parameterObject パラメータ
     * @param offset スキップ件数
     * @param limit 最大件数
     * @return 検索結果
     */
    <T> List<T> selectList(String key, Object parameterObject, int offset, int limit);

    /**
     * 検索処理(N件).<br>
     *
     * @param <T> ジェネリクス
     * @param key SQL ID
     * @param offset スキップ件数
     * @param limit 最大件数
     * @return 検索結果
     */
    <T> List<T> selectList(String key, int offset, int limit);

    /**
     * 検索処理(MAP).<br>
     *
     * @param <K> 仮引数型
     * @param <V> 仮引数型
     * @param key SQL ID
     * @param mapKey 主キー
     * @return 検索結果
     */
    <K, V> Map<K, V> selectMap(String key, String mapKey);

    /**
     * 検索処理(MAP).<br>
     *
     * @param <K> 仮引数型
     * @param <V> 仮引数型
     * @param key SQL ID
     * @param parameterObject パラメータ
     * @param mapKey 主キー
     * @return 検索結果
     */
    <K, V> Map<K, V> selectMap(String key, Object parameterObject, String mapKey);

    /**
     * 検索処理(MAP).<br>
     *
     * @param <K> 仮引数型
     * @param <V> 仮引数型
     * @param key SQL ID
     * @param parameterObject パラメータ
     * @param mapKey 主キー
     * @param offset スキップ件数
     * @param limit 最大件数
     * @return 検索結果
     */
    <K, V> Map<K, V> selectMap(String key, Object parameterObject, String mapKey, int offset, int limit);

    /**
     * コミット処理.<br>
     */
    void commit();

    /**
     * ロールバック処理.<br>
     */
    void rollback();

}
