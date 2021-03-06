# Web architecture

## Goals 
* Learn front-end technologies on web-development.
* Learn the cloud technologies.
* Investigate different databases, performance, scalability.

## Introduction

Web-architecture is application that allows to investigate and analyze behavior of different DBMS in different conditions. 
As example, you can compare spent time to create/delete some entity in SQL and NoSQL databases. Also you can investigate how database performance depends to volume of data.

The application allows to investigate two DBMS:
* Postgresql - one of most popular DBMS based on SQL tehnologies. 
* MongoDB - one of most popular DBMS based on NoSQL tehnologies.   
This list can be extended easily.   

The main functionality of the application is start the test and check reports.

## Data templates
The application fills up databases by data templates during the test. Data templates should be filled before start the test. User can see and manage it on "Data templtes" tab.
Template structure:
1. "Author" entity has "name" property only
2. "Data template" is a product of the author, has properties:
   * author - link to "Author" entity
   * name
   * text - minimum length of text is 200 characters.
   
## Test
User can start test in the "Tests" tab. The duration of tests is 1 minute. Client (web-browser) sends http requests to REST API of the server every 300 ms with template id. One request includes  following actions on server side:
* Create 3 copy of template in data collection.
* Update some random item from data collection.
* Delete some random item from data collection.
* Search items in data collection by indexed field.
* Search items in data collection by part of long text.  
* Retrieve full item data with related entities and collections.   

This actions is executed for all supported DBMS (currently is Postgresql and MongoDB) and measured what time server spend for each action. All measurements is saved in the test. Data base volume is fixed on each test starting. 
Only one test can be started simultaneously. Test is stoped automatically in one minute after starting. But the user can forcibly cancel this.

## Reports
User can investigate the statistics by completed tests using table data or charts.
#### Tests list 
User can see detailed data about any completed test. 
Descriptions of the table columns:
* Created - test creation and start date
* Requests - requests count that  was executed successfully
* Duration - test duration in seconds
* Database volume - records count of main collection of the database on the test starting (separate columns for each databases)
* Measurements - average time in milliseconds which server spended to execute some action, as example create, delete object, search, retrieve full data by object id. 

#### Charts
Charts allows visually analyze statistics by tests. There are separate charts for each measured action. Axis X is databases volume, axis Y is average time in milliseconds that server spended to execute the action.

