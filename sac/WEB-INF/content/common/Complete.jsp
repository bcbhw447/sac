<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/sac.tld" prefix="sac" %>
<tiles:insertDefinition name="layout.normal">
<tiles:putAttribute name="content">
<div class="title">
<s:if test="completionTitle != '' && completionTitle != null">
  <s:text name="completionTitle"/>
</s:if>
<s:else>
  <s:text name="scr.item.complete"/>
</s:else>
</div>
<s:if test="completionMsg != '' && completionMsg != null">
<div><s:text name="completionMsg"/></div>
</s:if>
<s:else>
<div><s:text name="INF0007"/></div>
</s:else>
<s:if test="backTo != null and backTo != ''">
  <div class="pt20" align="right">
    <table>
      <tr>
        <td><a href="javascript:void(0);" class="btn02 sendbtn" onclick="submitBack('${backTo}');return false;"><s:text name="scr.button.back"/></a></td>
      </tr>
    </table>
  </div>
  <s:form id="form" action="" method="post">
    <s:if test="backTo == 'ar0290'">
      <s:hidden name="classCd" />
    </s:if>
  </s:form>
</s:if>
</tiles:putAttribute>
</tiles:insertDefinition>
