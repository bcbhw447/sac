<%--
/**
 * 共通フッター。
 *
 * 変更日付	 作成／変更者		コメント
 * ========== ================= ===========================================
 * 2014/07/15 m_hhamaoka		新規作成
 * yyyy/MM/dd ××				変更内容を記述する
 */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!--- ヘッダー↓↓↓ --->
<table cellpadding="0" cellspacing="0" align="center">
	<tr>
		<td class="titleleft">&nbsp;</td>
		<td class="titlecenter">
			オンラインショッピング <s:if test="userInfo.adminKbn == 1">(管理者モード)</s:if>
		</td>
		<td class="titleright">【<s:property value="%{screenTitle}" />】</td>
	</tr>
	<tr>
		<td colspan="3"><br></td>
	</tr>
	<tr>
		<td colspan="2" align="left">
			<a href="#" onclick="submitMenu()">ログイン画面へ</a>
		</td>
		<td align="right">
			<a href="#" onclick="submitHistory()">&nbsp;&nbsp;</a>
		</td>
	</tr>
</table>
<!--- ヘッダー↑↑↑ --->

<div class="cla"><br /></div>
