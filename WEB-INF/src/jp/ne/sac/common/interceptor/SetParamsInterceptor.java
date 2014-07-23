package jp.ne.sac.common.interceptor;

import javax.servlet.http.HttpServletRequest;

import jp.ne.sac.common.base.BaseAction;
import jp.ne.sac.common.dao.DAO;
import jp.ne.sac.common.system.Constants;
import jp.ne.sac.common.system.bean.SystemInfo;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * パラメータ取得インターセプター.
 * <p>
 * アプリケーションに必要なパラメータを取得するインターセプター。<br>
 *
 * @author m_hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
public class SetParamsInterceptor extends AbstractInterceptor {

	/** serialVersionUID. */
	private static final long serialVersionUID = -7981370221373622410L;

	/** DAO. */
	private DAO dao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public String intercept(ActionInvocation invocation) throws Exception {

		ActionContext context = invocation.getInvocationContext();
		HttpServletRequest req = ServletActionContext.getRequest();
		String actionName = context.getActionInvocation().getProxy().getActionName();

		SystemInfo sys = (SystemInfo) context.getSession().get(Constants.SES_SYS_INFO);
		if (sys == null || "ac0000".equals(actionName)) {
			// システム情報が空の場合、新規で作成する
			sys = new SystemInfo();
			context.getSession().put(Constants.SES_SYS_INFO, sys);
		}

		// JSファイル情報をセットする
		Object action = invocation.getAction();
		if (action instanceof BaseAction) {
			BaseAction baseAction = (BaseAction) action;
			// BaseActionを継承している場合
			ActionContext ctx = ActionContext.getContext();
			baseAction.setJsFile("/" + ctx.getActionInvocation().getProxy().getActionName() + ".js");
		}

		// ユーザエージェントからブラウザタイプを取得する
		String userAgent = req.getHeader("User-Agent");

		if (userAgent.indexOf("Mobile") > 0) {
			// Mobileという文字列がある場合はスマホ端末(語弊があるが。。。)
			sys.setIsMobile(true);
		} else {
			sys.setIsMobile(false);
		}

		if (userAgent.indexOf("iPhone") > 0 || userAgent.indexOf("iPod") > 0) {
			// iPhoneまたはiPodという文字列がある場合はiOS端末(iPhone)とする
			sys.setBrowserType("iPhone");
		} else if (userAgent.indexOf("Android") > 0) {
			// Androidという文字列がある場合はAndorid端末
			sys.setBrowserType("Android");
		}

		return invocation.invoke();
	}

	/**
	 * DAOを取得します.
	 *
	 * @return DAO
	 */
	public DAO getDao() {
		return dao;
	}

	/**
	 * DAOを設定します.
	 *
	 * @param dao DAO
	 */
	public void setDAO(DAO dao) {
		this.dao = dao;
	}

}
