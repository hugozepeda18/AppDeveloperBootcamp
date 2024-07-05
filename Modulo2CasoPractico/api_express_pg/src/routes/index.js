const { Router } = require('express');
const router = Router();
var pool = require('../database.js'); 

router.get( '/', (req, res ) => {
    console.log( req.headers  );
        res.json (
            {
             "msg":"Aplicación en Línea!!!",  
            }
        );
    }
);

/**
 * 
 */
router.get( '/personas/', async (req, res ) => {
    var select = "SELECT * FROM persona ";
    try {
        const data = await pool.query( select  );
        var dataSend = {};
        dataSend.data = data.rows;
		res.status(200).send({ "data" : dataSend.data});	
    }
    catch( err ){

        console.log("ERROR", err );
    }

});

/**
 * Funcion de guardado de personas
 */

router.post( '/personas/', async (req, res ) => {

    console.log( req.body );
    persona = req.body;

    var insert = "INSERT INTO persona (nombre,apellido,celular)";
    insert += " VALUES ( $1, $2, $3)";

    const iData =  await  pool.query( insert, [ persona.nombre,persona.apellido,persona.celular ] )   ;
    console.log(  iData );  
       res.json (
        {
            "msg"  : "Persona Almacenada",
            "data" : persona
        }
        );
    }
);

/**
 * Consultar una persona / Cliente
 */
router.get( '/personas/:id', (req, res ) => {
    
    console.log("req.params.id -> ", req.params.id );
       var select = "SELECT * FROM persona WHERE id = ? ";
       var parameters = [req.params.id];

       db.get( select,parameters, (error, row) => {
           if (error ){
               res.status(400).json({"msg": error.message });
               return;
           }
           else {
               console.log( row );
               res.json ({
                   "msg"  : "Consulta efectuada",
                   "data" : row,
               });
           }
           });
    }); 

/**
 * Actualizacion de una persona / cliente 
 */
router.put( '/personas/:id', (req, res ) => {
    console.log( req.body );
    persona = req.body;
    var { nombre, apellido, celular }  = req.body;
    id = req.params.id

    var update = "UPDATE persona " ;
    update += " SET nombre = ?, apellido = ? , celular = ?";
    update += " WHERE id = ? ";

    db.run( update, [ nombre, apellido, celular, id ] )   ;
       res.json (
        {
            "msg"  : "Persona Actualizada",
            "data" : persona,
            "id": id
        }
        );
    }


 ); 

/**
 * Borra persona / Cliente
 */
 router.delete( '/personas/:id', (req, res ) => {
    console.log( req.body );
    persona = req.body;
    var { nombre, apellido, celular }  = req.body;
    id = req.params.id

    var _delete_ = "DELETE FROM persona " ;
    _delete_ += " WHERE id = ? ";

    db.run( _delete_, [ id ] )   ;
       res.json (
        {
            "msg"  : "Persona Borrada..",
            "data" : persona,
            "id": id
        }
        );
    }


 ); 

////// rutas para CRUD Mascotas
router.post( '/mascotas/', async (req, res ) => {
/*
CREATE TABLE public.mascotas (
	id_mascota serial4 NOT NULL,
	nombre_mascota varchar(255) NULL,
	tipo_mascota varchar(50) NULL,
	edad_mascota int4 NULL,
	acompanante int4 NULL,
	CONSTRAINT mascotas_pkey PRIMARY KEY (id_mascota)
);
*/
    console.log( req.body );
    let { nombre_mascota,tipo_mascota,acompanante }  = req.body;

    var insert = "INSERT INTO mascotas (nombre_mascota,tipo_mascota,acompanante)";
    insert += " VALUES ( $1, $2, $3)";

    const iData =  await  pool.query( insert, [ nombre_mascota, tipo_mascota, acompanante ] )   ;
    console.log(  iData );  
       res.json (
        {
            "msg"  : "Mascota Almacenada",
            "data" : req.body
        }
        );
    }
);


 module.exports = router;