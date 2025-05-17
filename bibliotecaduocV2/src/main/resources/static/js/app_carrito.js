const carrito = (() =>{
    const API = "http://localhost:8080/api/v2/carrito";
    async function listarCarrito() {
        try {
            const response = await fetch(API);
            const libros = await response.json();
            const tbody = document.querySelector("#tablaCarrito tbody");
            const totalItems = document.getElementById("totalCarrito");
            tbody.innerHTML = "";
            totalItems.textContent = libros.length;

            libros.forEach(libro => {
                const fila = `
                    <tr>
                        <td>${libro.id}</td>
                        <td>${libro.titulo}</td>
                        <td>${libro.autor}</td>
                        <td>
                            <button onclick= "carrito.eliminarLibro(${libro.id})">ELIMINAR </button>
                        </td>
                    </tr>
                `;
                tbody.innerHTML += fila;
            })
        } catch (err) {
            console.error("Error al cargar el carrito", err);
        }
    }
    //Funcion para agregar un libro al carrito
    async function agregarLibro(id) {
        try{
            await fetch(`${API}/agregar/${id}`,
                 {method: "POST"});
            alert("El libro ha sido agregado");
            listarCarrito();
        } catch (err) { 
            console.error("Error al agregar el libro al carrito", err);
        }
    }

    //Metodo para eliminar un libro del carrito
    async function eliminarLibro(id) {
        try {
            await fetch(`${API}/eliminar/${id}`, {method: "DELETE"});
            listarCarrito()
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
            alert("Compra realizada exitosamente");
            listarCarrito();
        }
    }
    return{listarCarrito, agregarLibro, eliminarLibro, vaciarCarrito, confirmarCompra};
}) ();