FROM  azul/zulu-openjdk-alpine:11
RUN apk update
LABEL maintainer="jpoffy@ameritech.net"
RUN adduser -D -s /bin/bash -u 4004  hl7app
COPY entry.sh /

RUN chmod +x /entry.sh


COPY target/dependency/app.jar /app.jar
USER hl71pp
ENTRYPOINT [ "/entry.sh" ]

