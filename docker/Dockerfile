FROM alpine:edge
MAINTAINER timsnky@gmail.com
RUN apk add --no-cache openjdk8
COPY files/safaricommoviesapp.p12 /
COPY files/movies-0.0.1-SNAPSHOT.jar /opt/safaricom-movies/lib/
ENTRYPOINT  ["/usr/bin/java"]
CMD ["-jar", "/opt/safaricom-movies/lib/movies-0.0.1-SNAPSHOT.jar"]
VOLUME /var/lib/safaricom-movies/config-repo
EXPOSE 8080 8443


