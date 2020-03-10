FROM  azul/zulu-openjdk-alpine:11
RUN apk update
LABEL maintainer="jpoffy@ameritech.net"

COPY target/Audit-0.0.3-SNAPSHOT.jar /opt/Audit.jar
RUN adduser -u 40003 -D -s /bin/bash hl7acc
COPY entry.sh /

RUN chmod +x /entry.sh
RUN chmod +r /opt/Audit.jar

USER hl7acc

ENTRYPOINT [ "/entry.sh" ]

