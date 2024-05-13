REM Ruta al ejecutable node
set "NODE_EXECUTABLE=%~1"
REM Ruta al archivo .js a ejecutar
set "NODE_FILE=%~2"
REM Ruta al archivo .wasm a leer
set "WASM_FILE=%~3"
REM Ruta al archivo .txt a escribir
set "OUTPUT_FILE=%~4"


REM Verifica si el ejecutable y los archivos existen
if exist "%NODE_EXECUTABLE%" (
    if exist "%NODE_FILE%" (
        REM Ejecuta el comando para convertir el archivo
        "%NODE_EXECUTABLE%" "%NODE_FILE%" "%WASM_FILE%" "%OUTPUT_FILE%"
    ) else (
        echo Error: Archivo de entrada no encontrado.
    )
) else (
    echo Error: Ejecutable wat2wasm no encontrado.
)

:fin
