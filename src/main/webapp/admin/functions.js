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
 * Called by bootstrap table to format the data in the table.
 */
function formatTeamTable(res) {
    for (var i=0; i<res.length; i++) {
        if (res[i].apiKeys === undefined) {
            res[i].apiKeysNum = 0;
        } else {
            res[i].apiKeysNum = res[i].apiKeys.length;
        }
        if (res[i].ldapUsers === undefined) {
            res[i].membersNum = 0;
        } else {
            res[i].membersNum = res[i].ldapUsers.length;
        }

        if (res[i].hakmaster == true) {
            res[i].hakmasterIcon = "&#10004;";
        } else {
            res[i].hakmasterIcon = "";
        }
    }
    return res;
}

/**
 * Called by bootstrap table to format the data in the table.
 */
function formatUserTable(res) {
    for (var i=0; i<res.length; i++) {
    }
    return res;
}

var $teamTable = $('#teamsTable');
$teamTable.on("click-row.bs.table", function(e, row, $tr) {
    //console.log("Clicked on: " + $(e.target).attr('class'), [e, row, $tr]);
    if ($tr.next().is('tr.detail-view')) {
        $teamTable.bootstrapTable('collapseRow', $tr.data('index'));
        $teamTable.expanded = false;
    } else {
        $teamTable.bootstrapTable('collapseAllRows');
        $teamTable.bootstrapTable('expandRow', $tr.data('index'));
        $teamTable.expanded = true;
        $teamTable.expandedUuid = row.uuid;
    }
});

$teamTable.on("load-success.bs.table", function(e, data) {
    if ($teamTable.expanded == true) {
        $.each(data, function(i, team) {
            if (team.uuid == $teamTable.expandedUuid) {
                $teamTable.bootstrapTable('expandRow', i);
            }
        });
    }
});

function teamDetailFormatter(index, row) {
    var hakmasterChecked = '';
    if (row.hakmaster == true) {
        hakmasterChecked = 'checked="checked"';
    }
    var html = [];
    //$.each(row, function (key, value) {
    //    html.push('<p><b>' + key + ':</b> ' + value + '</p>');
    //});


    var apiKeysHtml = '';
    if (!(row.apiKeys === undefined)) {
        for (i = 0; i < row.apiKeys.length; i++) {
            apiKeysHtml += `
            <li class="list-group-item" id="container-apikey-${row.apiKeys[i].key}">
                <a href="#" id="delete-${row.apiKeys[i].key}" onclick="deleteApiKey('${row.apiKeys[i].key}')" data-toggle="tooltip" title="Delete API Key">
                    <span class="glyphicon glyphicon-trash glyphicon-input-form pull-right"></span>
                </a>
                <a href="#" id="regen-${row.apiKeys[i].key}" onclick="regenerateApiKey('${row.apiKeys[i].key}')" data-toggle="tooltip" title="Regenerate New API Key">
                    <span class="glyphicon glyphicon-refresh glyphicon-input-form pull-right spacer-horizontal-10"></span>
                </a>
                <span id="apikey-${row.apiKeys[i].key}">${row.apiKeys[i].key}</span>
            </li>`;
        }
    }
    apiKeysHtml += `
            <li class="list-group-item" id="container-no-apikey">
                <a href="#" id="add-apikey" onclick="addApiKey('${row.uuid}')" data-toggle="tooltip" title="Add API Key">
                    <span class="glyphicon glyphicon-plus-sign glyphicon-input-form pull-right"></span>
                </a>
                <span>&nbsp;</span>
            </li>`;



    var membersHtml = '';
    if (!(row.ldapUsers === undefined)) {
        for (i = 0; i < row.ldapUsers.length; i++) {
            membersHtml += `
            <li class="list-group-item">
                <a href="#" onclick="removeTeamMembership('${row.uuid}', '${row.ldapUsers[i].username}')" data-toggle="tooltip" title="Remove User From Team">
                    <span class="glyphicon glyphicon-trash glyphicon-input-form pull-right"></span>
                </a>
                ${row.ldapUsers[i].username}
            </li>`;
        }
    }

    var template = `
    <div class="col-sm-6 col-md-6">
    <form id="form-${row.uuid}">
        <div class="form-group">
            <label for="inputTeamName">Team Name</label>
            <input type="text" class="form-control" id="inputTeamName-${row.uuid}" placeholder="Name" value="${row.name}" data-team-uuid="${row.uuid}">
        </div>
        <div class="form-group">
            <label for="inputApiKeys">API Keys</label>
            <ul class="list-group" id="inputApiKeys">
                ${apiKeysHtml}
            </ul>
        </div> 
        <div class="form-group">
            <label for="inputApiKeys">Hakmaster</label>
            <input type="checkbox" class="checkbox-inline" id="inputTeamHakmaster-${row.uuid}" placeholder="Hakmaster" ${hakmasterChecked} data-team-uuid="${row.uuid}">
        </div> 
    </div>
    <div class="col-sm-6 col-md-6">
        <div class="form-group">
            <label for="inputTeamMembers">Team Members</label>
            <ul class="list-group" id="inputTeamMembers">
                ${membersHtml}
            </ul>
        </div>
        <button type="button" class="btn btn-danger pull-right" id="deleteTeam-${row.uuid}" data-team-uuid="${row.uuid}">Delete Team</button>
    </form>
    </div>
    <script type="text/javascript">
        $('#inputTeamName-${row.uuid}').keypress(debounce(updateTeam, 750));
        $('#inputTeamHakmaster-${row.uuid}').change(updateTeam);
        $('#deleteTeam-${row.uuid}').on('click', deleteTeam);
    </script>
`;
    html.push(template);
    return html.join('');
}

