//URL que permite que la API funcione
const API_URL = "http://localhost:8080/api/v2/productos";

//-funcion para listar ProductoS y ordenarlos en tarjetas (Catalogo)
function listarProductos() {
    fetch(`${API_URL}/listar`)
        .then(response => response.json())
        .then(productos => {
            const catalogo = document.getElementById("catalogo-productos");
            catalogo.innerHTML = ""; // LIMPIA contenido anterior

            productos.forEach(producto => {
                const tarjeta = `
                    <div class="col">
                        <div class="card h-100">
                            <div class="card-body">
                                <h5 class="card-title" style="text-align: center;">${producto.nombre}</h5>
                                <p class="card-text">-${producto.marca}-</p>
                                <p class="card-text">Modelo ${producto.modelo}</p>
                                <p class="card-text">Hecho en ${producto.paisOrigen}</p>
                                <p class="card-text">contenido: ${producto.ml}ml</p>
                                <p class="card-text">Stock disponible: ${producto.stock}</p>
                                <h5 class="card-text">$${producto.precio}</h5>
                                <img src="imagenes/${producto.r_img}"width="100%" height="200px">
                            </div>
                            <div class="card-footer">
                                <button class="btn btn-success btn-sm" onclick="carrito.agregarProducto(${producto.id})">Agregar al carro</button>
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
    const ruta = document.getElementById("r_img").value;

    const nuevoProducto = 
    {
        precio,
        nombre,
        marca,
        modelo,
        pOrigen,
        ml,
        stock,
        ruta
    };

    fetch(`${API_URL}/crear`, {
        method: "POST", 
        headers: {"Content-Type": "application/json"}, 
        body: JSON.stringify(nuevoProducto) })
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
    fetch(`${API_URL}/eliminar/${id}`, {method: "DELETE"}).then(response => {
        if (response.ok) {
            alert("Se ha eliminado el Producto exitosamente");
            listarProductos();
        }
    });
}

//-variable para almacenar la ID del Producto que se este MODIFICANDO
let productoEnModificacion = null; 
//-funcion para buscar un Producto por su ID
function buscarPorID(id) {
    fetch(`${API_URL}/buscar/${id}`)
        .then(response => response.json())
        .then(producto => {
            document.getElementById("precio").value = producto.precio;
            document.getElementById("nombre").value = producto.nombre;
            document.getElementById("marca").value = producto.modelo;
            document.getElementById("modelo").value = producto.marca;
            document.getElementById("paisOrigen").value = producto.paisOrigen;
            document.getElementById("ml").value = producto.ml;
            document.getElementById("stock").value = producto.stock;
            document.getElementById("r_img").value = producto.r_img;

            productoEnModificacion = producto.id;

            const boton = document.getElementById("botonFormulario");
            if (boton) {
                boton.textContent = "Actualizar Producto";
                boton.onclick = function() {
                    actualizarProducto(producto.id);
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
    const r_imgMod = document.getElementById("r_img").value;

    const productoActualizacion = {
        id: id,
        precio: precioMod,
        nombre: nombreMod,
        marca: marcaMod,
        modelo: modeloMod,
        paisOrigen: pOrigenMod,
        ml: mlMod,
        stock: stockMod,
        r_img: r_imgMod
    };

    fetch(`${API_URL}/actualizar/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json"},
        body: JSON.stringify(productoActualizacion)
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
    document.getElementById("r_img").value = "";

    const boton = document.getElementById("botonFormulario");
    boton.innerText = "Agregar Producto";
    boton.setAttribute("onclick", "agregarProducto()");

    productoEnModificacion = null;
}

listarProductos()