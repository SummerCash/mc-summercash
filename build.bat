REM Build the Java code
mvn package

REM Install the plugin to the MC test server
rm -f server/plugins/mc-summercash-v0.1.jar
cp target/mc-summercash-v0.1.jar server/plugins/mc-summercash-v0.1.jar
echo "installed the latest build to the server!"
