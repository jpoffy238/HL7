FROM  azul/zulu-openjdk-alpine:11
RUN apk update
LABEL maintainer="jpoffy@ameritech.net"
RUN adduser -D -s /bin/bash -u 40001  hl7acc
COPY entry.sh /

RUN chmod +x /entry.sh


COPY target/dependency/app.jar /app.jar
USER hl7acc
ENTRYPOINT [ "/entry.sh" ]

