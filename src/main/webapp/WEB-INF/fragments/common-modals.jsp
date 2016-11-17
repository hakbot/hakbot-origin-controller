<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                                <div><strong id="systemAppVersion"></strong> (built on: <span id="systemAppTimestamp"></span>)</div>
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
                        <img src="<c:url value="/images/hakbot-header-logo.png"/>"/> Hakbot Origin Controller
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
