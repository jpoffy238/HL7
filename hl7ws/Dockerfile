FROM  azul/zulu-openjdk-alpine:11
RUN apk update
LABEL maintainer="jpoffy@ameritech.net"
RUN chmod 777 /tmp

RUN adduser -u 40001 -D -s /bin/bash hl7acc

COPY entry.sh /
RUN chmod +x /entry.sh
COPY target/dependency/app.jar /app.jar
USER hl7acc
ENTRYPOINT [ "/entry.sh" ]

