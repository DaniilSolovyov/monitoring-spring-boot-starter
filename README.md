# Useful monitoring spring boot starter
Create two tables in your PostgreSQL
~~~~sql
CREATE TABLE EVENT
(
  ID   SERIAL,
  NAME VARCHAR(128) UNIQUE NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE METRIC
(
  ID              SERIAL,
  EVENT_ID        INT,
  VALUE           BIGINT,
  PARAMETERS      VARCHAR(1024),
  EVENT_TIMESTAMP TIMESTAMP,
  PRIMARY KEY (ID),
  FOREIGN KEY (EVENT_ID) REFERENCES EVENT (ID)
);
~~~~
Provide three properties in application.properties file
~~~~properties
monitoring.database.url=
monitoring.database.username=
monitoring.database.password=
~~~~
Place annotation on interested method or methods
~~~~java
@Monitoring("METHOD")
public void methodUnderMonitoring(String name, double value) {
    //sophisticated code goes here
}
~~~~
And enjoy!