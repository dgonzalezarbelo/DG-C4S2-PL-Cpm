const { readFileSync } = require("fs");
const readline = require('readline');
const fs = require('fs'); // Importa el módulo fs
const inputFilePath = process.argv[2];
const outputFilePath = process.argv[3];
const isTesting = process.argv[4];
const MAX_INPUT = process.argv[5] ? process.argv[5] : 0;


const insrc = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

entrada = [];
i = 0; 

async function readInput(n){
    if (isTesting == 1) {
        for (let j = 0; j < MAX_INPUT; j++) {
            entrada.push(process.argv[6+j]); 
        }
    }
    else {
        var line;
        if (n > 0) {
            for await (line of insrc) {
                entrada.push(parseInt(line));
                n--;
                if (n==0) return;
            }
        }
        insrc.close();
    }

}

var importObjects = {
    runtime: {
        exceptionHandler : function(code) {
            let errStr;
            if (code == 1) {
                errStr= "Error 1. ";
            } else if (code == 2) {
                errStr= "Error 2. ";
        } else if (code == 3) {
                errStr= "Not enough memory. ";
        } else {
        errStr= "Unknown error\n";
            }
            throw new Error(errStr);
        },
    print: function(n) {
        fs.appendFileSync(outputFilePath, n + "\n");
        },
    read: function() {
        let val = entrada[i];
        i += 1;
        return val;
        }
    }};

async function start() {
    fs.writeFileSync(outputFilePath, "");
    const code = fs.readFileSync(inputFilePath);
    const wasmModule = await WebAssembly.compile(code);
    const instance = await WebAssembly.instantiate(wasmModule, importObjects);
    process.exit(0)
}

async function run() {
    await readInput(MAX_INPUT);
    start();
}

run();