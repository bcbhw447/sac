<%--
/**
 * 共通フッター。
 *
 * 変更日付   作成／変更者      コメント
 * ========== ================= ===========================================
 * 2014/07/14 m_hamaoka         新規作成
 * yyyy/MM/dd ××              変更内容を記述する
 */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<br />
<%--
<div id="pankuzu">
<s:iterator value="#session.portal.history" status="rs">
	<s:if test="!#rs.first">
		<img src="<s:url value="/img/ar_pankuzu_mid.gif" />" width="6" height="7" class="ar_mid" />
	</s:if>
	<s:if test="locale.toString() == 'ja_JP'">
		<s:property value="menuMei" />
	</s:if>
	<s:else>
		<s:property value="menuMeiE" />
	</s:else>
</s:iterator>
</div>
 --%>
