function add(a, b) {
    return a + b;
}
function subtract(a, b) {
    return a - b;
} 
function multiply(a, b) {
   return a * b;
}
function divide(a, b) {
    if (b === 0) {
      throw new Error("Division by zero is not allowed");
    }
    return a / b;
}

function calculate(operation, a, b) {
    switch (operation) {
      case "+":
        return add(a, b);
      case "-":
        return subtract(a, b);
      case "*":
        return multiply(a, b);
      case "/":
        return divide(a, b);
      default:
        throw new Error("Invalid operation provided");
    }
}

const result1 = calculate("+", 5, 3); 
const result2 = calculate("-", 10, 2); 
const result3 = calculate("*", 4, 6); 
const result4 = calculate("/", 12, 3); 