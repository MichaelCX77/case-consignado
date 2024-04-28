@echo off

SETLOCAL ENABLEDELAYEDEXPANSION
echo Iniciando build dos modulos
for /d %%d in (..\consignado*) do (
    
    SET "modulename=%%~nd"
    echo Buildando o modulo: !modulename!
    pushd "%%d"
    call mvn -DskipTests clean package > ..\build\!modulename!-build.log 2>&1
    popd
)
echo Build finalizado