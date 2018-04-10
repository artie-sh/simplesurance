## Test execution
Tests are run by Maven with the following system properties passed as environment parameters:
- **-DuserName**, **-DrepoName** and **-DpullRequestNumber** - repository related information used to build the target pull request url;
- **-DaccessToken** - GitHub authentication token. In case the repo under test is public, this variable can be omitted.

Example of a valid test launch:
- mvn clean test -DuserName=${userName} -DrepoName=${repoName} -DpullRequestNumber=${pullRequestNumber} -DaccessToken=${access_token}