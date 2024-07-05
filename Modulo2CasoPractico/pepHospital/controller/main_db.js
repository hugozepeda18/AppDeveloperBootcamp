/**
 * 
 */
function guardaClienteDB ( persona ) {
   var { accion, id } = persona;
   

   const myHeaders = new Headers();
   myHeaders.append("Content-Type", "application/json");

   const raw = JSON.stringify( persona );
   const requestOptions = {
    method: ( accion === 'I' ? "POST" : "PUT" ), // **
    headers: myHeaders,
    body: raw,
    redirect: "follow"
    };

    fetch(`http://127.0.0.1:3000/personas/${accion === 'I' ? "" : id}`, requestOptions) //**
    .then( (response) => response.text() )
    .then((result) => {
                console.log("Fin inicio Guardado!!");
                console.log(result)
                // inicializar el vector
                // getDataFromDB( 'CLIENTE' );
                getForm( 'CLIENTES' )
            })
    .catch((error) => console.error( error ));


}

/**
 * Consulta la lista de datos desde la base de datos
 */
function getDataFromDB( _form_, _area_trabajo_ ){

    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    const requestOptions = {
        method: "GET",
        redirect: "follow"
    };

    local_form = _form_;
    local_area_trabajo = _area_trabajo_

    fetch("http://127.0.0.1:3000/personas/", requestOptions)
    .then( ( response ) =>  response.json()  )
    .then((result) => {
                console.log(result.data);
                personas = result.data; 
                // inicializar el vector
                // mustra la lista de registros una vez insertado o modificado 
                // mostrarLista( local_btn_   );
                dibujarTabla( local_form, local_area_trabajo, personas )
            })
    .catch((error) => console.error( error ));

}

/**
 * Elimina un cliente / persona
 * @param {id_cliente} id 
 */
function borrarPersonaDB ( id ) {
   
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
 
    const requestOptions = {
     method: "DELETE", // **
     headers: myHeaders,
     redirect: "follow"
     };
 
     fetch(`http://127.0.0.1:3000/personas/${id}`, requestOptions) //**
     .then( (response) => response.text() )
     .then((result) => {
                 console.log("Fin Eliminación del registro!!");
                 console.log(result)
                 // inicializar el vector
                 // getDataFromDB( 'CLIENTE' );
                 getForm( 'CLIENTES' )
             })
     .catch((error) => console.error( error ));
  
 }



/**
 * Almacena los datos de mascota
 * @param {*} mascota 
 */
function guardaMascotaDB ( mascota ) {
     
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
 
    const raw = JSON.stringify( mascota );
    const requestOptions = {
     method:  "POST" , // **
     headers: myHeaders,
     body: raw,
     redirect: "follow"
     };
 
     fetch(`http://127.0.0.1:3000/mascotas/`, requestOptions) //**
     .then( (response) => response.text() )
     .then((result) => {
                 console.log("Fin inicio Guardado!!");
                 console.log(result)
                 // inicializar el vector
                 // getDataFromDB( 'CLIENTE' );
                 getForm( 'MASCOTAS' )
             })
     .catch((error) => console.error( error ));
 
 
 }
 