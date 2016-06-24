<%@ page import="java.util.List" %>
<%@ page import="io.hakbot.controller.workers.ExpectedClassResolver" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%
    String path = request.getPathInfo();
    String pluginClass = StringUtils.stripStart(path, "/");

    ExpectedClassResolver resolver = new ExpectedClassResolver();
    List<Class> classes = resolver.getResolvedProviders();

    boolean resolved = false;
    for (Class clazz: classes) {
        if (clazz.getName().equals(pluginClass)) {
            resolved = true;
            String pluginPage = "/WEB-INF/plugins/" + pluginClass + "/index.jsp";
            %><jsp:include page="<%= pluginPage %>"/><%
            break;
        }
    }
    if (!resolved) {
        response.sendError(404);
    }
%>