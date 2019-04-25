# Jira Export

A simple framework to export Jira issues and work log.

###USAGE:
<jira-instance-url> <project> <user> <password> <worklog: true/false> <changelog: true/false>

```shell
java -jar jira-export-jar-with-dependencies.jar jira-instance-url project user password true/false true/false
```

first true/false flag is used to export work log or not
second true/false flag is used to export change log or not

[Download](target/jira-export-jar-with-dependencies.jar)

Using this approach you will be able to export nearly all Jira data, you simply have to extend the used classes with attributes from Jira API documentation.


[https://medium.com/@robertdiers/the-coffee-break-statement-using-the-jira-api-76007e0713e0](https://medium.com/@robertdiers/the-coffee-break-statement-using-the-jira-api-76007e0713e0)

