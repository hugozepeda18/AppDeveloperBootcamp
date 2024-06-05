let varAuto = [
    {marca: "Toyota", modelo: "Corolla", año: 2020, valor:900},
    {marca: "Honda", modelo: "Civic", año: 2019, valor:1200},
    {marca: "Ford", modelo: "Mustang", año: 2021, valor:235},
    {marca: "Chevrolet", modelo: "Camaro", año: 2018, valor:2000},
    {marca: "Nissan", modelo: "Altima", año: 2020, valor:1290}
];

varAuto.forEach((auto) => {
    console.log(auto.modelo);
    auto.valor = auto.valor*1.1;
    console.log(auto.valor)
});