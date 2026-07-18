# Selenium Practice Web App

A small React application with a Java Selenium/TestNG automation framework and a GitHub Actions pipeline that validates the UI and publishes a production Docker image.

This project is designed as a portfolio sample for SDET, QA automation, and test automation engineer roles. The application is intentionally compact, but it includes realistic automation concerns: authenticated user flows, role-based navigation, form controls, browser alerts, dropdowns, reusable Page Objects, headless browser execution, failure screenshots, CI test execution, and Docker image publishing.

## Application Overview

The application under `react-app/` is a React 18 single-page app named `selenium-practice`.

It provides:

- A login screen with two valid users:
  - `admin` / `admin123`
  - `tester` / `test123`
- A form page with:
  - Browser alert toggle
  - Priority radio buttons
  - Interest checkboxes
  - Country dropdown
  - Submission success message
- A table page with paginated sample user data
- An admin page that is available only to the `admin` user
- Stable `data-testid` selectors for automation-friendly UI targeting

The app gives the Selenium framework several UI patterns to exercise without needing a backend service.

## Repository Structure

```text
.
|-- .github/workflows/selenium.yml   # CI/CD workflow
|-- react-app/                       # React application and Dockerfile
|   |-- Dockerfile
|   |-- package.json
|   `-- src/
`-- selenium-tests/                  # Java Selenium TestNG framework
    |-- pom.xml
    |-- testng.xml
    |-- resources/screenshots/
    `-- src/
        |-- main/java/com/example/
        |   |-- base/
        |   |-- pages/
        |   `-- utils/
        `-- test/java/com/example/tests/
```

## Prerequisites

- Node.js 24, matching the GitHub Actions workflow
- npm
- Java 21 for CI parity, although the Maven compiler target is Java 17
- Maven
- Google Chrome
- Docker, if building or running the container image locally

## Run the Application Locally

From the React app directory:

```powershell
cd react-app
npm ci
npm start
```

The app runs at:

```text
http://localhost:3000
```

## Run the Selenium Tests Locally

Start the React app first, then run the TestNG suite from a second terminal:

```powershell
cd selenium-tests
mvn clean test
```

By default, the tests target:

```text
http://localhost:3000
```

To run against a different deployed environment:

```powershell
cd selenium-tests
mvn clean test -DbaseUrl=https://your-environment.example.com
```

## Test Framework

The automation framework is located in `selenium-tests/` and uses:

- Java
- Selenium WebDriver `4.32.0`
- TestNG `7.10.2`
- Maven Surefire `3.2.5`
- ChromeDriver through Selenium Manager
- Headless Chrome for local and CI execution

### Framework Design

The suite follows the Page Object Model:

- `BasePage` wraps common WebDriver interactions such as find, click, set, scroll, and explicit waits.
- `BaseTest` owns browser lifecycle, application loading, shared page setup, and failure screenshot handling.
- Page classes in `src/main/java/com/example/pages/` model the app screens:
  - `LoginPage`
  - `FormPage`
  - `TablePage`
  - `AdminPage`
- Utility classes in `src/main/java/com/example/utils/` provide reusable dropdown, alert, and driver helper methods.
- Test classes in `src/test/java/com/example/tests/` are grouped by feature area.

### Test Coverage

The TestNG suite covers:

- Valid admin login
- Valid tester login
- Invalid login error handling
- Role-based admin navigation
- Navigation between form, table, admin, and login pages
- Form submission success messaging
- Browser alert text and acceptance
- Checkbox selection behavior
- Radio button selection behavior
- Country dropdown selection
- Failure screenshot capture

The suite is configured through:

```text
selenium-tests/testng.xml
```

Maven Surefire runs that suite during:

```text
mvn clean test
```

### Screenshots and Reports

On test failure, `BaseTest` captures a PNG screenshot under:

```text
selenium-tests/resources/screenshots/
```

Surefire and TestNG reports are generated under:

```text
selenium-tests/target/surefire-reports/
```

These generated outputs are useful for debugging local and CI failures.

## CI/CD Pipeline

The GitHub Actions workflow is defined in:

```text
.github/workflows/selenium.yml
```

The pipeline runs on:

- Pushes to `main`
- Pull requests targeting `main`
- Published GitHub releases

### UI Test Job

The `UI-tests` job:

1. Checks out the repository.
2. Installs Node.js 24.
3. Installs Temurin Java 21.
4. Runs `npm ci` in `react-app/`.
5. Starts the React app with `npm start`.
6. Waits for `http://localhost:3000` using `wait-on`.
7. Runs the Selenium TestNG suite with `mvn clean test` in `selenium-tests/`.

This job acts as the quality gate for the Docker publishing job.

### Docker Job

The `docker` job depends on `UI-tests`, so Docker images are only built after the automated UI tests pass.

The job:

1. Reads the application version from `react-app/package.json`.
2. Sets up Docker Buildx.
3. Logs in to Docker Hub for non-pull-request events.
4. Builds the Docker image from `react-app/Dockerfile`.
5. Pushes the image for push and release events.
6. Uses GitHub Actions cache for Docker layers.
7. Publishes provenance and SBOM metadata.

Pull request builds validate the Docker image build but do not push images.

## Docker Image

The Dockerfile uses a multi-stage build:

1. `node:current-alpine3.24` builds the React production bundle.
2. `nginx:1.30.3-alpine3.23` serves the compiled static site.

The container exposes port `80`.

### Build Locally

```powershell
docker build -t selenium-webapp:local ./react-app
```

### Run Locally

```powershell
docker run --rm -p 8080:80 selenium-webapp:local
```

Then open:

```text
http://localhost:8080
```

### Published Images

The CI pipeline publishes images to Docker Hub:

```text
dwoneill18/selenium-webapp
```

Each successful non-PR pipeline publishes these tags:

- `latest`
- Git commit SHA, for traceability to the exact source revision
- Application version from `react-app/package.json`, currently `0.1.0`

Example image references:

```text
dwoneill18/selenium-webapp:latest
dwoneill18/selenium-webapp:<git-sha>
dwoneill18/selenium-webapp:0.1.0
```

## Notes for Reviewers

This repository demonstrates:

- End-to-end UI automation against a running web application
- Page Object Model implementation
- Explicit waits and reusable Selenium interaction helpers
- Headless browser execution suitable for CI
- TestNG suite configuration
- Failure artifact capture through screenshots
- CI quality gating before image publication
- Dockerized frontend delivery through Nginx
- Versioned and traceable Docker image tags
