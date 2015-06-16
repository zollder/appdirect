#### AppDirect coding challenge by Eugene Simon.

Simple user CRUD web application built using Gradle - Spring - SpringData(JPA) - MySQL stack.

#### Instructions:
- Deploy database schema located in the src/main/resources/scripts.
- Modify database credentials in the src/main/resources/props/database.properties
- Run ./gradlew war or ./gradlew jar to generate a war or jar respectively.
- or Run ./gradlew tomcatRunWar to build and run the application using embedded tomcat instance.
- or Run ./gradlew tasks locally to see all available options.

#### Notes:
- the integration was not complete - no working endpoints are available at the moment
- the UI is missing
- unit/integration tests are missing
- all working application endpoints (User & Company CRUD) were tested with SoapUI
- web version is not available yet
