<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="<c:url value="/images/favicon.png"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/bootstrap/css/bootstrap.min.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/bootstrap-table/bootstrap-table.min.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/fonts/opensans/opensans.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/style.css"/>">
    <title>Hakbot Origin Controller</title>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <div style="float:left">
                <img src="images/hakbot-header-logo.png" style="margin-top:2px; margin-right:10px"/>
            </div>
            <div style="float:right">
                <a class="navbar-brand" href="#"> Origin Controller</a>
            </div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><span class="glyphicon glyphicon-cog" aria-hidden="true"></span> System</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-book" aria-hidden="true"></span> Help</a></li>
            </ul>
        </div>
    </div>
</nav>
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
                        <th data-align="center" data-field="success"></th>
                        <th data-align="left" data-field="name">Job Name</th>
                        <th data-align="left" data-field="provider">Provider</th>
                        <th data-align="left" data-field="publisher">Publisher</th>
                        <th data-align="left" data-field="created">Created</th>
                        <th data-align="left" data-field="duration">Duration</th>
                        <th data-align="left" data-field="completed">Completed</th>
                        <th data-align="left" data-field="state">State</th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
        <div class="col-sm-3 col-sm-offset-9 col-md-2 col-sm-offset-10 sidebar" id="sidebar">
            <h3>Job Details</h3>
            <div id="job-details-panel" class="job-details">
                <div class="job-label">Name:</div>
                <div id="details-name" class="job-data"></div>
                <div class="job-label">Provider:</div>
                <div id="details-provider" class="job-data"></div>
                <div class="job-label">Publisher:</div>
                <div id="details-publisher" class="job-data"></div>

                <div class="job-label">Artifacts:</div>
                <div class="job-data">
                    <div class="btn-group btn-group-xs" role="group" aria-label="...">
                        <div class="btn-group btn-group-xs" role="group">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Provider
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                                <li><a href="#">View Payload</a></li>
                                <li><a href="#">Download Payload</a></li>
                            </ul>
                        </div>
                        <div class="btn-group btn-group-xs" role="group">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Publisher
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                                <li><a href="#">View Payload</a></li>
                                <li><a href="#">Download Payload</a></li>
                            </ul>
                        </div>
                        <button type="button" class="btn btn-default">Messages</button>
                    </div>
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
                <div id="details-state" class="job-data"></div>
                <div class="job-label">Success:</div>
                <div id="details-success" class="job-data"></div>
                <div class="job-label">Result:</div>
                <div id="details-result" class="job-data"></div>
                <div class="job-label">UUID:</div>
                <div id="details-uuid" class="job-data"></div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<c:url value="/assets/jquery/jquery-2.2.3.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/assets/bootstrap/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/assets/bootstrap-table/bootstrap-table.js"/>"></script>
<script type="text/javascript" src="<c:url value="/assets/functions.js"/>"></script>
</body>
</html>
