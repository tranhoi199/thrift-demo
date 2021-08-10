thrift --gen java songservice.thrift
cp -rf ./gen-java/* ./src/main/java/
rm -rf ./gen-java/