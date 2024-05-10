@echo off
REM Ruta al ejecutable wat2wasm
set WAT2WASM_EXECUTABLE=.\launch\src\webAssembly\wat2wasm.exe

REM Ruta del archivo de entrada .wat
set WAT_INPUT=.\launch\src\webAssembly\Main.wat

REM Ruta del archivo de salida .wasm
set WASM_OUTPUT=.\launch\src\webAssembly\Main.wasm

REM Verifica si el ejecutable y los archivos existen
if exist "%WAT2WASM_EXECUTABLE%" (
    if exist "%WAT_INPUT%" (
        REM Ejecuta el comando para convertir el archivo
        "%WAT2WASM_EXECUTABLE%" "%WAT_INPUT%" -o "%WASM_OUTPUT%"
    ) else (
        echo Error: Archivo de entrada no encontrado.
    )
) else (
    echo Error: Ejecutable wat2wasm no encontrado.
)