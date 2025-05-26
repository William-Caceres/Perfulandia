const carrito = (() =>{
    const API = "http://localhost:8080/api/v2/carrito";
    async function listarCarrito() {
        try {
            const response = await fetch(API);
            const Perfume = await response.json();
            const tbody = document.querySelector("#tablaCarrito tbody");
            const totalItems = document.getElementById("totalCarrito");
            tbody.innerHTML = "";
            totalItems.textContent = Perfume.length;

            Perfume.forEach(Perfume => {
                const fila = `
                    <tr>
                        <td> ${Perfume.id}</td>
                        <td> ${Perfume.nombre}</td>
                        <td> ${Perfume.ml}</td>
                        <td>
                            <button onclick= "carrito.eliminarPerfume(${Perfume.id})">Quitar del carro </button>
                        </td>
                    </tr>
                `;
                tbody.innerHTML += fila;
            })
        } catch (err) {
            console.error("Error al cargar el carrito", err);
        }
    }
    //Funcion para agregar un Perfume al carrito
    async function agregarPerfume(id) {
        try{
            await fetch(`${API}/agregar/${id}`,
                 {method: "POST"});
            alert("El Perfume ha sido agregado");
            listarCarrito();
        } catch (err) { 
            console.error("Error al agregar el Perfume al carrito", err);
        }
    }

    //Metodo para eliminar un libro del carrito
    async function eliminarPerfume(id) {
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
    return{listarCarrito, agregarPerfume, eliminarPerfume, vaciarCarrito, confirmarCompra};
}) ();