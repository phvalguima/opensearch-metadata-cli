# OpenSearch Metadata CLI

Manage the node's metadata using CLI commands.

**WARNING**: BEWARE tinkering with opensearch metadata may cause *irreversible data loss*!

## Building

```
./gradlew clean build
```

If needed, clean the previous cached dependencies:
```
./gradlew clean build --refresh-dependencies
```

## Executing

The first step is to make sure you have access to the jar files from opensearch. One option is to download the `opensearch snap`.

First, power down the opensearch service running in your machine. One example of potential command:

```
sudo java \
  -Dopensearch.path.home="$OPENSEARCH_HOME" \
  -Dopensearch.path.conf="$OPENSEARCH_PATH_CONF" \
  -cp /snap/opensearch/current/usr/share/opensearch/lib/*:app/build/libs/app.jar \
    org.opensearch.cli.main.Node list-metadata
```

OBS.: `sudo` may be needed as the folders will not be accessible to your current user.

### Debugging

To debug using the `opensearch snap` installed:

```
sudo java \
    -agentlib:jdwp=transport=dt_socket,server=y,suspend=y \
    -Dopensearch.path.home="/var/snap/opensearch/current/usr/share/opensearch" \
    -Dopensearch.path.conf="/var/snap/opensearch/current/etc/opensearch" \
    -Dopensearch.path.data="/var/snap/opensearch/common/var/lib/opensearch" \
    -cp /snap/opensearch/current/usr/share/opensearch/lib/*:app/build/libs/app.jar \
    org.opensearch.cli.main.Node list-metadata
```

Then, connect to the debug server with JDB.
