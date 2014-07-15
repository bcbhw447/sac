<%--
/**
 * アプリケーションエラー画面。
 *
 * 変更日付	 作成／変更者			コメント
 * ========== ================= ===========================================
 * 2014/07/14 m_hamaoka			新規作成
 * yyyy/MM/dd ××				変更内容を記述する
 */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:insertDefinition name="layout.error">
<tiles:putAttribute name="bodyId">err_page</tiles:putAttribute>
<tiles:putAttribute name="content">

<script type="text/javascript">
<!--
	/**
	 * 初期処理.
	 */
	initialLoad = function() {
	};
//-->
</script>

<s:if test="errors.size != 0">
<div class="main_area">
	<div class="logo_top_bg">
		<div class="logo_top"><img src="../img/logo_ad02_top.gif" width="196" height="90" /></div>
	</div>
	<div class="title_err_page"><s:property value="screenTitle" /></div>
	<br/>
	<s:text name="errors.get(\"errMsg\").get(0)"/>
	<div class="center mt20">
		<s:if test="#session.portal != null && #session.portal.userInfo != null" >
			<a href="javascript:void(0);" class="btn02 table_btn_center" onclick="comSubmit2Top();return false;"><s:text name="scr.button.back"/></a>
		</s:if>
		<s:else>
			<a href="javascript:void(0);" class="btn02 table_btn_center" onclick="comSubmit2Login();return false;"><s:text name="scr.button.back"/></a>
		</s:else>
	</div>
</div>
</s:if>
<s:else>
<div class="main_area">
	<div class="logo_top_bg">
		<div class="logo_top"><img src="../img/logo_ad02_top.gif" width="196" height="90" /></div>
	</div>
	<div class="title_err_page"><s:text name="scr.title.application" /></div>
	<br/>
	<s:text name="ERR1003" />
	<div class="center mt20">
		<a href="javascript:void(0);" class="btn02 table_btn_center" onclick="comSubmit2Top();return false;"><s:text name="scr.button.back"/></a>
	</div>
</div>
</s:else>
</tiles:putAttribute>
</tiles:insertDefinition>
