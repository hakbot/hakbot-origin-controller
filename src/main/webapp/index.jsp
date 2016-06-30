<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                       data-url="<c:url value="/api/job"/>" data-response-handler="formatJobTable"
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

    <div class="modal fade" id="modalTextDetail" tabindex="-1" role="dialog" aria-labelledby="modalTitle">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="modalTitle">Messages</h4>
                </div>
                <div class="modal-body">
                    <textarea id="details-result" style="min-width:100%; max-width:100%; min-height:100%; max-height:100%; resize:none; font-family:monospace;"></textarea>
                </div>
                <div class="modal-footer">
                    <div class="form-group">
                        <label id="decodeToggleLabel" for="decodeToggle">Decode: </label>
                        <input type="checkbox" class="form-control" id="decodeToggle" data-toggle="toggle">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="modalSystem" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">System</h4>
                </div>
                <div class="modal-body">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#aboutTab" data-toggle="tab">About</a></li>
                        <li><a href="#providersTab" data-toggle="tab">Providers</a></li>
                        <li><a href="#publishersTab" data-toggle="tab">Publishers</a></li>
                    </ul>
                    <div class="tab-content" style="margin-top:20px; max-height:calc(85vh - 250px); overflow-y: auto;">
                        <div class="tab-pane active" id="aboutTab">
                            <div style="max-width:150px; display:inline-block">
                                <img src="<c:url value="/images/hakbot-logo-500x500.png"/>" width="150" height="150">
                            </div>
                            <div style="margin-left:30px; display:inline-block">
                                <h3 id="systemAppName"></h3>
                                <div id="systemAppVersion"></div>
                                <div><a href="http://hakbot.io/">Website</a> - <a href="https://github.com/hakbot">GitHub</a></div>
                            </div>
                        </div>
                        <div class="tab-pane" id="providersTab"></div>
                        <div class="tab-pane" id="publishersTab"></div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal" id="modal-login" tabindex="-1" role="dialog" aria-labelledby="modal-login-label" aria-hidden="true">
        <div class="vertical-alignment-helper">
            <div class="modal-dialog vertical-align-center">
                <div class="modal-content login-modal-content">
                    <div class="modal-header login-modal-header login-header">
                        <img src="images/hakbot-header-logo.png"/> Hakbot Origin Controller
                    </div>
                    <div class="modal-body login-modal-body">
                        <form id="login-form" role="form" action="" method="post" class="login-form" autocomplete="off">
                            <div class="form-group">
                                <label class="sr-only" for="username">Username</label>
                                <input type="text" name="username" placeholder="Username..." class="form-control" id="username">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="password">Password</label>
                                <input type="password" name="password" placeholder="Password..." class="form-control" id="password">
                            </div>
                            <button type="submit" class="btn btn-primary btn-block">Log In</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<jsp:include page="/WEB-INF/fragments/footer.jsp"/>
</body>
</html>