function createTeam() {
    var inputField = $('#createTeamNameInput');
    var teamName = inputField.val();
    $.ajax({
        url: contextPath() + URL_TEAM,
        contentType: CONTENT_TYPE_JSON,
        dataType: DATA_TYPE,
        type: METHOD_PUT,
        data: JSON.stringify({name: teamName}),
        success: function (data) {
            console.log(data);
            $teamTable.bootstrapTable('refresh', {silent: true});
        },
        error: function(xhr, ajaxOptions, thrownError){
            console.log("failed");
        }
    });
    inputField.val('');
}

function updateTeam() {
    var teamUuid = $(this).data("team-uuid");
    var teamName = $('#inputTeamName-' + teamUuid).val();
    var isHakmaster = $('#inputTeamHakmaster-' + teamUuid).is(':checked');
    $.ajax({
        url: contextPath() + URL_TEAM,
        contentType: CONTENT_TYPE_JSON,
        dataType: DATA_TYPE,
        type: METHOD_POST,
        data: JSON.stringify({uuid: teamUuid, name: teamName, hakmaster: isHakmaster}),
        success: function (data) {
            $teamTable.bootstrapTable('refresh', {silent: true});
        },
        error: function(xhr, ajaxOptions, thrownError){
            console.log("failed");
        }
    });
}

function deleteTeam() {
    var teamUuid = $(this).data("team-uuid");
    $.ajax({
        url: contextPath() + URL_TEAM,
        contentType: CONTENT_TYPE_JSON,
        type: METHOD_DELETE,
        data: JSON.stringify({uuid: teamUuid}),
        success: function (data) {
            $teamTable.expanded = false;
            $teamTable.bootstrapTable('collapseAllRows');
            $teamTable.bootstrapTable('refresh', {silent: true});
        },
        error: function(xhr, ajaxOptions, thrownError){
            console.log("failed");
        }
    });
}

$(document).ready(function () {
    // Initialize all tooltips
    $('[data-toggle="tooltip"]').tooltip();

    // Listen for if teams should be created
    $('#createTeamCreateButton').on('click', createTeam);

    // When modal closes, clear out the input fields
    $('#modalCreateTeam').on('hidden.bs.modal', function () {
        $('#createTeamNameInput').val('');
    })
});

function deleteApiKey(apikey) {
    $.ajax({
        url: contextPath() + URL_TEAM + "/key/" + apikey,
        contentType: CONTENT_TYPE_JSON,
        type: METHOD_DELETE,
        success: function () {
            $('#container-apikey-' + apikey).remove();
            $teamTable.bootstrapTable('refresh', {silent: true});
        },
        error: function(xhr, ajaxOptions, thrownError){
            console.log("failed");
        }
    });
}

function regenerateApiKey(apikey) {
    $.ajax({
        url: contextPath() + URL_TEAM + "/key/" + apikey,
        contentType: CONTENT_TYPE_JSON,
        dataType: DATA_TYPE,
        type: METHOD_POST,
        success: function (data) {
            $('#apikey-' + apikey).html(data.key);
            $('#apikey-' + apikey).attr("id","apikey-" + data.key);
            $('#regen-' + apikey).attr("id","regen-" + data.key);
            $('#regen-' + data.key).attr("onclick","regenerateApiKey('" + data.key + "')");
            $('#delete-' + apikey).attr("id","delete-" + data.key);
            $('#delete-' + data.key).attr("onclick","deleteApiKey('" + data.key + "')");
            $teamTable.bootstrapTable('refresh', {silent: true});
        },
        error: function(xhr, ajaxOptions, thrownError){
            console.log("failed");
        }
    });
}

function addApiKey(uuid) {
    $.ajax({
        url: contextPath() + URL_TEAM + "/" + uuid + "/key",
        contentType: CONTENT_TYPE_JSON,
        dataType: DATA_TYPE,
        type: METHOD_PUT,
        success: function (data) {
            $teamTable.bootstrapTable('refresh', {silent: true});
        },
        error: function(xhr, ajaxOptions, thrownError){
            console.log("failed");
        }
    });
}

function removeTeamMembership(uuid, username) {
    alert("Removing membership of " + username + " from group: " + uuid);
}