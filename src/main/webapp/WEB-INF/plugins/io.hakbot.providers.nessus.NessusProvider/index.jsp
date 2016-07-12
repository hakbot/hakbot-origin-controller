<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/fragments/header.jsp"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/console/static/io.hakbot.providers.nessus.NessusProvider/style.css"/>">
    <title>Hakbot Origin Controller - Nessus</title>
</head>
<body>
<jsp:include page="/WEB-INF/fragments/navbar.jsp"/>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12 col-md-12 main" id="main">
            <h3><c:out value="${requestScope.job.name}"/><span style="float:right">Nessus</span></h3>
            <div class="btn-group btn-breadcrumb">
                <a href="#hosts" class="btn btn-default" data-toggle="tab">Hosts <span id="hostsBadge" class="badge"></span></a>
                <a href="#vulnerabilities" class="btn btn-default" data-toggle="tab">Vulnerabilities <span id="vulnerabilitiesBadge" class="badge"></span></a>
                <a href="#compliance" class="btn btn-default" data-toggle="tab">Compliance <span id="complianceBadge" class="badge"></span></a>
                <a href="#remediations" class="btn btn-default" data-toggle="tab">Remediations <span id="remediationBadge" class="badge"></span></a>
            </div>
        </div>
        <div class="col-sm-9 col-md-9 main">
            <div class="tab-content">
                <div class="tab-pane active" id="hosts"></div>
                <div class="tab-pane" id="vulnerabilities">
                    <table id="vulnerabilitiesTable" class="table" data-height="100%" data-sort-name="severityOrder" data-sort-order="asc">
                        <thead>
                        <tr>
                            <th data-visible="false" data-field="severityOrder"></th>
                            <th data-align="center" data-field="severityLabel">Severity</th>
                            <th data-align="left" data-field="pluginName">Plugin Name</th>
                            <th data-align="left" data-field="pluginFamily">Plugin Family</th>
                            <th data-align="left" data-field="count">Count</th>
                        </tr>
                        </thead>
                    </table>
                </div>
                <div class="tab-pane" id="compliance">
                    <table id="complianceTable" class="table" data-height="100%" data-sort-name="severityOrder" data-sort-order="asc">
                        <thead>
                        <tr>
                            <th data-visible="false" data-field="severityOrder"></th>
                            <th data-align="center" data-field="severityLabel">Status</th>
                            <th data-align="left" data-field="pluginName">Plugin Name</th>
                            <th data-align="left" data-field="pluginFamily">Plugin Family</th>
                            <th data-align="left" data-field="count">Count</th>
                        </tr>
                        </thead>
                    </table>
                </div>
                <div class="tab-pane" id="remediations">
                    <table id="remediationTable" class="table">
                        <thead>
                        <tr>
                            <th data-align="left" data-field="action">Action to take</th>
                            <th data-align="left" data-field="vulnerabilities">Vulns</th>
                            <th data-align="left" data-field="hosts">Hosts</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
        <div class="col-sm-3 col-md-3 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Scan Details</h3>
                </div>
                <table class="table">
                    <tr>
                        <td>Name:</td>
                        <td id="scanName"></td>
                    </tr>
                    <tr>
                        <td>Status:</td>
                        <td id="status"></td>
                    </tr>
                    <tr>
                        <td>Policy:</td>
                        <td id="policy"></td>
                    </tr>
                    <tr>
                        <td>Scanner:</td>
                        <td id="scanner"></td>
                    </tr>
                    <tr>
                        <td>Start:</td>
                        <td id="start"></td>
                    </tr>
                    <tr>
                        <td>End:</td>
                        <td id="end"></td>
                    </tr>
                    <tr>
                        <td>Elapsed:</td>
                        <td id="elapsed"></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/fragments/footer.jsp"/>
<script type="text/javascript" src="<c:url value="/console/static/io.hakbot.providers.nessus.NessusProvider/functions.js"/>"></script>
<script type="text/javascript">
    $nessus.getJobConsoleData('<c:out value="${requestScope.job.uuid}"/>');
    //setInterval(function() {
    //    $nessus.getJobConsoleData('<c:out value="${requestScope.job.uuid}"/>');
    //}, 30 * 1000);
</script>
</body>
</html>
