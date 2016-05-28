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

/**
 * Constants
 */
var CONTENT_TYPE = 'application/json';
var DATA_TYPE = "json";
var METHOD_GET = "GET";
var METHOD_POST = "POST";
var URL_ABOUT = "/version";
var URL_PROVIDERS = "/providers";
var URL_PUBLISHERS = "/publishers";
var STATE_CREATED = "CREATED";
var STATE_IN_QUEUE = "IN_QUEUE";
var STATE_IN_PROGRESS = "IN_PROGRESS";
var STATE_COMPLETED = "COMPLETED";
var STATE_PUBLISHED = "PUBLISHED";
var STATE_CANCELED = "CANCELED";
var STATE_UNAVAILABLE = "UNAVAILABLE";
var PLUGIN_PROVIDER = "provider";
var PLUGIN_PUBLISHER = "publisher";

/**
 * Variables
 */
var initialized = false;
var about;
var providers;
var publishers;


function contextPath() {
    //return $.cookie("CONTEXTPATH");
    return "/api";
}

/**
 * Retrieve a listing of all providers & publishers and stores JSON response in variables
 */
$(document).ready(function () {
    var successAbout=false, successProviders=false, successPublishers=false;
    $.ajax({
        url: contextPath() + URL_ABOUT,
        contentType: CONTENT_TYPE,
        dataType: DATA_TYPE,
        type: METHOD_GET,
        async: false,
        success: function (data) {
            about = data;
            successAbout = true;
        }
    });
    $.ajax({
        url: contextPath() + URL_PROVIDERS,
        contentType: CONTENT_TYPE,
        dataType: DATA_TYPE,
        type: METHOD_GET,
        async: false,
        success: function (data) {
            providers = data;
            successProviders = true;
        }
    });
    $.ajax({
        url: contextPath() + URL_PUBLISHERS,
        contentType: CONTENT_TYPE,
        dataType: DATA_TYPE,
        type: METHOD_GET,
        async: false,
        success: function (data) {
            publishers = data;
            successPublishers = true;
        }
    });

    if (successAbout && successProviders && successPublishers) {
        initialized = true;
    }

    for (var i=0; i<providers.length; i++) {
        console.log(providers[i].class);
        console.log(resolvePluginByClass(PLUGIN_PROVIDER, providers[i].class).name);
    }
    for (var j=0; j<publishers.length; j++) {
        console.log(publishers[j].class);
        console.log(resolvePluginByClass(PLUGIN_PUBLISHER, publishers[j].class).name);
    }

    console.log(getAppName());
    console.log(getAppVersion());
    
});

function resolvePluginByClass(pluginType, className) {
    if (PLUGIN_PROVIDER == pluginType) {
        for (var i=0; i<providers.length; i++) {
            if (providers[i].class == className) {
                return providers[i];
            }
        }
    }
    if (PLUGIN_PUBLISHER == pluginType) {
        for (i=0; i<publishers.length; i++) {
            if (publishers[i].class == className) {
                return publishers[i];
            }
        }
    }
    return null;
}

function getAppName() {
    return about.application;
}

function getAppVersion() {
    return about.version;
}

function formatJobTable(res) {
    for (var i=0; i<res.length; i++) {
        res[i].provider = resolvePluginByClass(PLUGIN_PROVIDER, res[i].provider).name;
        if (resolvePluginByClass(PLUGIN_PUBLISHER, res[i].publisher)) {
            res[i].publisher = resolvePluginByClass(PLUGIN_PUBLISHER, res[i].publisher).name;    
        }
        res[i].duration = getDuration(res[i].created, res[i].completed);
        res[i].created = timeConverter(res[i].created);
        res[i].started = timeConverter(res[i].started);
        res[i].completed = timeConverter(res[i].completed);
        res[i].success = getSuccessIcon(res[i].success, res[i].state);
    }
    return res;
}

function timeConverter(timestamp) {
    if (timestamp == null || timestamp == "" || timestamp == 0) {
        return;
    }
    var a = new Date(timestamp);
    var months = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
    var year = a.getFullYear();
    var month = months[a.getMonth()];
    var date = a.getDate();
    var hour = a.getHours();
    var min = a.getMinutes();
    var sec = a.getSeconds();
    var time = date + ' ' + month + ' ' + year + ' ' + hour + ':' + min + ':' + sec ;
    return time;
}

function getDuration(startTimestamp, endTimestamp) {
    if (startTimestamp == null || startTimestamp == "" || startTimestamp == 0 || endTimestamp == null || endTimestamp == "" || endTimestamp == 0) {
        return;
    }
    var durationInMinutes = Math.ceil(((endTimestamp - startTimestamp) /1000) /60);
    if (durationInMinutes < 60)  {
        return durationInMinutes + " min";
    } else {
        return Math.floor(durationInMinutes / 60) + " hrs";
    }
}

function getSuccessIcon(success, state) {
    if (success) {
        return '<span class="glyphicon glyphicon glyphicon-ok-circle" style="color:seagreen" aria-hidden="true"></span>';
    } else if (state == STATE_CREATED || state == STATE_IN_QUEUE || state == STATE_IN_PROGRESS) {
        return '<span class="glyphicon glyphicon-hourglass" style="color:dimgrey" aria-hidden="true"></span>';
    } else if (state == STATE_COMPLETED || state == STATE_PUBLISHED){
        return '<span class="glyphicon glyphicon-warning-sign" style="color:darkred" aria-hidden="true"></span>';
    } else if (state == STATE_UNAVAILABLE ){
        return '<span class="glyphicon glyphicon-time" style="color:lightslategrey" aria-hidden="true"></span>';
    } else if (state == STATE_CANCELED ){
        return '<span class="glyphicon glyphicon-ban-circle" style="color:lightslategrey" aria-hidden="true"></span>';
    }
}

$('#jobsTable').on('click-row.bs.table', function (e, job, $element) {
    $('#main').removeClass("col-sm-12");
    $('#main').removeClass("col-md-12");
    $('#main').addClass("col-sm-9");
    $('#main').addClass("col-md-10");
    $('#sidebar').css("display", "block");
    $('#jobsTable').bootstrapTable('resetView');
    $('#details-uuid').html(job.uuid);
    $('#details-name').html(job.name);
    $('#details-provider').html(job.provider);
    $('#details-publisher').html(job.publisher);
    $('#details-message').html(job.message);
    $('#details-providerPayload').html(job.providerPayload);
    $('#details-publisherPayload').html(job.publisherPayload);
    $('#details-created').html(job.created);
    $('#details-started').html(job.started);
    $('#details-completed').html(job.completed);
    $('#details-duration').html(job.duration);
    $('#details-state').html(job.state);
    $('#details-success').html(job.success);
    $('#details-result').html(job.result);
});
$('#jobsTable').on('click', 'tbody tr', function(event) {
    $(this).addClass('highlight').siblings().removeClass('highlight');
});
