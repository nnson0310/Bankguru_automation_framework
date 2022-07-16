# Bankguru Selenium Java Automation Framework
### This is a skeleton project for learning building Selenium 3 + TestNG automation framework from scratch.
````shell
Important Note: 
+ Language binding is java. Running stably with java version 11 (jdk 11.0.3)
+ This project does not using latest selenium version. Using selenium version is 3.141.59 (7/3/2022: latest stable version is 4.0.3)
+ Public domain using for test: https://demo.guru99.com
+ Using maven build tools to manage dependencies and third-party libraries
+ Using TestNG as test automation framework (annotation, testRunner...)
`````

### Features
+  Support demo running through local, selenium grid and cloud testing with proper configuration
+  Support cross browser testing: chrome, firefox, headless browser, opera, edge, safari....
+  Auto retry when testcases fail, console logging, generating report (testNG report) with attached screenshot and so on...

### Install
**Running by maven command line (recommend):**
```sh
mvn run test 
mvn run test -Dserver=serverName
```
**Running with batch file**
Click and runbatch file with server name

#### Local running:
Clone project and run through maven command line (or running with TestNG plugins).
**Support server name** is: dev, testing, staging, product. Each server name is corresponding to each below domain:
- dev: https://demo.guru99.com/v1
- testing: https://demo.guru99.com/v2
- staging: https://demo.guru99.com/v3
- product: https://demo.guru99.com/v4

### Cloud running:
Only support running on https://saucelabs.com/. You can configure other platform (ex: Browser Stack...) by setting credentials and url in GlobalConstants class. Creating in test/resources "cloud_credentials.properties" with content as below:
```sh
cloudUsername=your_cloud_username
cloudPassword=your_cloud_password
```
### Grid Running:
Support both real devices or docker grid selenium (recommend). Install docker desktop and running below command:
```sh
cd project_location
docker compose up -d 
```




