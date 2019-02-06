# Jira Export

A simple framework to export Jira issues and work log.

```shell
java -jar jira-export-jar-with-dependencies.jar jira-instance-url project user password true/false
```

true/false flag is used to export work log or not

[Download](target/jira-export-jar-with-dependencies.jar)

Using this approach you will be able to export nearly all Jira data, you simply have to extend the used classes with attributes from Jira API documentation.

