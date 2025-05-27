//URL que permite que la API funcione
const API_URL = "http://localhost:8080/api/v2/Productos";

//-funcion para listar ProductoS y ordenarlos en tarjetas (Catalogo)
function listarProductos() {
    fetch(API_URL)
        .then(response => response.json())
        .then(Productos => {
            const catalogo = document.getElementById("catalogo-productos");
            catalogo.innerHTML = ""; // LIMPIA contenido anterior

            Productos.forEach(Producto => {
                const tarjeta = `
                    <div class="col">
                        <div class="card h-100">
                            <div class="card-body">
                                <h5 class="card-title">${Producto.nombre}</h5>
                                <p class="card-text">-${Producto.marca}-</p>
                                <p class="card-text">Modelo ${Producto.modelo}</p>
                                <p class="card-text">Hecho en ${Producto.paisOrigen}</p>
                                <p class="card-text">contenido: ${Producto.ml}ml</p>
                                <p class="card-text">Stock disponible: ${Producto.stock}</p>
                                <h5 class="card-text">$${Producto.precio}</h5>
                            </div>
                            <div class="card-footer">
                                <button class="btn btn-success btn-sm" onclick="carrito.agregarProducto(${Producto.id})">Agregar al carro</button>
                                <button class="btn btn-success btn-sm" onclick="eliminarProducto(${Producto.id})">Eliminar</button>
                                <button class="btn btn-success btn-sm" onclick="buscarPorID(${Producto.id})">Modificar</button>
                            </div>
                        </div>
                    </div>`;
                catalogo.innerHTML += tarjeta;
            });
        });
}

//-variable para almacenar la lista de ProductoS  
let Producto = [];

//-funcion para agregar un Producto
function agregarProducto() {
    const precio = document.getElementById("precio").value;
    const nombre = document.getElementById("nombre").value;
    const marca = document.getElementById("marca").value;
    const modelo = document.getElementById("modelo").value;
    const pOrigen = document.getElementById("paisOrigen").value;
    const ml = parseInt(document.getElementById("ml").value);
    const stock = parseInt(document.getElementById("stock").value);

    const nuevoProducto = 
    {
        precio,
        nombre,
        marca,
        modelo,
        pOrigen,
        ml,
        stock
    };

    fetch(API_URL, {method: "POST", headers: {"Content-Type": "application/json"}, body: JSON.stringify(nuevoProducto) })
        .then(response => {
            if (!response.ok) throw new Error("Error al guardar Producto");
            return response.json();
        })
        .then(data => {
            alert("Producto agregado exitosamente");
            listarProductos();
            limpiarFormularioProductos();
        })
        .catch(error => {
            console.error("Error:", error);
            alert("Ocurrió un error al guardar el Producto.");
        });
}

//-funcion para ELIMINAR un Producto
function eliminarProducto(id) {
    fetch(`${API_URL}/${id}`, {method: "DELETE"}).then(response => {
        if (response.ok) {
            alert("Se ha eliminado el Producto exitosamente");
            listarProductos();
        }
    });
}

//-variable para almacenar la ID del Producto que se este MODIFICANDO
let ProductoEnModificacion = null; 
//-funcion para buscar un Producto por su ID
function buscarPorID(id) {
    fetch(`${API_URL}/${id}`)
        .then(response => response.json())
        .then(Producto => {
            document.getElementById("precio").value = Producto.precio;
            document.getElementById("nombre").value = Producto.nombre;
            document.getElementById("marca").value = Producto.modelo;
            document.getElementById("modelo").value = Producto.marca;
            document.getElementById("paisOrigen").value = Producto.marca;
            document.getElementById("ml").value = Producto.ml;
            document.getElementById("stock").value = Producto.stock;

            ProductoEnModificacion = Producto.id;

            const boton = document.getElementById("botonFormulario");
            if (boton) {
                boton.textContent = "Actualizar Producto";
                boton.onclick = function() {
                    actualizarProducto(ProductoEnModificacion);
                };
            }
        });
}

//-funcion para ACTUALIZAR los datos de un Producto---------------------------------------------------
function actualizarProducto(id) {
    const precioMod = document.getElementById("precio").value;
    const nombreMod = document.getElementById("nombre").value;
    const marcaMod = document.getElementById("marca").value;
    const modeloMod = document.getElementById("modelo").value;
    const pOrigenMod = document.getElementById("paisOrigen").value;
    const mlMod = document.getElementById("ml").value;
    const stockMod = document.getElementById("stock").value;

    const ProductoActualizacion = {
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
        body: JSON.stringify(ProductoActualizacion)
    })
    .then(response => response.json())
    .then(data => {
        alert("Producto actualizado exitosamente");
        listarProductos();
        limpiarFormularioProductos();
    });
}

//-funcion para limpiar el formulario de AGREGAR/ACTUALIZAR un Producto
function limpiarFormularioProductos() {
    document.getElementById("precio").value = "";
    document.getElementById("nombre").value = "";
    document.getElementById("marca").value = "";
    document.getElementById("modelo").value = "";
    document.getElementById("paisOrigen").value = "";
    document.getElementById("ml").value = "";
    document.getElementById("stock").value = "";

    const boton = document.getElementById("botonFormulario");
    boton.innerText = "Agregar Producto";
    boton.setAttribute("onclick", "agregarProducto()");

    ProductoEnModificacion = null;
}

listarProductos()