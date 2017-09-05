#!/bin/bash
set -e
thisscript=$(readlink -m $0)
thisdir=$(dirname $thisscript)
MVN_DIST_VER=3.5.0
TMP_DIR="$thisdir/.mvnw"
if [ ! -d "$TMP_DIR" ]; then
    mkdir -p "$TMP_DIR"
    pushd $TMP_DIR
    wget "https://repo1.maven.org/maven2/org/apache/maven/apache-maven/$MVN_DIST_VER/apache-maven-$MVN_DIST_VER-bin.tar.gz" -O - | tar -zx
    popd
fi

MVN_PATH=$TMP_DIR/apache-maven-3.5.0/bin
export PATH=$PATH:$MVN_PATH
cd $thisdir && "$MVN_PATH/mvn" $@

