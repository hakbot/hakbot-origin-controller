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

$nessus = function() {

    var info = {};         // general information about the scan
    var vulns = [];        // listing of all vulnerabilities found in scan
    var compliance = [];   // listing of all compliance issues found in scan
    var comphosts = [];    // hosts scanned for compliance
    var hosts = [];        // hosts scanned for vulnerabilities
    var remediations = []; // a list of recommended remediations //todo

    /**
     * Populates the DOM with the results
     */
    function redrawConsole() {
        // Update scan details
        $('#scanName').html(info.scanName);
        $('#status').html(info.status);
        $('#policy').html(info.policy);
        $('#scanner').html(info.scannerName);
        $('#start').html(timeConverter(info.scannerStart));
        $('#end').html(timeConverter(info.scannerEnd));
        $('#elapsed').html(getDuration(info.scannerStart, info.scannerEnd));

        // Populate the badges in the breadcrumb
        $('#hostsBadge').html((hosts.length + comphosts.length));
        $('#vulnerabilitiesBadge').html(vulns.length);
        $('#complianceBadge').html(compliance.length);
        $('#remediationBadge').html(remediations.length);
        
        // Dynamically generate host progress bars for every host
        var hostBlock = '';
        for(var hostCount = 0; hostCount < hosts.length; hostCount++) {
            var host = hosts[hostCount];
            hostBlock += generateHostProgressBar(host.hostname, host.critical, host.high, host.medium, host.low, host.info);
        }
        for(var compHostCount = 0; compHostCount < comphosts.length; compHostCount++) {
            var compHost = comphosts[compHostCount];
            hostBlock += generateCompHostProgressBar(compHost.hostname, compHost.critical, compHost.medium, compHost.low);
        }
        $('#hosts').html(hostBlock);

        // Update vulnerabilities information
        $('#vulnerabilitiesTable').bootstrapTable({data: vulns});

        // Update compliance information
        $('#complianceTable').bootstrapTable({data: compliance});

        // Update remediation information
        $('#remediationTable').bootstrapTable({data: remediations});

        // Initialize all tooltips
        $('[data-toggle="tooltip"]').tooltip();
    }

    function generateHostProgressBar(hostname, critical, high, medium, low, info) {
        var percentCritical = (critical/(critical+high+medium+low+info))*100;
        var percentHigh = (high/(critical+high+medium+low+info))*100;
        var percentMedium = (medium/(critical+high+medium+low+info))*100;
        var percentLow = (low/(critical+high+medium+low+info))*100;
        var percentInfo = (info/(critical+high+medium+low+info))*100;
        var block = '<h4>' + hostname + '</h4><div class="progress">';
        block += '<div class="progress-bar severity-critical" data-toggle="tooltip" data-placement="top" title="Critical: ' + critical + ' (' + Math.round(percentCritical*10)/10 + '%)" style="width:' + percentCritical+ '%">' + critical + '</div>';
        block += '<div class="progress-bar severity-high" data-toggle="tooltip" data-placement="top" title="High: ' + high + ' (' + Math.round(percentHigh*10)/10 + '%)" style="width:' + percentHigh + '%">' + high + '</div>';
        block += '<div class="progress-bar severity-medium" data-toggle="tooltip" data-placement="top" title="Medium: ' + medium + ' (' + Math.round(percentMedium*10)/10 + '%)" style="width:' + percentMedium + '%">' + medium + '</div>';
        block += '<div class="progress-bar severity-low" data-toggle="tooltip" data-placement="top" title="Low: ' + low + ' (' + Math.round(percentLow*10)/10 + '%)" style="width:' + percentLow + '%">' + low + '</div>';
        block += '<div class="progress-bar severity-info" data-toggle="tooltip" data-placement="top" title="Info: ' + info + ' (' + Math.round(percentInfo*10)/10 + '%)" style="width:' + percentInfo + '%">' + info + '</div>';
        block += '</div>';
        return block;
    }

    function generateCompHostProgressBar(hostname, failed, warning, passed) {
        var percentFailed = (failed/(failed+warning+passed))*100;
        var percentWarning = (warning/(failed+warning+passed))*100;
        var percentPassed = (passed/(failed+warning+passed))*100;
        var block = '<h4>' + hostname + '</h4><div class="progress">';
        block += '<div class="progress-bar status-failed" data-toggle="tooltip" data-placement="top" title="Failed: ' + failed + ' (' + Math.round(percentFailed*10)/10 + '%)" style="width:' + percentFailed+ '%">' + failed + '</div>';
        block += '<div class="progress-bar status-warning" data-toggle="tooltip" data-placement="top" title="Warning: ' + warning + ' (' + Math.round(percentWarning*10)/10 + '%)" style="width:' + percentWarning + '%">' + warning + '</div>';
        block += '<div class="progress-bar status-passed" data-toggle="tooltip" data-placement="top" title="Passed: ' + passed + ' (' + Math.round(percentPassed*10)/10 + '%)" style="width:' + percentPassed + '%">' + passed + '</div>';
        block += '</div>';
        return block;
    }

    /**
     * Returns the provider-specific job console data
     */
    function getJobConsoleData(uuid) {
        $nessus.uuid = uuid;
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
     * Parses and normalizes the JSON response from the controller
     */
    function parseConsoleData(data) {
        info = {};
        // data is the JSON response from the console
        info.status = data.info.status;
        info.policy = data.info.policy;
        info.scannerStart = data.info.scanner_start;
        info.scannerEnd = data.info.scanner_end;
        info.scanStart = data.info.scan_start;
        info.scanEnd = data.info.scan_end;
        info.timestamp = data.info.timestamp;
        info.hostCount = data.info.hostcount;
        info.scanName = data.info.name;
        info.scannerName = data.info.scanner_name;

        vulns = [];
        for(var i = 0; i < data.vulnerabilities.length; i++) {
            vulns.push(formatVulnerabilityFinding(createFinding(data.vulnerabilities[i])));
        }

        compliance = [];
        for(var j = 0; j < data.compliance.length; j++) {
            compliance.push(formatComplianceFinding(createFinding(data.compliance[j])));
        }

        comphosts = [];
        for(var k = 0; k < data.comphosts.length; k++) {
            comphosts.push(createHost(data.comphosts[k]));
        }

        hosts = [];
        for(var l = 0; l < data.hosts.length; l++) {
            hosts.push(createHost(data.hosts[l]));
        }
    }

    function createHost(jsonFragment) {
        var host = {};
        host.totalChecksConsidered = jsonFragment.totalchecksconsidered;
        host.numChecksConsidered = jsonFragment.numchecksconsidered;
        host.scanProgressTotal = jsonFragment.scanprogresstotal;
        host.scanProgressCurrent = jsonFragment.scanprogresscurrent;
        host.score = jsonFragment.score;
        host.progress = jsonFragment.progress;
        host.critical = jsonFragment.critical;
        host.high = jsonFragment.high;
        host.medium = jsonFragment.medium;
        host.low = jsonFragment.low;
        host.info = jsonFragment.info;
        host.severity = jsonFragment.severity;
        host.hostId = jsonFragment.host_id;
        host.hostname = jsonFragment.hostname;
        return host;
    }

    function createFinding(jsonFragment) {
        var finding = {};
        finding.count = jsonFragment.count;
        finding.pluginName = jsonFragment.plugin_name;
        finding.vulnIndex = jsonFragment.vuln_index;
        finding.severity = jsonFragment.severity;
        finding.pluginId = jsonFragment.plugin_id;
        finding.severityIndex = jsonFragment.severity_index;
        finding.pluginFamily = jsonFragment.plugin_family;
        return finding;
    }
    
    function formatVulnerabilityFinding(finding) {
        if (finding.severity == 0) {
            finding.severityOrder = 5;
            finding.severityLabel = '<span class="label label-default label-md severity-info">Info</span>';    
        } else if (finding.severity == 4) {
            finding.severityOrder = 1;
            finding.severityLabel = '<span class="label label-default label-md severity-critical">Critical</span>';
        } else if (finding.severity == 3) {
            finding.severityOrder = 2;
            finding.severityLabel = '<span class="label label-default label-md severity-high">High</span>';
        } else if (finding.severity == 2) {
            finding.severityOrder = 3;
            finding.severityLabel = '<span class="label label-default label-md severity-medium">Medium</span>';
        } else if (finding.severity == 1) {
            finding.severityOrder = 4;
            finding.severityLabel = '<span class="label label-default label-md severity-low">Low</span>';
        }
        return finding;
    }

    function formatComplianceFinding(finding) {
        if (finding.severity == 1) {
            finding.severityOrder = 1;
            finding.severityLabel = '<span class="label label-default label-md status-failed">Failed</span>';
        } else if (finding.severity == 2) {
            finding.severityOrder = 2;
            finding.severityLabel = '<span class="label label-default label-md status-warning">Warning</span>';
        } else if (finding.severity == 3) {
            finding.severityOrder = 3;
            finding.severityLabel = '<span class="label label-default label-md status-passed">Passed</span>';
        }
        return finding;
    }

    /**
     * Makes the following functions 'public'
     */
    return{
        info:info,
        vulns:vulns,
        compliance:compliance,
        remediations:remediations,
        hosts:hosts,
        comphosts:comphosts,
        getJobConsoleData:getJobConsoleData
    }

}();
