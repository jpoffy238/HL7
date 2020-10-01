FROM  azul/zulu-openjdk-alpine:11
RUN apk update
LABEL maintainer="jpoffy@ameritech.net"


RUN adduser -u 40003 -D -s /bin/bash hl7acc
COPY entry.sh /

RUN chmod +x /entry.sh

USER hl7acc
COPY target/dependency/app.jar /app.jar
ENTRYPOINT [ "/entry.sh" ]

