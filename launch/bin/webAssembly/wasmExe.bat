REM Ruta al ejecutable wat2wasm
set NODE_EXECUTABLE=.\launch\src\webAssembly\node.exe
set NODE_FILE=.\launch\src\webAssembly\main.js

REM Verifica si el ejecutable y los archivos existen
if exist "%NODE_EXECUTABLE%" (
    if exist "%NODE_FILE%" (
        REM Ejecuta el comando para convertir el archivo
        "%NODE_EXECUTABLE%" "%NODE_FILE%"
    ) else (
        echo Error: Archivo de entrada no encontrado.
    )
) else (
    echo Error: Ejecutable wat2wasm no encontrado.
)
