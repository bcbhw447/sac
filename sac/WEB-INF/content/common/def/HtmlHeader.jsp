<%--
/**
 * 共通HTMLヘッダー。
 *
 * 変更日付	 作成／変更者		コメント
 * ========== ================= ===========================================
 * 2014/07/14 m_hamaoka			新規作成
 * yyyy/MM/dd ××				変更内容を記述する
 */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/sac.tld" prefix="sac" %>
<title><s:text name="scr.title.sac" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<meta http-equiv="Content-Style-Type" content="text/css" />

<%-- javaScript --%>
<script type="text/javascript" src="<s:url value="/js/common/jquery-2.1.1.min.js" />"></script>
<script type="text/javascript" src="<s:url value="/js/common/jquery-ui.min.js" />"></script>
<script type="text/javascript" src="<s:url value="/js/common/bootstrap.min.js" />"></script>
<script type="text/javascript" src="<s:url value="/js/common/common.js" />"></script>
<script type="text/javascript" src="<s:url value="/js%{jsFile}" />"></script>

<%-- CSS --%>
<link href="<s:url value="/css/common/bootstrap.min.css" />" rel="stylesheet" type="text/css" />
<link href="<s:url value="/css/common/common.css" />" rel="stylesheet" type="text/css" />
<link href="<s:url value="/css/common/Tab.css" />" rel="stylesheet" type="text/css" />

<sac:fielderror />
