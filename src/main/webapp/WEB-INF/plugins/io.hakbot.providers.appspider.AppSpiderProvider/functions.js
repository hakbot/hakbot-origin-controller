/*
 * This file is part of Hakbot Origin Controller.
 *
 * Hakbot Origin Controller is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * Hakbot Origin Controller is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Hakbot Origin Controller. If not, see http://www.gnu.org/licenses/.
 */

$appspider = function() {

    var status = {};
    var events = [];
    var modules = [];

    /**
     * Populates the DOM with the results of the status object.
     */
    function redrawConsole() {
        // Update progress bar
        var percentComplete = $('#percentComplete');
        percentComplete.html(status.scanProgress + "%");
        percentComplete.css('width', status.scanProgress + "%");
        if (status.scanProgress = 100 && !status.running) {
            percentComplete.removeClass("progress-bar-striped");
        }

        // Update crawling status progress bar
        var crawlingProgress = $('#crawlingProgress');
        crawlingProgress.html(status.linksCrawled + "/" + (status.linksCrawled + status.linksInQueue));
        crawlingProgress.css('width',((status.linksCrawled / (status.linksCrawled + status.linksInQueue)) * 100 )+ "%");
        if (status.scanProgress = 100 && !status.running) {
            crawlingProgress.removeClass("progress-bar-striped");
        }

        // Update attack status progress bar
        var attackProgress = $('#attackProgress');
        attackProgress.html(status.attacked + "/" + (status.attacked + status.attacksInQueue));
        attackProgress.css('width',((status.attacked / (status.attacked + status.attacksInQueue)) * 100 )+ "%");
        if (status.scanProgress = 100 && !status.running) {
            attackProgress.removeClass("progress-bar-striped");
        }

        // Update time information
        $('#startTime').html(status.startTime);
        $('#endTime').html(status.endTime);
        $('#elapsedTime').html(status.elapsedTime);
        $('#timeRemaining').html(status.timeRemaining);
        $('#consoleTimeTable').bootstrapTable('resetView');
        
        // Update network information
        $('#requests').html(status.requests);
        $('#failedRequests').html(status.failedRequests);
        $('#requestDelay').html(status.dripDelay);
        var kbpersec = Math.round((status.networkSpeed/1000) * 100) / 100;
        $('#speed').html(kbpersec);
        $('#responseTime').html(status.averageResponseTime);

        // Update event information
        $('#consoleEventTable').bootstrapTable({data: $appspider.events});

        // Update attack module information
        $('#consoleAttacksTable').bootstrapTable({data: $appspider.modules});
    }

    /**
     * Returns the provider-specific job console data
     */
    function getJobConsoleData(uuid) {
        $appspider.uuid = uuid;
        $.ajax({
            url: contextPath() + URL_CONSOLE_JOB + "/" + uuid,
            contentType: CONTENT_TYPE_JSON,
            dataType: DATA_TYPE,
            type: METHOD_GET,
            success: function (data) {
                parseConsoleData(data);
                redrawConsole();
            }
        });
    }

    /**
     * Parses and normalizes the JSON response from the controller creating
     * a 'status' object with all available properties.
     */
    function parseConsoleData(data) {
        // data is the JSON response from the console
        status.scanProgress = data.status2.m_iScanProgress;
        status.timeRemaining = data.status2.m_szTimeRemaining;
        status.elapsedTime = data.status2.m_szTimeElapsed;
        status.token = data.Token;
        status.running = data.Running;
        status.crashed = data.Crashed;
        status.scanned = data.Scanned;
        status.loggedIn = data.LoggedIn;
        status.linksInQueue = data.LinksInQueue;
        status.linksCrawled = data.LinksCrawled;
        status.attacksInQueue = data.AttacksInQueue;
        status.attacked = data.Attacked;
        status.vulnerable = data.Vulnerable;
        status.requests = data.Requests;
        status.failedRequests = data.FailedRequests;
        status.networkSpeed = data.NetworkSpeed;
        status.dripDelay = data.DripDelay;
        status.averageResponseTime = data.AvgRespTime;
        status.startTime = timeConverter(data.StartTime);
        status.endTime = timeConverter(data.EndTime);
        status.elapsedTime = data.Elapsed;

        events = [];
        for(var i = 0; i < data.EventList.EVENT.length; i++) {
            var objE = data.EventList.EVENT[i];
            var event = {};
            event.timestamp = objE.Time;
            event.dateTime = timeConverter(objE.Time);
            event.event = objE.Event;
            event.details = objE.Details;
            event.error = objE.Error;
            event.errorCode = objE.ErrorCode;
            events.push(event);
        }

        modules = [];
        for(var j = 0; j < data.ModuleStatusList.MODULESTATUS.length; j++) {
            var objM = data.ModuleStatusList.MODULESTATUS[j];
            var module = {};
            module.name = objM.ModuleName;
            module.passiveAnalysis = objM.PassiveAnalysis;
            module.attempted = objM.Attempted;
            module.vulnerable = objM.Vulnerable;
            modules.push(module);
        }
        
        // Sort events by timestamp
        events.sort(function(a, b) {
            return b.timestamp - a.timestamp; // descending order
        });

        // Sort attack modules by name
        modules.sort(function(a, b) {
            if(a.name < b.name) return -1;
            if(a.name > b.name) return 1;
            return 0;
        });
    }

    /**
     * Makes the following functions 'public'
     */
    return{
        status:status,
        events:events,
        modules:modules,
        getJobConsoleData:getJobConsoleData
    }

}();
