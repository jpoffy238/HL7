FROM  azul/zulu-openjdk-alpine:11
RUN apk update
LABEL maintainer="jpoffy@ameritech.net"
RUN adduser -D -s -U 4004 /bin/bash hl7app
COPY entry.sh /

RUN chmod +x /entry.sh


USER hl7app
COPY target/dependency/app.jar /app.jar
ENTRYPOINT [ "/entry.sh" ]

