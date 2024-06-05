const prompt = require('prompt-sync')();

/* EJERCICIO 3 - BUCLES */
 let flag = true;
while(flag) {
    let num = prompt("Ingrese su edad: ");
    if (num <= 0) {
        console.log("Por favor introduzca una edad válido");
    }
    if(num >= 18) {
        console.log("Eres mayor de edad");
    } else {
        console.log("Eres menor de edad");
    }
    let answer = prompt("Desea continuar? (y/n)");
    if (answer === "n") {
        flag = false;
    }
} 

/* EJERCICIO 4 - BUCLES ANIDADOS */

let num = prompt("Ingrese el número de filas de la piramide: ");
for (let i = 1; i <= num; i++) {
    let str = "";
    for (let j = 1; j <= i; j++) {
        str += "*";
    }
    console.log(str);
}