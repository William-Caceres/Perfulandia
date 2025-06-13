const carrito = (() =>{
    const API = "http://localhost:8080/api/v2/carrito";
    async function listarCarrito() {
        try {
            const response = await fetch(API);
            const Producto = await response.json();
            const tbody = document.querySelector("#tablaCarrito tbody");
            const totalItems = document.getElementById("totalCarrito");
            tbody.innerHTML = "";
            totalItems.textContent = Producto.length;

            Producto.forEach(Producto => {
                const fila = `
                    <tr>
                        <td> $${Producto.precio}</td>
                        <td> ${Producto.nombre}</td>
                        <td> ${Producto.marca}</td>
                        <td> ${Producto.paisOrigen}</td>
                        <td>
                            <button onclick= "carrito.eliminarProducto(${Producto.id})">Quitar del carro </button>
                        </td>
                    </tr>
                `;
                tbody.innerHTML += fila;
            })
        } catch (err) {
            console.error("Error al cargar el carrito", err);
        }
    }
    //Funcion para agregar un Producto al carrito
    async function agregarProducto(id) {
        try{
            await fetch(`${API}/agregar/${id}`,
                 {method: "POST"});
            alert("El Producto ha sido agregado");
            listarCarrito();
        } catch (err) { 
            console.error("Error al agregar el Producto al carrito", err);
        }
    }

    //Metodo para eliminar un libro del carrito
    async function eliminarProducto(id) {
        try {
            await fetch(`${API}/eliminar/${id}`, {method: "DELETE"});
            listarCarrito();
        } catch (err) {
            console.error("Error al eliminar del carrito")
        }
    }

    //Funcion para vaciar el carrito
    async function vaciarCarrito(){
        if(confirm("¿Estas seguro de querer vaciar el carrito?")) {
            await fetch(`${API}/vaciar`, {method: "DELETE"})
            alert("Carrito vaciado");
            listarCarrito();
        }
    }

    //Funcion para confirmar la compra
    async function confirmarCompra() {
        const total = document.getElementById("totalCarrito").textContent;
        if (parseInt(total) == 0){
            alert("El carrito esta vacio")
            return;
        }
        if(confirm(`"¿Desea confirmar la compra por ${total}?"`)){
            await fetch(`${API}/vaciar`, {method: "DELETE"})
            //Intento de generacion de notificacion 
            //Primero recuperamos el nombre del usuario
            const usuario = sessionStorage.getItem("nombreUsuario");
             if (usuario) {
            // Crear notificación con un mensaje para el usuario :D
            await fetch("http://localhost:8080/api/v2/notificaciones/crear", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    destinatario: usuario,
                    mensaje: `¡Hola ${usuario}! Tu compra por ${total} Producto(s) fue realizada con éxito.`
                }),
            });
            //refrescamos al obtener notificaciones para que le salgan de inmediato al usuario al confirmar la compra
            obtener_notificaciones();
        }
            alert("Compra realizada exitosamente");
            listarCarrito();
        }
    }
    
    listarCarrito()
    return{listarCarrito, agregarProducto, eliminarProducto, vaciarCarrito, confirmarCompra};
}) (); 