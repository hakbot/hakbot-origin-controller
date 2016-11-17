<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="e" uri="https://www.owasp.org/index.php/OWASP_Java_Encoder_Project" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/fragments/header.jsp"/>
    <title>Hakbot Origin Controller</title>
</head>
<body>
<jsp:include page="/WEB-INF/fragments/navbar.jsp"/>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12 col-md-12 main" id="main">
            <div class="row placeholders">
                <table id="jobsTable" class="table table-hover table-striped" data-toggle="table"
                       data-url="<c:url value="/api/v1/job"/>" data-response-handler="formatJobTable"
                       data-show-refresh="true" data-show-columns="true" data-search="true"
                       data-click-to-select="true" data-height="100%">
                    <thead>
                    <tr>
                        <th data-align="center" data-field="successIcon"></th>
                        <th data-align="center" data-field="consoleIcon"></th>
                        <th data-align="left" data-field="name">Job Name</th>
                        <th data-align="left" data-field="providerName">Provider</th>
                        <th data-align="left" data-field="publisherName">Publisher</th>
                        <th data-align="left" data-field="created">Created</th>
                        <th data-align="left" data-field="duration">Duration</th>
                        <th data-align="left" data-field="completed">Completed</th>
                        <th data-align="left" data-field="stateLabel">State</th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
        <div class="col-sm-3 col-sm-offset-9 sidebar" id="sidebar">
            <h3>Job Details <span id="details-successLabel" style="float:right"></span></h3>
            <div id="job-details-panel" class="job-details">
                <div class="job-label">Name:</div>
                <div id="details-name" class="job-data"></div>
                <div class="job-label">Provider:</div>
                <div id="details-providerName" class="job-data"></div>
                <div class="job-label">Publisher:</div>
                <div id="details-publisherName" class="job-data"></div>
                <div class="job-label">Artifacts:</div>
                <div class="job-data">
                    <div class="btn-group btn-group-xs" role="group" aria-label="...">
                        <div class="btn-group btn-group-xs" role="group">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Provider
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                                <li><a href="#" data-toggle="modal" data-target="#modalTextDetail" data-modal-title="Provider Payload" data-api="/payload/provider">View Payload</a></li>
                                <li><a href="#" onClick="downloadJobArtifact('/payload/provider?q=1')">Download Payload</a></li>
                            </ul>
                        </div>
                        <div class="btn-group btn-group-xs" role="group">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Publisher
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                                <li><a href="#" data-toggle="modal" data-target="#modalTextDetail" data-modal-title="Publisher Payload" data-api="/payload/publisher">View Payload</a></li>
                                <li><a href="#" onClick="downloadJobArtifact('/payload/publisher?q=1')">Download Payload</a></li>
                            </ul>
                        </div>
                        <div class="btn-group btn-group-xs" role="group">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Result
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu dropdown-menu-right">
                                <li><a href="#" data-toggle="modal" data-target="#modalTextDetail" data-modal-title="Result" data-api="/result">View</a></li>
                                <li><a href="#" onClick="downloadJobArtifact('/result?q=1')">Download</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="job-label">Messages:</div>
                <div class="job-data">
                    <button type="button" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modalTextDetail" data-modal-title="Messages" data-api="/message">View Processing Messages</button>
                </div>
                <div class="job-label" id="job-console-label">Console:</div>
                <div class="job-data" id="job-console">
                    <a type="button" class="btn btn-primary btn-xs" role="button" id="job-console-button" href="#"></a>
                </div>
                <div class="job-label">Created:</div>
                <div id="details-created" class="job-data"></div>
                <div class="job-label">Started:</div>
                <div id="details-started" class="job-data"></div>
                <div class="job-label">Completed:</div>
                <div id="details-completed" class="job-data"></div>
                <div class="job-label">Duration:</div>
                <div id="details-duration" class="job-data"></div>
                <div class="job-label">State:</div>
                <div id="details-stateLabel" class="job-data"></div>
                <div class="job-label">Success:</div>
                <div id="details-success" class="job-data"></div>
                <div class="job-label">UUID:</div>
                <div id="details-uuid" class="job-data"></div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/fragments/common-modals.jsp"/>
</div>
<jsp:include page="/WEB-INF/fragments/footer.jsp"/>
</body>
</html>
