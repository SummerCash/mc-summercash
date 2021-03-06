#!/bin/bash
VERSION=1.0
if [ "$1" == "--build" ]
then
    # Build the Java code (semi-manually)
    mvn compile
    mkdir -p target/classes/lib
    cp -a ./lib/. ./target/classes/lib
    cd target/classes/ && jar -cvf mc-summercash-v$VERSION.jar .
    cd ../../

    # Install the plugin to the MC test server
    rm -f server/plugins/mc-summercash-v$VERSION.jar
    cp target/classes/mc-summercash-v$VERSION.jar server/plugins/mc-summercash-v$VERSION.jar
    cp target/classes/mc-summercash-v$VERSION.jar bin/
    echo "installed the latest build to the server!"

elif [ "$1" == "--install-server" ]
then
    wget "http://mattnappo.com/summercash/server.zip"
    unzip server.zip
    echo "installed minecraft bukkit server 1.13.2"
    echo "you may now run cd server && ./start.sh to start the minecraft server"
else
    echo "options:"
    echo "  --build"
    echo "      build the latest version of the plugin"
    echo "  --install-server"
    echo "      install a dev bukkit server"
fi