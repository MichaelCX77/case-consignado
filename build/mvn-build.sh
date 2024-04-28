#!/bin/bash
echo "Iniciando build dos modulos"
for dir in ../consignado*/; do
    if [ -d "$dir" ]; then
        module_name="${dir#../}"
        module_name="${module_name%/}"
        echo "Buildando o modulo: $module_name"
        
        (cd "$dir" && mvn -DskipTests clean package >../build/"$module_name-build.log" 2>&1)
    fi
done
echo "Build finalizado"