package jp.ne.sac.common.interceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jp.ne.sac.common.base.BaseAction;
import jp.ne.sac.common.exception.ApplicationException;
import jp.ne.sac.common.exception.SystemException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.config.entities.ExceptionMappingConfig;
import com.opensymphony.xwork2.interceptor.ExceptionHolder;
import com.opensymphony.xwork2.interceptor.ExceptionMappingInterceptor;

/**
 * 例外処理インターセプター.
 * <p>
 * 例外処理を判断するインターセプター。<br>
 *
 * @author m_hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
public class ExceptionInterceptor extends ExceptionMappingInterceptor {

	/** serialVersionUID. */
	private static final long serialVersionUID = -6275571131530395071L;

	/** logger. */
	private static Logger log = LoggerFactory.getLogger(SystemException.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void handleLogging(Exception e) {
		if (e instanceof ApplicationException) {
			// ApplicationExceptionの場合はログ出力を行わない
			return;
		}
		log.error("error", e);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public String intercept(ActionInvocation invocation) throws Exception {
		String result;
		BaseAction action = (BaseAction) invocation.getAction();

		try {
			result = invocation.invoke();
		} catch (Exception e) {

			if (isLogEnabled()) {
				handleLogging(e);
			}
			List<ExceptionMappingConfig> exceptionMappings = invocation.getProxy().getConfig().getExceptionMappings();
			ExceptionMappingConfig mappedResult = this.findMappingFromExceptions(exceptionMappings, e);
			if (mappedResult != null) {
				HashMap<String, List<String>> map = new HashMap<String, List<String>>();
				List<String> errMsg = new ArrayList<String>();

				// 画面タイトルの上書き、エラーメッセージの格納処理を行う
				// トークンエラー
				String key = e.getMessage();

				if (e instanceof ApplicationException) {
					action.setScreenTitle(action.getText("scr.title.application"));
					if (StringUtils.isEmpty(key)) {
						key = action.getText("ERR1002");
					}
				} else {
					action.setScreenTitle(action.getText("scr.title.system"));
					key = "ERR1004";
				}

				errMsg.add(action.getText(key));
				map.put("errMsg", errMsg);

				action.setFieldErrors(map);
				result = "exception";
				publishException(invocation, new ExceptionHolder(e));
			} else {
				throw e;
			}
		}

		return result;
	}

}
