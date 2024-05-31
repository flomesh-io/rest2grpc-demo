#!/bin/bash
set -e

if [[ "$1" == "docker-run" ]]; then
  if [[ "$2" == "client" ]]; then
    shift 2
    # workaround for https://github.com/moby/moby/issues/31243
    chmod o+w /proc/self/fd/1 || true
    chmod o+w /proc/self/fd/2 || true

    exec java -javaagent:/opentelemetry-javaagent.jar -Dotel.resource.attributes=service.name=rest2grpc-client -Dserver.port=${CLIENT_LISTEN_PORT} -Dgrpc.client.rest2grpc-server.address=${REST2GRPC_BACKEND} -jar /client-0.0.2.jar
  fi
fi

exec "$@"
