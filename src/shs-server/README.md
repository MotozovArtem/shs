# SHS (Smart Home System)
## Server side
Language: Java
Database: SQLite3

Protocol buffers (shs-api) compile:
protoc --go_out=paths=source_relative:

## Smart Home System Head Controller
### How to build:
In project root:
`mvn clean package -P dev -DskipTests -Dfile.encoding=UTF-8`