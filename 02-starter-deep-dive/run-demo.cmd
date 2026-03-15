@echo off
echo ========================================================
echo Installing modules to local repository...
echo ========================================================
call mvnw.cmd clean install -DskipTests
if %errorlevel% neq 0 (
    echo Build failed!
    pause
    exit /b %errorlevel%
)

echo.
echo ========================================================
echo Starting demo-consumer application...
echo ========================================================
cd demo-consumer
call ..\mvnw.cmd spring-boot:run

