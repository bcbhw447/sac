<#if parameters.get("noResult")?? && parameters.get("noResult")>
<script type="text/javascript">
<!--
$T.addErrorIfEmpty(null, UE0014);
// -->
</script>
</#if>
<#if parameters.get("maxOver")?? && parameters.get("maxOver")>
<script type="text/javascript">
<!--
$T.addErrorIfEmpty(null, UE0004);
// -->
</script>
</#if>
<#if parameters.get("list")?? && parameters.get("list")?size &gt; 0>
    <input type="hidden" name="criteria.page" id="__criteria_page" value="${parameters.page}" />
    <input type="hidden" name="criteria.sort" id="__criteria_sort" value="${parameters.sort}" />
    <input type="hidden" name="criteria.action" id="__criteria_action" />
    <script type="text/javascript">
    <!--
    function __doPageClick(page) {
        var e = $('#__criteria_page').get(0);
        e.value = page;
        $('#__criteria_action').val('page');
        doPage(e.form);
    }
    function __doSortClick(sort) {
        var e = $('#__criteria_sort').get(0);
        e.value = sort;
        $('#__criteria_action').val('sort');
        $('#__criteria_page').val(1);
        doSort(e.form);
    }
    // -->
    </script>
    <#if parameters.get("paging")??>
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr>
            <td class="bg_message_l" width="500">
                Search Result: ${parameters.paging.count?html} ( ${parameters.paging.start?html} - ${parameters.paging.end?html} )
            </td>
            <td class="bg_message_r" width="500">
    <#if parameters.paging.page != 1>
        <a href="javascript:void(0);" onclick="__doPageClick(${parameters.paging.page - 1});return false;"><input type="image" name="nextPage" src="/portal/img/icon_prevpage.gif" /></a>
        &nbsp;
    </#if>
    <#list parameters.paging.startPage..parameters.paging.endPage as p>
        <#if p == parameters.paging.page>
            ${p}
        <#else>
            <a href="javascript:void(0);" onclick="__doPageClick(${p});return false;">${p}</a>
        </#if>
    </#list>
    <#if parameters.paging.page != parameters.paging.maxPage>
        &nbsp;
        <a href="javascript:void(0);" onclick="__doPageClick(${parameters.paging.page + 1});return false;"><input type="image" name="nextPage" src="/portal/img/icon_nextpage.gif" /></a>
    </#if>
            </td>
        </tr>
        <tr>
            <td class="bg_message_blank_s">
            </td>
        </tr>
    </table>
    </#if>
</#if>
