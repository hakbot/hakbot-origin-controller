<%@ page import="java.util.List" %>
<%@ page import="io.hakbot.controller.workers.ExpectedClassResolver" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="io.hakbot.util.UuidUtil" %>
<%
    String pathInfo = request.getPathInfo();

    // Check to make sure path info was specified
    if (StringUtils.isEmpty(pathInfo)) {
        response.sendError(400);
        return;
    } else {
        // Path info was specified so strip off the leading / character
        pathInfo = StringUtils.stripStart(pathInfo, "/");
    }

    // Check to make sure resulting path info has a / character in it so we can split it
    if (!pathInfo.contains("/")) {
        response.sendError(400);
        return;
    }

    // Splits the pathinfo
    String[] paths = pathInfo.split("/");

    // Check to make sure at least 2 strings result from the split. The first being the
    // classname of the plugin, the second being the uuid of the job.
    if (paths.length < 2) {
        response.sendError(400);
        return;
    }

    // Assign the path strings
    String pluginClass = paths[0];
    String uuid = paths[1];

    // Check to make sure the uuid is a valid format
    if (!UuidUtil.isValidUUID(uuid)) {
        response.sendError(400);
        return;
    }

    ExpectedClassResolver resolver = new ExpectedClassResolver();
    List<Class> classes = resolver.getResolvedProviders();

    boolean resolved = false;
    for (Class clazz: classes) {
        if (clazz.getName().equals(pluginClass)) {
            resolved = true;
            String pluginPage = "/WEB-INF/plugins/" + pluginClass + "/index.jsp";
            %>
                <jsp:include page="<%= pluginPage %>">
                    <jsp:param name="uuid" value="<%= uuid %>"/>
                </jsp:include>
            <%
            break;
        }
    }
    if (!resolved) {
        response.sendError(404);
    }
%>