FROM ubuntu:lunar

WORKDIR /java
RUN apt update
RUN apt install wget -y


RUN wget https://download.oracle.com/java/19/latest/jdk-19_linux-x64_bin.deb
RUN apt install ./jdk-19_linux-x64_bin.deb -y
RUN update-alternatives --install /usr/bin/java java /usr/lib/jvm/jdk-19/bin/java 1
RUN update-alternatives --install /usr/bin/javac javac /usr/lib/jvm/jdk-19/bin/javac 1
RUN update-alternatives --install /usr/bin/jar jar /usr/lib/jvm/jdk-19/bin/jar 1

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]
