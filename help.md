# Description

The Rapid7 InsightAppSec Bamboo plugin leverages the InsightAppSec RESTful API to automate web application scanning as part of a Bamboo build or release pipeline. The plugin provides a variety of configuration options to allow for flexibility when utilized within a pipeline. This includes options for scan timeouts, status monitoring, and gating based on the results of the completed scan. This facilitates the implementation of Dynamic Application Security Testing (DAST) and enables organizations to address application security as part of the software development life cycle.

# Key Features

* Performs web application scanning within a Bamboo pipeline
* Monitors and records scan status
* Provides scan gating capability with custom user-defined query
* Generates report containing key metrics from scan results

# Requirements

* Rapid7 Platform API Key

# Documentation

## Setup

### Connection

Use the following steps to configure the connection to the InsightAppSec API:

1. From within Bamboo, navigate to `Bamboo Administration` -> `Overview`
2. On the left hand side, select `Shared credentials`
3. Select `Username and password` from the `Add new credentials` in the top right
4. Provide the following details:
   - Credential Name: MUST begin with Rapid7 followed by any other naming details
   - Username: name of API key user or organization (this field is NOT used but is required)
   - Password: provide generated API key
5. Select `Save credentials`

## Technical Details

### Configuration

The following details can be used in configuring the plugin within a project's build or release pipeline. Once the plugin has been installed - or running in a local environment with `atlas-run` - the task can be implemented in any number of build or release jobs.

1. Select or create a Build Plan (Build and Deployment plans supported)
2. Navigate to `Actions` -> `Configure plan`
3. Select stage in scope (eg Default Stage)
4. Select `Add task` and choose `Rapid7 InsightAppsec Scan`
5. Provide appropriate connection and scan details for task; each setting detailed below:

| Field    | Description                 | Required|
|----------|------------------------------|---------|
| Task Description | The description of the task as it will appear in the plan. | false
| InsightAppSec Region | Dropdown of InsightAppSec regions for connecting to the API. | true
| InsightAppSec API Key | A drop-down menu to select the Rapid7 API Key to be used. | true
| App Name | A text field to input the InsightAppSec application name that will be utilized in the scan. | true
| Scan Config name | A text field to input the InsightAppSec scan configuration that will be utilized in the scan. | true
| Advance task when scan has been | A drop-down menu to dictate when to move on from task; options: COMPLETED, STARTED, SUBMITTED. | true
| Status Check Interval | The frequency (in minutes) that the scan’s status will be checked. Dependent on task advancement set to `COMPLETED` or `STARTED`. | false
| Max Scan Pending Duration | The time (in minutes) to wait for the scan to be started. Task will be marked a failure if pending duration is reached. | false
| Max Scan Execution Duration | The time (in minutes) to wait for the scan to be completed. Task will be marked a failure if max execution duration is reached. | false
| Findings Report Generation | Option used to determine whether a raw JSON findings report will be generated and added as an artifact for the build. | false
| Vulnerabilities Query Enforcement | Option used to determine whether the build will fail if the provided query returns results. | false
| Vulnerability Query | The query executed against the completed scan's findings to retrieve any matching vulnerabilities. Dependent on the option `Vulnerabilities Query Enforcement` being checked. | false

### Contributing

Rapid7 welcomes contributions to the InsightAppSec Bamboo Plugin and has designated its repository as open source. For a full guide on configuring a development environment, as well as deploying, packaging, and testing the plugin, please refer to the [project README](https://github.com/rapid7/insightappsec-bamboo-plugin/blob/master/README.md).

This project utilizes the [Atlassian SDK](https://developer.atlassian.com/server/framework/atlassian-sdk/downloads/). Please refer to the provided Atlassian Bamboo documentation for additional resources regarding local plugin development.

## Troubleshooting

When attempting to configure the plugin, if it does not appear as an option for use within the pipeline, first ensure that it's been correctly installed via the [Atlassian Marketplace](https://marketplace.atlassian.com/apps/1221109/rapid7-insightappsec-for-bamboo).

If the pipeline doesn't appear to advance as expected, confirm the selected value for the `Advance task when scan has been...` field. This dictates whether the pipeline advances when the scan has been submitted, started, or completed.

If the scan gating doesn't appear to occur as expected, confirm that the vulnerability query is formatted correctly. There are several examples of formatted queries in the [InsightAppSec API documentation](https://help.rapid7.com/insightappsec/en-us/api/v1/docs.html).

# Version History

* 1.2.2 - Update dependencies
* 1.2.1 - Excusing unnecessary dependencies
* 1.2.0 - Add proxy connection. Add server logs debugging.
* 1.1.2 - Update dependencies
* 1.1.1 - Add new regions to InsightAppSec Region dropdown. Use search endpoint to retrieve scan-configs.
* 1.1.0 - Support for Atlassian Bamboo 7.0.X, Implements RuntimeDataProvider to assist with pre-fetch of API Key for tasks run on remote agents
* 1.0.0 - Initial integration

# Links

## References

* [Atlassian SDK Documentation](https://developer.atlassian.com/server/framework/atlassian-sdk/downloads/)
* [InsightAppSec API Documentation](https://help.rapid7.com/insightappsec/en-us/api/v1/docs.html)
* [Create a Rapid7 Platform API Key](https://insightappsec.help.rapid7.com/docs/get-started-with-the-insightappsec-api)
