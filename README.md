# MeetingRoomReservation
A.Installation guide

Softwares to be installed:
1.JDK 1.8
2.Maven 3.6.3 - can be downloaded from https://maven.apache.org/download.cgi
3.MySQL 8.0.19 or above - can be downloaded from https://www.mysql.com/downloads/
----------------------------------------------------------
B.steps for build & execution for all APIs

1.Build the source-code using maven:
command: <source code directory where pom.xml is available>mvn clean packagemvn clean package

2.DB creation  : create the database meeting.
SQL command : create database meeting;

3.execute jar file:
java -jar target\meeting-0.0.1-SNAPSHOT.jar
----------------------------------------------------
C.List of all assumptions
1. We have assumed there is one company with multiple buildings.
2.Recurring meeting is not supported through this app.
3.MySql port 3306 is open andthe same has to be used for this app.
4. This application runs on inbuilt Tomcat hence port 8080 shouldbe open.
5.Username and password for MySQL is default (root/root)and can be modified in application.properties which resides in 
<source code directory>\src\main\resources\application.properties.
6.A meeting can be cancelled by anyone.Going forward userId can be used to restrict this behavior.
7.StartTime an Endtime has to be given properly (in proper format as mentioned in the api guide and starttime should always be earlier than end time)by the user.Currently the validation of these fields is not implemented.
