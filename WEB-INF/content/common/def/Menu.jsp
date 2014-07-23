<%--
/**
 * 共通サイドメニュー
 *
 * 変更日付	 作成／変更者		コメント
 * ========== ================= ===========================================
 * 2014/07/14 m_hamaoka			新規作成
 * yyyy/MM/dd ××				変更内容を記述する
 */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<s:set var="menuFlg" value="false" />
<s:iterator value="#session.portal.menu" status="rs">
	<s:if test="locale.toString() == 'ja_JP'">
		<s:set name="menuLabel" value="%{menuMei}" />
	</s:if>
	<s:else>
		<s:set name="menuLabel" value="%{menuMeiE}" />
	</s:else>
	<s:if test="#rs.first == false & #menuFlg == true"></ul></s:if>
	<s:set name="menuFlg" value="false" />
	<div class="btn_side_blue"><s:property value="#menuLabel" /></div>
	<s:iterator value="itemList" status="sts">
		<s:if test="locale.toString() == 'ja_JP'">
			<s:set name="menuItemLabel" value="%{menuMei}" />
		</s:if>
		<s:else>
			<s:set name="menuItemLabel" value="%{menuMeiE}" />
		</s:else>
		<s:if test="#sts.first == true & #menuFlg == false">
			<ul class="side01">
			<s:set name="menuFlg" value="true" />
		</s:if>
		<li class="${sts.first == true ? 'top_line' : ''}">
			<s:if test="outerLinkFlg == 1">
				<a href="${outerLinkUrl}" onclick="" target="_blank" ><s:property value="#menuItemLabel" />&nbsp;</a>
			</s:if>
			<s:else>
				<a href="javascript:void(0);" onclick="comSubmitMenu('${menuGroupCd}', '${actionId}', '${functionId}');return false;" ><s:property value="#menuItemLabel" />&nbsp;</a>
			</s:else>
		</li>
	</s:iterator>
</s:iterator>
<s:if test="#menuFlg == true"></ul></s:if>
