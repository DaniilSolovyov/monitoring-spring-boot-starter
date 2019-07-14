# Useful monitoring spring boot starter
Create table in your PostgreSQL
~~~~sql
CREATE TABLE METRIC
(
  ID              BIGSERIAL,
  EVENT_NAME      TEXT,
  VALUE           BIGINT,
  PARAMETERS      TEXT,
  EVENT_TIMESTAMP TIMESTAMP,
  PRIMARY KEY (ID)
);
~~~~
Provide three properties in application.properties file
~~~~properties
#queue size with metrics for batch processing
monitoring.queue-size=
#max size for batch
monitoring.batch-size=
#min size for batch
monitoring.batch-threshold=
~~~~
Add the JitPack repository to your build file
~~~~xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
~~~~
Add the dependency
~~~~xml
<dependency>
    <groupId>com.github.DaniilSolovyov</groupId>
    <artifactId>monitoring-spring-boot-starter</artifactId>
    <version>2.0.2.RELEASE</version>
</dependency>
~~~~
Place annotation on interested method or methods
~~~~java
@Monitoring("METHOD")
public void methodUnderMonitoring(String name, double value) {
    //sophisticated code goes here
}
~~~~
And enjoy!