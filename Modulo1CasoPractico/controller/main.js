/**
 * funcion de llamado asincronico del formulario del parametro
 */
function getForm( _form_ ){
    // alert( _form_ );
    var url_form = './form/';
    var area_trabajo = 'area_clientes';
    switch( _form_) {
        case 'CLIENTE':{
            url_form = url_form + 'Cliente.html';
            break;
        }
        case 'MASCOTA': {
            url_form = url_form + 'Mascota.html';
            break;
        }
        case 'CITAS' :{
            url_form = './view/Citas.html'
            break;
        }
        case 'CLIENTES': {
            url_form = './view/Clientes.html'
             area_trabajo = 'area_clientes';
            break;
        }
        case 'MASCOTAS': {
            url_form = './view/Mascotas.html'
            area_trabajo = 'area_mascotas';
            break;
        }
    }
    var _formulario_procesar_ = _form_;
    fetch(url_form)
    .then( response => response.text() )
    .then( form => {
            document.getElementById('area_trabajo').innerHTML = form;
            queryData( _formulario_procesar_, area_trabajo )
        }
    );
}


/**
 * Funcion encargada de procesar los formulario
 * @param {*} _formulario_ 
 */
function procesarFormulario( _btn_ ) {
    switch( _btn_.form.name  ){
        case "frm_cliente": {
            guardaCliente ( _btn_ );     
            break;
        }
        case "frm_mascota":{
            guardaMascota( _btn_ );
            break;
        }
    }
}

/**
 * Funcion encargada de consulta informacion y presentarla
 * @param {*} _formulario_procesar_ 
 * @param {*} area_trabajo 
 */
function queryData( _formulario_procesar_, area_trabajo ) {
    console.log(  _formulario_procesar_, area_trabajo  )
   
    switch( _formulario_procesar_ ) {
        case 'CLIENTES': {
            if ( personas && personas.length > 0 ) {
                for ( var i = 0 ; i < personas.length; i++ ){
                                 var fila =  `<tr><td>${personas[i].id}
                                                    </td>
                                                    <td>
                                                        ${personas[i].nombre}
                                                    </td>
                                                    <td>
                                                        ${personas[i].apellido}
                                                    </td>
                                                    <td>
                                                        ${personas[i].celular}
                                                    </td>
                                                    <td>
                                                    <button name="editar" onclick="editarPersona('${personas[i].id}')" type="button" >Editar</button>
                                                    &nbsp;
                                                    <button name="borrar" onclick="borrarPersona('${personas[i].id}')" type="button" >Borrar</button>                                                    
                                                    </td>
                                                <tr>`;
                    //document.getElementById(area_trabajo).appendChild(fila);    
                    document.getElementById(area_trabajo).innerHTML += fila;
               }
            }
            break;
        }
        // no deberia estar aqui
        case 'MASCOTA' : {
            if ( document.getElementById("persona")  ) {
                var objPersona = document.getElementById("persona");
                if ( personas && personas.length > 0 ) {
                    for ( persona in personas ){
                        var localOption = `<option value='${personas[persona].id}' >${personas[persona].nombre} ${personas[persona].apellido}  </option>`
                        objPersona.innerHTML += localOption;
                    }
                }
            }
        }

        case 'MASCOTAS': {
            if ( mascotas && mascotas.length > 0 ) {
                for ( var i = 0 ; i < mascotas.length; i++ ){
                                 var fila =  `<tr><td>${mascotas[i].id_persona}
                                                    </td>
                                                    <td>${mascotas[i].id}
                                                    </td>
                                                    <td>
                                                        ${mascotas[i].nombre}
                                                    </td>
                                                    <td>
                                                        ${mascotas[i].raza}
                                                    </td>
                                                    <td>
                                                    <button name="editar" onclick="editarMascota('${mascotas[i].id}')" type="button" >Editar</button>
                                                    &nbsp;
                                                    <button name="borrar" onclick="borrarMascota('${mascotas[i].id}')" type="button" >Borrar</button>                                                    
                                                    </td>
                                                <tr>`;
                    //document.getElementById(area_trabajo).appendChild(fila);    
                    document.getElementById(area_trabajo).innerHTML += fila;
               }
            }
            break;
        }

    }

}


/**
 * Busqueda generica de personas usando el id
 * @param {*} id 
 * @returns 
 */
function buscarPersona( id ) {
    //debugger;
    var posicion = -1;
    if ( personas && personas.length > 0 ) {
        for ( var i = 0 ; i < personas.length; i++ ){
            if ( personas[i].id === id  ){
                posicion = i;
                break;    
            }
        }
    }
    return posicion;
}

/**
 * Funcion de Edicion de un Cliente / persona
 * @param {*} id 
 * @returns 
 */
