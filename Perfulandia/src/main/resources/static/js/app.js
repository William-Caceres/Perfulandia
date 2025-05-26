//URL que permite que la API funcione
const API_URL = "http://localhost:8080/api/v2/perfumes";

//-funcion para listar PERFUMES y ordenarlos en tarjetas (Catalogo)
function listarPerfumes()
{
    fetch(API_URL)
        .then(response => response.json())
        .then(perfume => {
            const catalogo = document.getElementById("catalogo-productos")
            perfume.forEach(perfume => {
                const tarjeta =
                `
                            <!--img src = "..." class = "card-img-top" alt = "..."-->
                            <div class = "card-body">
                                <h5 class = "card-title">${perfume.nombre}</h5>
                                <p class = "card-text"> Marca: ${perfume.marca} </p>
                                <p class = "card-text"> Modelo: ${perfume.modelo} </p>
                                <p class = "card-text"> Pais de origen: ${perfume.paisOrigen} </p>
                                <p class = "card-text"> Contenido: ${perfume.ml}ml </p>
                            </div>
                            <div class = "card-footer">
                                <button class="btn btn-danger btn-sm" onclick="eliminarPerfume(${perfume.id})">Eliminar</button> 
                                <button class="btn btn-warning btn-sm" onclick="buscarPorID(${perfume.id})">Editar</button> 
                                <button class="btn btn-success btn-sm" onclick="carrito.agregarPerfume(${perfume.id})">Agregar al carro</button>
                `;
                catalogo.innerHTML += tarjeta;
            });
        });
}

//-variable para almacenar la lista de PERFUMES  
let perfume = [];

//-funcion para agregar un PERFUME
function agregarPerfume() {
    const nombre = document.getElementById("nombre").value;
    const marca = document.getElementById("marca").value;
    const modelo = document.getElementById("modelo").value;
    const ml = document.getElementById("ml").value;

    const nuevoPerfume = 
    {
        nombre,
        marca,
        modelo,
        ml
    };

    fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json"},
        body:  JSON.stringify(nuevoPerfume)
    })
    .then(response => response.json())
    .then(data => {
        alert("Perfume agregado exitosamente a la base de datos");
        listarPerfumes();
        limpiarFormularioPerfumes();
    });
}

//-funcion para ELIMINAR un PERFUME
function eliminarPerfume(id) {
    fetch(`${API_URL}/${id}`, {method: "DELETE"}).then(response => {
        if (response.ok) {
            alert("Se ha eliminado el perfume exitosamente");
            listarPerfumes();
        }
    });
}

//-variable para almacenar la ID del PERFUME que se este MODIFICANDO
let perfumeEnModificacion = null; 
//-funcion para buscar un PERFUME por su ID
function buscarPorID(id) {
    fetch(`${API_URL}/${id}`)
        .then(response => response.json())
        .then(perfume => {
            document.getElementById("nombre").value = perfume.nombre;
            document.getElementById("marca").value = perfume.modelo;
            document.getElementById("modelo").value = perfume.marca;
            document.getElementById("ml").value = perfume.ml;

            perfumeEnModificacion = perfume.id;

            const boton = document.getElementById("botonFormulario");
            if (boton) {
                boton.textContent = "Actualizar perfume";
                boton.onclick = function() {
                    actualizarPerfume(perfume.id);
                };
            }
        });
}

//-funcion para ACTUALIZAR los datos de un PERFUME
function actualizarPerfume(id) {
    const nombreMod = document.getElementById("nombre").value;
    const marcaMod = document.getElementById("marca").value;
    const modeloMod = document.getElementById("modelo").value;
    const mlMod = document.getElementById("ml").value;

    const perfumeActualizacion = {
        id: id,
        nombre: nombreMod,
        marca: marcaMod,
        modelo: modeloMod,
        ml: mlMod
    };

    fetch(`${API_URL}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json"},
        body: JSON.stringify(perfumeActualizacion)
    })
    .then(response => response.json())
    .then(data => {
        alert("Perfume actualizado exitosamente");
        listarPerfumes();
        limpiarFormularioPerfumes();
    });
}

//-funcion para limpiar el formulario de AGREGAR/ACTUALIZAR un PERFUME
function limpiarFormularioPerfumes() {
    document.getElementById("nombre").value = "";
    document.getElementById("marca").value = "";
    document.getElementById("modelo").value = "";
    document.getElementById("ml").value = "";

    const boton = document.getElementById("botonFormulario");
    boton.innerText = "Agregar perfume";
    boton.setAttribute("onclick", "agregarPerfume()");

    perfumeEnModificacion = null;
}

listarPerfumes()