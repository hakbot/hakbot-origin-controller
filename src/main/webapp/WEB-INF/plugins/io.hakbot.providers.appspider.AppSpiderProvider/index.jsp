<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="e" uri="https://www.owasp.org/index.php/OWASP_Java_Encoder_Project" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/fragments/header.jsp"/>
    <title>Hakbot Origin Controller - AppSpider Pro</title>
</head>
<body>
<jsp:include page="/WEB-INF/fragments/navbar.jsp"/>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12 col-md-12 main" id="main">
            <h3><e:forHtml value="${requestScope.job.name}"/><span style="float:right">AppSpider Pro</span></h3>

            <table id="consoleTimeTable" class="table" data-toggle="table" data-click-to-select="false" data-height="100%">
                <thead>
                <tr>
                    <th data-align="center">Start Time</th>
                    <th data-align="center">Elapsed Time</th>
                    <th data-align="center">Time Remaining</th>
                    <th data-align="center">Completed On</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td id="startTime"></td>
                    <td id="elapsedTime"></td>
                    <td id="timeRemaining"></td>
                    <td id="endTime"></td>
                </tr>
                </tbody>
            </table>

        </div>

        <div class="col-sm-9 col-md-9 main">
            <h4>Overall Progress</h4>
            <div class="progress">
                <div id="percentComplete" class="progress-bar progress-bar-success progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="min-width:2em; width:0">
                </div>
            </div>

            <h4>Crawling Progress</h4>
            <div class="progress">
                <div id="crawlingProgress" class="progress-bar progress-bar-info progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="min-width:2em; width:0">
                </div>
            </div>

            <h4>Attack Progress</h4>
            <div class="progress">
                <div id="attackProgress" class="progress-bar progress-bar-danger progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="min-width:2em; width:0">
                </div>
            </div>
        </div>

        <div class="col-sm-3 col-md-3 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Network Monitor</h3>
                </div>
                <table class="table">
                    <tr>
                        <td>Requests:</td>
                        <td id="requests"></td>
                    </tr>
                    <tr>
                        <td>Failed Requests:</td>
                        <td id="failedRequests"></td>
                    </tr>
                    <tr>
                        <td>Request Delay (ms):</td>
                        <td id="requestDelay"></td>
                    </tr>
                    <tr>
                        <td>Speed (KB/sec):</td>
                        <td id="speed"></td>
                    </tr>
                    <tr>
                        <td>Response Time (ms):</td>
                        <td id="responseTime"></td>
                    </tr>
                </table>
            </div>
        </div>

        <div class="col-sm-12 col-md-12 main">
            <div class="panel with-nav-tabs panel-default">
                <div class="panel-heading">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#eventsTab" data-toggle="tab">Events</a></li>
                        <li><a href="#attacksTab" data-toggle="tab">Attacks</a></li>
                    </ul>
                </div>
                <div class="panel-body">
                    <div class="tab-content">
                        <div class="tab-pane active" id="eventsTab">
                            <table id="consoleEventTable" class="table">
                                <thead>
                                <tr>
                                    <th data-align="left" data-field="dateTime">Date/Time</th>
                                    <th data-align="left" data-field="event">Description</th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                        <div class="tab-pane" id="attacksTab">
                            <table id="consoleAttacksTable" class="table">
                                <thead>
                                <tr>
                                    <th data-align="left" data-field="name">Module</th>
                                    <th data-align="left" data-field="attempted">Attempted</th>
                                    <th data-align="left" data-field="vulnerable">Vulnerable</th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
<jsp:include page="/WEB-INF/fragments/footer.jsp"/>
<script type="text/javascript" src="<c:url value="/console/static/io.hakbot.providers.appspider.AppSpiderProvider/functions.js"/>"></script>
<script type="text/javascript">
    $appspider.getJobConsoleData('<e:forJavaScript value="${requestScope.job.uuid}"/>');
    setInterval(function() {
        $appspider.getJobConsoleData('<e:forJavaScript value="${requestScope.job.uuid}"/>');
    }, 30 * 1000);
</script>
</body>
</html>
