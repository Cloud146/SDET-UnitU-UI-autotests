@echo off

REM Указание пути к testng.jar и директории с классами вашего проекта
set TESTNG_JAR=testng-7.10.2.jar
set PROJECT_CLASSES=target\test-classes\Tests

REM Запуск основного набора тестов
java -cp "%TESTNG_JAR%;%PROJECT_CLASSES%" org.testng.TestNG testng.xml

REM Проверка наличия файла testng-failed.xml
IF EXIST "target\surefire-reports\testng-failed.xml" (
    echo Failed tests detected. Running failed tests...
    java -cp "%TESTNG_JAR%;%PROJECT_CLASSES%" org.testng.TestNG target\surefire-reports\testng-failed.xml
) ELSE (
    echo No failed tests. All tests passed successfully.
)
