[![Build Status](https://travis-ci.org/stevespringett/restjob-controller.svg?branch=master)](https://travis-ci.org/stevespringett/restjob-controller)

RESTjob Controller
=====================================

A minimalistic Java Web Application (Servlet) that provides the queuing and processing of jobs over JSON.

This project is under heavy development and may be unstable. Use at your own risk.

Background
-------------------

In software development, it is essential that security tools are integrated into every step of the
development process. Sometimes, custom tools and scripts may be necessary to integrate with continuous
integration servers, the security tools, and the target application being tested.

This is why the RESTjob Controller came about. The controller accepts incoming jobs over JSON/HTTP,
places the jobs into a queue for processing, and when worker threads are available, the jobs will
be processed.

Jobs can be anything ranging from executing shell scripts to controller dynamic analysis engines.

The RESTjob Controller is not designed to be a security application. It's designed for integrating into
the build process via continuous integration. Basic security features are implemented, but best-practices
should be employed.

Features
-------------------

* Configurable queue and job engine
* RESTful endpoints that respond with JSON
* Swagger2 support
* Embedded database engine (H2)
* Requires Java 8 or higher


Compiling
-------------------

> $ mvn clean package

Installing
-------------------

The RESTjob Controller can be deployed to any Servlet 3 compatible container including Tomcat and Jetty.
Simply copy restjob-controller.war to the webapps directory and restart the servlet engine.

Usage
-------------------

The URL for the API is: http://$HOSTNAME:$PORT/$CONTEXT/api

Swagger JSON is located: http://$HOSTNAME:$PORT/$CONTEXT/api/swagger.json

Copyright & License
-------------------

RESTjob Controller is Copyright (c) Steve Springett. All Rights Reserved.

Permission to modify and redistribute is granted under the terms of the [GPL 3.0] license.

  [GPL 3.0]: http://www.gnu.org/licenses/gpl-3.0.txt
