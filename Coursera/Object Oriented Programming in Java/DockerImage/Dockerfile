#Linux image to use
FROM alpine
WORKDIR /root/coursera
COPY UCSDUnfoldingMaps/ /root/coursera

# Install JDK
RUN apk add openjdk8
ENV JAVA_HOME /usr/lib/jvm/java-1.8-openjdk
ENV PATH $PATH:$JAVA_HOME/bin