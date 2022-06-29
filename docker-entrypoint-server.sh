#!/bin/bash
set -e

if [[ "$1" == "docker-run" ]]; then
  if [[ "$2" == "server" ]]; then
    shift 2
    # workaround for https://github.com/moby/moby/issues/31243
    chmod o+w /proc/self/fd/1 || true
    chmod o+w /proc/self/fd/2 || true

    exec java -jar -Dgrpc.server.port=${SERVER_LISTEN_PORT} /server-0.0.2.jar
  fi
fi

exec "$@"
