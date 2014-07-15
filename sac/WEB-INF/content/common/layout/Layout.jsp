<%--
/**
 * 基本レイアウト。
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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<%-- HTMLヘッダ --%>
<tiles:insertAttribute name="htmlHeader" />
</head>
<body id="<tiles:getAsString name="bodyId" />" onload="initialLoad();">
<div id="container" class="">

	<%-- ヘッダ --%>
	<div id="head">
		<tiles:insertAttribute name="header"/>
	</div>

	<div id="main">
		<div id="main_contents">
			<%-- メニュー --%>
			<div id="left_area">
				<tiles:insertAttribute name="menu"/>
			</div>

			<%-- メインコンテンツ --%>
			<div id="right_area">
				<tiles:insertAttribute name="content"/>
			</div>
			<div class="cla"><br /></div>
		</div>
	</div>

	<%-- フッタ --%>
	<div id="footer">
		<tiles:insertAttribute name="footer"/>
	</div>
</div>
</body>
</html>
