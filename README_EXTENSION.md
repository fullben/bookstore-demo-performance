# MicroStream BookStore Performance Demo Extension

This project consists of an extended version of the performance demo implementation provided by the MicroStream developers. This implementation remains unchanged, except for an extension which provides access to the queries used for evaluating the performance by means of a REST API. A [JMeter](https://jmeter.apache.org/) script for automatically executing the queries has also been included.

## Structure

All modifications to the Spring Boot application are implemented in the `one.microstream.demo.bookstore.rest` package and subpackages thereof.
The directory `client-script` contains the relevant JMeter files. A `Dockerfile` for containerizing the application and a corresponding `docker-compose` file can be found in the root directory of the project.

## Setup and Usage

- First, you need to set up and run the application as instructed in the main `README` file. Note that you can define the amount of data to be used by the application by setting the `bookstoredemo.initialDataAmount` property in the `application.properties` file or adjusting the value of the matching environment variable in the `docker-compose.yml` file.
- Following this, you may launch JMeter and use the `client-script/client-script.jmx`

### JMeter Script

The script is meant for executing the 7 read-only queries defined by the application for a specific persistence implementation (either MicroStream-based or JPA-based) for a specified amount of time.
In order to achieve this, the script has various configuration properties, defined in the block *User Defined Variables*. You can set the values there or alternatively use the appropriate command-line arguments.

User Defined Variable|Command-Line Argument|Default Value|Description
---|---|---|---
`server.url`|`-Jurl`|`localhost`|The url of the targeted server, excluding port and protocol, e.g. `localhost` for a server running on the same machine.
`server.port`|`-Jport`|80|The port of the targeted server, usually 80.
`server.protocol`|`-Jprotocol`|http|The protocol of the targeted server, usually either `http` or `https`.
`query.mode`|`-Jmode`|ms|The persistence implementation to be used for the queries, `ms` for MicroStream, `jpa` for JPA.
`query.duration`|`-Jduration`|1800|The duration for which queries will be executed in seconds.
`query.clients`|`-Jclients`|10|The number of threads used for executing queries.

Open a terminal and execute the command `jmeter -Jsample_variables=rduration -n -t client-script.jmx -l results.jtl` to run the test in JMeter's CLI-mode. This will execute the testplan defined in `client-script.jmx`. The results will be written as CSV data to the `results.jtl` file.

Once the test has been completed, you can use the `jmeter -g results.jtl -o ./report` command to automatically create a report from the test results. The report will be placed in the `report` directory. Be aware that the `jmeter.reportgenerator.exporter.html.series_filter` property in the `user.properties` file defines which requests will be considered for the report.
