@echo off

REM Verifica si se pasaron los argumentos correctos
if "%~1"=="" (
    echo Error: Falta el primer argumento.
    exit /b 1
)

if "%~2"=="" (
    echo Error: Falta el segundo argumento.
    exit /b 2
)

if "%~3"=="" (
    echo Error: Falta el tercer argumento.
    exit /b 3
)

REM Ruta al ejecutable wat2wasm + wat2wasm.exe
set "WAT2WASM_EXECUTABLE=%~1"
REM Ruta del archivo de entrada + nombre.wat
set "WAT_INPUT=%~2"
REM Ruta del archivo de salida + nombre.wasm
set "WASM_OUTPUT=%~3"

REM Verifica si el ejecutable y los archivos existen
if exist "%WAT2WASM_EXECUTABLE%" (
    if exist "%WAT_INPUT%" (
        REM Ejecuta el comando para convertir el archivo
        "%WAT2WASM_EXECUTABLE%" "%WAT_INPUT%" -o "%WASM_OUTPUT%"
        set "RESULT_CODE=%ERRORLEVEL%"
        exit /b %RESULT_CODE%
    ) else (
        echo Error: Archivo de entrada no encontrado.
        exit /b 4
    )
) else (
    echo Error: Ejecutable wat2wasm no encontrado.
    exit /b 5
)
