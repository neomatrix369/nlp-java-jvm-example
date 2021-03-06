FROM valohai/jupyhai as base-image

### Install JDK 11 from AdoptOpenJDK images

COPY --from=adoptopenjdk/openjdk11 /opt/java /opt/java

### Install GraalVM for Java 11

COPY --from=oracle/graalvm-ce:19.3.0-java11 /opt/graalvm-ce-java11-19.3.0 /opt/java/graalvm

### Install curl needed for rest of the tasks

USER root
RUN apt-get update && apt-get install -qy curl

ARG WORKDIR
WORKDIR ${WORKDIR}

### Java setup

ARG JAVA_11_HOME

ARG GRAALVM_HOME
ENV GRAALVM_HOME=${GRAALVM_HOME}
ENV JAVA_HOME=${GRAALVM_HOME}
ENV PATH=${JAVA_HOME}/bin:${PATH}

### Test Java (Traditional and GraalVM)

RUN ${JAVA_11_HOME}/bin/java -version

RUN ${GRAALVM_HOME}/bin/java -version

### Install packages

RUN apt-get update && apt-get install -qy unzip git vim

RUN unzip -version
RUN git --version
RUN vim --version

### Install the Jupyter Java Kernel

COPY install-java-kernel.sh install-java-kernel.sh
RUN ./install-java-kernel.sh --buildJar
RUN ./install-java-kernel.sh --installJar

### Setup user
COPY .bashrc .bashrc_custom
RUN cat .bashrc_custom >> /home/jovyan/.bashrc
RUN rm .bashrc_custom