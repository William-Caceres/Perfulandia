//URL que permite que la API funcione
const API_URL = "http://localhost:8080/api/v2/perfumes";

//-funcion para listar PERFUMES y ordenarlos en tarjetas (Catalogo)
function listarPerfumes() {
    fetch(API_URL)
        .then(response => response.json())
        .then(perfumes => {
            const catalogo = document.getElementById("catalogo-productos");
            catalogo.innerHTML = ""; // LIMPIA contenido anterior

            perfumes.forEach(perfume => {
                const tarjeta = `
                    <div class="col">
                        <div class="card h-100">
                            <div class="card-body">
                                <h5 class="card-title">${perfume.nombre}</h5>
                                <p class="card-text">-${perfume.marca}-</p>
                                <p class="card-text">Modelo ${perfume.modelo}</p>
                                <p class="card-text">Hecho en ${perfume.paisOrigen}</p>
                                <p class="card-text">contenido: ${perfume.ml}ml</p>
                                <p class="card-text">Stock disponible: ${perfume.stock}</p>
                                <h5 class="card-text">$${perfume.precio}</h5>
                            </div>
                            <div class="card-footer">
                                <button class="btn btn-success btn-sm" onclick="carrito.agregarPerfume(${perfume.id})">Agregar al carro</button>
                                <button class="btn btn-success btn-sm" onclick="eliminarPerfume(${perfume.id})">Eliminar</button>
                                <button class="btn btn-success btn-sm" onclick="buscarPorID(${perfume.id})">Modificar</button>
                            </div>
                        </div>
                    </div>`;
                catalogo.innerHTML += tarjeta;
            });
        });
}

//-variable para almacenar la lista de PERFUMES  
let perfume = [];

//-funcion para agregar un PERFUME
function agregarPerfume() {
    const precio = document.getElementById("precio").value;
    const nombre = document.getElementById("nombre").value;
    const marca = document.getElementById("marca").value;
    const modelo = document.getElementById("modelo").value;
    const pOrigen = document.getElementById("paisOrigen").value;
    const ml = parseInt(document.getElementById("ml").value);
    const stock = parseInt(document.getElementById("stock").value);

    const nuevoPerfume = 
    {
        precio,
        nombre,
        marca,
        modelo,
        pOrigen,
        ml,
        stock
    };

    fetch(API_URL, {method: "POST", headers: {"Content-Type": "application/json"}, body: JSON.stringify(nuevoPerfume) })
        .then(response => {
            if (!response.ok) throw new Error("Error al guardar perfume");
            return response.json();
        })
        .then(data => {
            alert("Perfume agregado exitosamente");
            listarPerfumes();
            limpiarFormularioPerfumes();
        })
        .catch(error => {
            console.error("Error:", error);
            alert("Ocurrió un error al guardar el perfume.");
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
            document.getElementById("precio").value = perfume.precio;
            document.getElementById("nombre").value = perfume.nombre;
            document.getElementById("marca").value = perfume.modelo;
            document.getElementById("modelo").value = perfume.marca;
            document.getElementById("paisOrigen").value = perfume.marca;
            document.getElementById("ml").value = perfume.ml;
            document.getElementById("stock").value = perfume.stock;

            perfumeEnModificacion = perfume.id;

            const boton = document.getElementById("botonFormulario");
            if (boton) {
                boton.textContent = "Actualizar perfume";
                boton.onclick = function() {
                    actualizarPerfume(perfumeEnModificacion);
                };
            }
        });
}

//-funcion para ACTUALIZAR los datos de un PERFUME---------------------------------------------------
function actualizarPerfume(id) {
    const precioMod = document.getElementById("precio").value;
    const nombreMod = document.getElementById("nombre").value;
    const marcaMod = document.getElementById("marca").value;
    const modeloMod = document.getElementById("modelo").value;
    const pOrigenMod = document.getElementById("paisOrigen").value;
    const mlMod = document.getElementById("ml").value;
    const stockMod = document.getElementById("stock").value;

    const perfumeActualizacion = {
        id: id,
        precio: precioMod,
        nombre: nombreMod,
        marca: marcaMod,
        modelo: modeloMod,
        paisOrigen: pOrigenMod,
        ml: mlMod,
        stock: stockMod
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
    document.getElementById("precio").value = "";
    document.getElementById("nombre").value = "";
    document.getElementById("marca").value = "";
    document.getElementById("modelo").value = "";
    document.getElementById("paisOrigen").value = "";
    document.getElementById("ml").value = "";
    document.getElementById("stock").value = "";

    const boton = document.getElementById("botonFormulario");
    boton.innerText = "Agregar perfume";
    boton.setAttribute("onclick", "agregarPerfume()");

    perfumeEnModificacion = null;
}

listarPerfumes()