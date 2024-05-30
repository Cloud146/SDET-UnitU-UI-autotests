@echo off

REM Выполните первоначальный запуск тестов
mvn clean test

REM Проверьте, существует ли файл testng-failed.xml
if exist "target\surefire-reports\testng-failed.xml" (
    echo Found failed tests. Re-running failed tests...
     mvn test "-Dsurefire.suiteXmlFiles=target\surefire-reports\testng-failed.xml"
) else (
    echo No failed tests found.
)