# Rapid7 InsightAppSec Bamboo Plugin
The Rapid7 InsightAppSec Plugin for Atlassian Bamboo is a Java-based project that leverages the Rapid7 InsightAppSec
RESTful API to automate the scanning and gating of Dynamic Application Security Testing (DAST) as part of a Bamboo build
or release pipeline.  The project utilizes the [Atlassian SDK](https://developer.atlassian.com/server/framework/atlassian-sdk/downloads/) 
and steps for setting up your development environment can be found [below](#development-environment-setup).

More information about InsightAppSec can be found here: https://www.rapid7.com/products/insightappsec/

If you would like to start using the InsightAppSec Plugin for Atlassian Bamboo today, it can be found on the Atlassian
Marketplace: https://marketplace.atlassian.com/1221109

## GETTING STARTED

### Development Environment Setup
1. Install Oracle JDK 8 (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
2. Install Maven (http://maven.apache.org/download.cgi)
3. Install Atlassian SDK
   * Linux/Mac: https://developer.atlassian.com/docs/getting-started/set-up-the-atlassian-plugin-sdk-and-build-a-project/install-the-atlassian-sdk-on-a-linux-or-mac-system
   * Windows: https://developer.atlassian.com/docs/getting-started/set-up-the-atlassian-plugin-sdk-and-build-a-project/install-the-atlassian-sdk-on-a-windows-system
     * brew tap atlassian/tap
     * brew install atlassian/tap/atlassian-plugin-sdk
4. Configure IDE (IntelliJ below) to use Atlassian SDK
   * Navigate to: Preferences -> Build, Execution, Deployment -> Build Tools -> Maven
     * (example) Maven home directory: /usr/local/Cellar/atlassian-plugin-sdk/6.3.10/libexec/apache-maven-3.2.1
     * (example) Local repository: /usr/local/Cellar/atlassian-plugin-sdk/6.3.10/libexec/repository

### Deploying Plugin in Development Environment
The Atlassian SDK provides the ability to compile the latest plugin changes and deploy it in a running version of 
Bamboo.  Ensure your development environment is set up and then run: 
```bash
# First clean the project
> atlas-clean  # Always clean to remove files from previous build-time (runs mvn clean)

# Run embedded Bamboo server with plugin generated
> atlas-run
```

Once the servlet has started up, Bamboo should be reachable locally at `http://localhost:6990/bamboo/`.  The default 
username is `admin` and password is `admin`.

In order to reload plugin changes within a locally running instance of Bamboo, the Atlassian SDK can also be used:
```bash
> atlas-package
> atlas-install-plugin
```

### Useful Atlassian SDK Commands
Here are the SDK commands every project can benefit from:
* atlas-run   -- installs this plugin into the product and starts it on localhost
* atlas-debug -- same as atlas-run, but allows a debugger to attach at port 5005
* atlas-cli   -- use after already running atlas-run or atlas-debug, run in another command line window in the same project directory:
                 - 'pi' reinstalls the plugin into the running product instance
* atlas-help  -- prints description for all commands in the SDK

Full documentation is available at: https://developer.atlassian.com/display/DOCS/Introduction+to+the+Atlassian+Plugin+SDK

## PACKAGING

### Packaging Plugin for Testing
In addition to running the plugin in a local Bamboo instance, it is possible to build the plugin and then upload it to
an instance of Bamboo for continued testing by others.
```bash
# First clean the project
> atlas-clean

# Then package the plugin so it can be installed manually
> atlas-package
```

Once the plugin has been created, the JAR can usually be located at `target/insightappsec-bamboo-plugin-\<version\>.jar`.
This JAR can be used to upload to an instance of Atlassian Bamboo in order to do further testing.

#### Install Plugin on Standalone Bamboo instance
1. Navigate to `Bamboo Administration` -> `Add-ons`
2. Select `Upload add-on`
3. Choose the jar file generated from the [Packaging Plugin for Testing](#packaging-plugin-for-testing) section above and select `Upload`
4. Once completed, `Rapid7 InsightAppSec Bamboo Plugin` will show as a User-installed add-on

### Packaging Plugin for Release
When releasing the plugin, the `atlas-release` SDK task will compile, increment the release version, and push the 
release commits to this GitHub repository.  The generated JAR can then be uploaded to the Atlassian Marketplace to be 
published for others to use:
```bash
> atlas-clean
> atlas-release
```

### Publish to Marketplace
Publishing the plugin to the Atlassian Marketplace is a manual effort.  Retrieve the packaged JAR from the steps 
followed during [packaging](#packaging-plugin-for-release) and upload it to your 
[Atlassian publisher account](https://marketplace.atlassian.com/manage/apps).  As noted above, this plugin has already
been listed by Rapid7 and can be found [here](https://marketplace.atlassian.com/1221109).

## CONFIGURATION
The following details can be used in configuring the plugin within a project's build or release pipeline.  Once the 
plugin has been installed - or running in a local environment with `atlas-run` - the task can be implemented in any
number of build or release jobs. 

#### Shared Credential
Before configuring a shared credential, first generate an Insight platform API key. This API key is used to authorize 
the Bamboo Plugin to interact with the InsightAppSec API. Steps for creating an organization or user API key can be 
found [here](https://insight.help.rapid7.com/docs/managing-platform-api-keys#section-generating-an-organization-key).

Once an API key has been generated, a Shared Credential in Bamboo can be created.  Bamboo Shared Credentials are used 
for storing and accessing InsightAppSec API keys needed for interacting with the RESTful API.  The below steps outline 
how to set up a shared credential.

1. Navigate to `Bamboo Administration` -> `Overview`
2. On the left hand side, select `Shared credentials`
3. Select `Username and password` from the `Add new credentials` in the top right
4. Provide the following details:
   * Credential Name: MUST begin with `Rapid7` followed by any other naming details
   * Username: name of API key user or organization (this field is NOT used but is required)
   * Password: provide generated API key
5. Select `Save credentials`

#### Task Configuration
1. Select or create a Build Plan (Build and Deployment plans supported)
2. Navigate to `Actions` -> `Configure plan`
3. Select stage in scope (eg Default Stage)
4. Select `Add task` and choose `Rapid7 InsightAppsec Scan`
5. Provide appropriate connection and scan details for task; each setting detailed below:

| Field                             | Description                                                                                                                                                                   | Required |
|-----------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------|
| Task Description                  | The description of the task as it will appear in the plan.                                                                                                                    | false    |
| InsightAppSec Region              | Dropdown of InsightAppSec regions for connecting to the API.                                                                                                                  | true     |
| InsightAppSec API Key             | A drop-down menu to select the Rapid7 API Key to be used.                                                                                                                     | true     |
| App Name                          | A text field to input the InsightAppSec application name that will be utilized in the scan.                                                                                   | true     |
| Scan Config name                  | A text field to input the InsightAppSec scan configuration that will be utilized in the scan.                                                                                 | true     |
| Advance task when scan has been   | A drop-down menu to dictate when to move on from task; options: COMPLETED, STARTED, SUBMITTED.                                                                                | true     |
| Status Check Interval             | The frequency (in minutes) that the scan’s status will be checked. Dependent on task advancement set to `COMPLETED` or `STARTED`.                                             | false    |
| Max Scan Pending Duration         | The time (in minutes) to wait for the scan to be started. Task will be marked a failure if pending duration is reached.                                                       | false    |
| Max Scan Execution Duration       | The time (in minutes) to wait for the scan to be completed. Task will be marked a failure if max execution duration is reached.                                               | false    |
| Findings Report Generation        | Option used to determine whether a raw JSON findings report will be generated and added as an artifact for the build.                                                         | false    |
| Vulnerabilities Query Enforcement | Option used to determine whether the build will fail if the provided query returns results.                                                                                   | false    |
| Vulnerability Query               | The query executed against the completed scan's findings to retrieve any matching vulnerabilities. Dependent on the option `Vulnerabilities Query Enforcement` being checked. | false    |
| Proxy Host                        | An optional text field to input the proxy host address if required.                                                                                                           | false    |
| Proxy Port                        | An optional text field to input the proxy port required if required.                                                                                                          | false    |
| Server Log Debugging              | Option used to determine whether the server will output debugging results. Includes API key in output.                                                                        | false    |