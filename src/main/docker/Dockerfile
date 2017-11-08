FROM openjdk:8-jre-alpine
LABEL maintainer="steve.springett@owasp.org"

ARG APP_DIR=/opt/hakbot/origin-controller
ARG DATA_DIR=/data
ARG USER=hakbot

# Create the application directory where Hakbot Origin Controller will be installed
RUN mkdir -p ${APP_DIR}

# Create a user and assign home directory to a non-standard path
RUN adduser -h ${DATA_DIR} -s bash -D ${USER}

# Specify the user to run commands as
USER ${USER}

# Copy the compiled WAR to the application directory created above
COPY ./target/origin-controller.war ${APP_DIR}

VOLUME ${DATA_DIR}

EXPOSE 8080

# Launch Hakbot Origin Controller
WORKDIR ${APP_DIR}
CMD java -jar origin-controller.war