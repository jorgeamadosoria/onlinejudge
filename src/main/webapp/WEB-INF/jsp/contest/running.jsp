<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h2 class="postheader">
	<spring:message code="pagehdr.rcsrunning" />
</h2>
<div class="postcontent">

	<div id="display-table-container"
		data-reload-url="/tables/running.xhtml"></div>
</div>
<script>
	$(document).ready(displayTableReload(" "));
</script>
