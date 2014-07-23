package jp.ne.sac.common.dao;

import java.util.List;
import java.util.Map;

import jp.ne.sac.common.exception.DeleteFailedException;
import jp.ne.sac.common.exception.UpdateFailedException;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.MDC;

/**
 * DAOクラス.
 * <p>
 * DAOクラス。<br>
 *
 * @author m_hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
public class DAOImpl extends SqlSessionDaoSupport implements DAO {

    /**
     * SQLID設定処理.<br>
     *
     * @param sqlId SQLID
     */
    private void putMDC(String sqlId) {
        MDC.put("sqlId", "SQL [" + sqlId + "] : ");
    }

    /**
     * SQLID破棄処理.<br>
     */
    private void removeMDC() {
        MDC.remove("sqlId");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int insert(String key, Object parameterObject) {
        this.putMDC(key);
        try {
            return super.getSqlSession().insert(key, parameterObject);
        } finally {
            this.removeMDC();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int update(String key, Object parameterObject) {
        return this.update(key, parameterObject, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int update(String key, Object parameterObject, boolean throwExceptionFlg) {
        this.putMDC(key);
        try {
            int result = super.getSqlSession().update(key, parameterObject);
            if ((result == 0) && throwExceptionFlg) {
                throw new UpdateFailedException("ERR1002");
            }
            return result;
        } finally {
            this.removeMDC();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int delete(String key, Object parameterObject) {
        return this.delete(key, parameterObject, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int delete(String key, Object parameterObject, boolean throwExceptionFlg) {
        this.putMDC(key);
        try {
            int result = super.getSqlSession().delete(key, parameterObject);
            if ((result == 0) && throwExceptionFlg) {
                // 0件削除エラー
                throw new DeleteFailedException("ERR1002");
            }
            return result;
        } finally {
            this.removeMDC();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object selectOne(String key, Object parameterObject) {
        this.putMDC(key);
        try {
            return super.getSqlSession().selectOne(key, parameterObject);
        } finally {
            this.removeMDC();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object selectOne(String key) {
        this.putMDC(key);
        try {
            return super.getSqlSession().selectOne(key);
        } finally {
            this.removeMDC();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> List<T> selectList(String key, Object parameterObject) {
        this.putMDC(key);
        try {
            return super.getSqlSession().selectList(key, parameterObject);
        } finally {
            this.removeMDC();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> List<T> selectList(String key) {
        this.putMDC(key);
        try {
            return super.getSqlSession().selectList(key);
        } finally {
            this.removeMDC();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> List<T> selectList(String key, Object parameterObject, int offset, int limit) {
        this.putMDC(key);
        try {
            return super.getSqlSession().selectList(key, parameterObject, new RowBounds(offset, limit));
        } finally {
            this.removeMDC();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> List<T> selectList(String key, int offset, int limit) {
        this.putMDC(key);
        try {
            return super.getSqlSession().selectList(key, new RowBounds(offset, limit));
        } finally {
            this.removeMDC();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <K, V> Map<K, V> selectMap(String key, String mapKey) {
        this.putMDC(key);
        try {
            return super.getSqlSession().selectMap(key, mapKey);
        } finally {
            this.removeMDC();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <K, V> Map<K, V> selectMap(String key, Object parameterObject, String mapKey) {
        this.putMDC(key);
        try {
            return super.getSqlSession().selectMap(key, parameterObject, mapKey);
        } finally {
            this.removeMDC();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <K, V> Map<K, V> selectMap(String key, Object parameterObject, String mapKey, int offset, int limit) {
        this.putMDC(key);
        try {
            return super.getSqlSession().selectMap(key, parameterObject, mapKey, new RowBounds(offset, limit));
        } finally {
            this.removeMDC();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void commit() {
        super.getSqlSession().commit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void rollback() {
        super.getSqlSession().rollback();
    }

}
