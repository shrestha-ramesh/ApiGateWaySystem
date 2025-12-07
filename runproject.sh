#!/bin/bash

# This script cleans and builds all Spring Boot applications in the ApiGateWaySystem project.
# It assumes the script is located in the ApiGateWaySystem root folder.
# It assumes each application folder has the necessary files.

# Define the applications to build.  Add more if you have more applications.
APPLICATIONS=("orders" "payment" "delivery")

# Function to execute a Gradle command in a specific application's directory.
run_gradle_command() {
    local app_name="$1"
    local gradle_command="$2"
    local app_path="./$app_name"
    #local gradlew_executable="$app_path/gradlew"
    local absolute_path="/Users/rameshshrestha/Downloads/ApiGateWaySystem/$app_name/gradlew"

    pushd "$app_path" > /dev/null

    case "$gradle_command" in
    "clean")
    if ! /bin/bash "$absolute_path" clean; then
      echo "Gradle command 'clean' failed for application '$app_name'!"
      popd > /dev/null
      return 1
    fi
    ;;
    "build")
    if ! /bin/bash "$absolute_path" build; then
      echo "Gradle command 'build' failed for application '$app_name'!"
      popd > /dev/null
      return 1
    fi
    ;;
    "bootRun")
    if ! /bin/bash "$absolute_path" bootRun; then
      echo "Gradle command 'clean' failed for application '$app_name'!"
      popd > /dev/null
      return 1
    fi
    ;;
    *)
    echo "Error: Invalid Gradle command '$gradle_command' for application '$app_name'!"
    popd > /dev/null
    return 1
    ;;
    esac
    popd > /dev/null
    return 0
}

# Loop through the applications and clean and build each one.
for app in "${APPLICATIONS[@]}"; do
    if ! run_gradle_command "$app" "clean"; then
        echo "Failed to clean application: $app.  Exiting."
        exit 1
    fi
    if ! run_gradle_command "$app" "build"; then
        echo "Failed to build application: $app.  Exiting."
        exit 1
    fi
    if ! run_gradle_command "$app" "bootRun"; then
      echo "Failed to run application $app. Exiting."
      exit 1
    fi
done

echo "Successfully cleaned and built all applications."
exit 0