function editarPersona ( id ) {
    var posicion =  buscarPersona( id );
    if ( posicion == -1 ){
        alert ("No se encontro el Registo!!!");
        return;
    }
    var persona = personas[posicion];
    // abrir el formuario
    var url_form = './form/';
    _form_ = 'CLIENTE';
    switch( _form_) {
        case 'CLIENTE':{
            // getForm( _form_ );
            url_form = url_form + 'Cliente.html';
            fetch(url_form)
             .then( response => response.text() )
             .then( form => {
                   document.getElementById('area_trabajo').innerHTML = form;
                   document.getElementById("id").value = persona.id;
                   document.getElementById("nombre").value = persona.nombre;
                   document.getElementById("apellido").value = persona.apellido;
                   document.getElementById("celular").value = persona.celular;            
                 }
             );
            break;
        }
    }    

}

/**
 * 
 * @param {*} id 
 * @returns 
 */
function borrarPersona(id) {
    var posicion =  buscarPersona( id );
    if ( posicion == -1 ){
        alert ("No se encontro el Registo!!!");
        return;
    }
    if ( confirm(`Seguro desea borrar el registro\n${personas[posicion].nombre} ${personas[posicion].apellido}`)   ){

        personas.splice(posicion,1);

        var _formulario_procesar_ = 'CLIENTES';
        url_form = './view/Clientes.html'
        fetch(url_form)
        .then( response => response.text() )
        .then( form => {
                var area_trabajo = 'area_clientes';
                document.getElementById('area_trabajo').innerHTML = form;
                queryData( _formulario_procesar_, area_trabajo )
            }
        );
    
    }

}

/**
 * Funcion que almacena los datos del cliente
 */
function guardaCliente ( _btn_   ) {

    console.log( _btn_.form.elements  ); // objetos 
    var elementosFormulario = _btn_.form.elements;
    var persona = {};
    for ( var i = 0; i < elementosFormulario.length ; i++  ){
        console.log( elementosFormulario[i].value );
        if ( elementosFormulario[i].nodeName === 'INPUT' ) {
            switch( elementosFormulario[i].name ) {
                case 'id': {
                    persona.id = elementosFormulario[i].value;
                    break;
                }
                case 'nombre': {
                    persona.nombre = elementosFormulario[i].value;
                    break;
                }
                case 'apellido': {
                    persona.apellido = elementosFormulario[i].value;
                    break;
                }
                case 'celular': {
                    persona.celular = elementosFormulario[i].value;
                    break;
                }                                
            }
        }
    }
    console.log( persona  );

    var posicion = buscarPersona( persona.id );
    if ( posicion == -1 ) {
        personas.push(persona); // arreglo
    }
    else {
        personas[posicion] = persona;
    }
    // mustra la lista de registros una vez insertado o modificado 
    mostrarLista( _btn_   );

} 


/**
 * 
 * @param {*} _btn 
 */
function guardaMascota( _btn_ ) {
    console.log( _btn_.form.elements  ); // objetos 
    var elementosFormulario = _btn_.form.elements;
    var mascota = {};
    for ( var i = 0; i < elementosFormulario.length ; i++  ){
        console.log("-->>", elementosFormulario[i].value, elementosFormulario[i].nodeName );
        if ( elementosFormulario[i].nodeName === 'INPUT' || elementosFormulario[i].nodeName === 'SELECT'    ) {
            switch( elementosFormulario[i].name ) {
                case 'id': {
                    mascota.id = elementosFormulario[i].value;
                    break;
                }
                case 'nombre': {
                    mascota.nombre = elementosFormulario[i].value;
                    break;
                }
                case 'raza': {
                    mascota.raza = elementosFormulario[i].value;
                    break;
                }
                case 'id_persona': {
                    mascota.id_persona = elementosFormulario[i].value;
                    break;
                }                                
            }
        }
    }
    console.log( mascota  );

    mascotas.push(mascota); // arreglo

    mostrarLista( _btn_  );


}

function mostrarLista( _btn_   ) {

    switch( _btn_.form.name ) {
        case "frm_cliente":{
            var _formulario_procesar_ = 'CLIENTES';
            url_form = './view/Clientes.html'
            fetch(url_form)
            .then( response => response.text() )
            .then( form => {
                    var area_trabajo = 'area_clientes';
                    document.getElementById('area_trabajo').innerHTML = form;
                    queryData( _formulario_procesar_, area_trabajo )
                }
            );
            break;
        }
        case "frm_mascota":{
            var _formulario_procesar_ = 'MASCOTAS';
            url_form = './view/Mascotas.html'
            fetch(url_form)
            .then( response => response.text() )
            .then( form => {
                    var area_trabajo = 'area_mascotas';
                    document.getElementById('area_trabajo').innerHTML = form;
                    queryData( _formulario_procesar_, area_trabajo )
                }
            );

        }
    }


}