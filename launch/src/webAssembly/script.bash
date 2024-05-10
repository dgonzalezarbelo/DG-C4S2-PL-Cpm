#!/bin/bash

# Define rutas de entrada y salida
wat_input="./launch/src/webAssembly/Main.wat"
wasm_output="./launch/src/webAssembly/Main.wasm"

# Define la ruta del ejecutable
wat2wasm_executable="./launch/src/webAssembly/wat2wasm"

# Verifica si el ejecutable y los archivos existen
if [[ -x "$wat2wasm_executable" && -f "$wat_input" ]]; then
    # Ejecuta el comando para convertir el archivo
    "$wat2wasm_executable" "$wat_input" -o "$wasm_output"

    # Verifica si el comando tuvo éxito
    if [[ $? -eq 0 ]]; then
        echo "Conversión completada con éxito."
    else
        echo "Error: no se pudo convertir el archivo."
    fi
else
    echo "Error: ejecutable wat2wasm o archivo de entrada no encontrados."
fi
