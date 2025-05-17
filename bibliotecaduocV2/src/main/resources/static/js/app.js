// Este archivo contiene el código JavaScript para la gestión de libros en la aplicación web.
// Se utiliza para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los libros.
const API_URL = "http://localhost:8080/api/v2/libros"; // URL de la API para acceder a los libros
// Función para listar los libros en la tabla
// Se utiliza la API Fetch para obtener los datos de los libros desde el servidor
function listarLibros() {
    fetch(API_URL)
        .then(response => response.json())
        .then(libros => {
            const tbody = document.querySelector("#tablaLibros tbody");
            tbody.innerHTML = "";
            libros.forEach(libro => {
                const fila = `
                    <tr>
                        <td>${libro.id}</td>
                        <td>${libro.titulo}</td>
                        <td>${libro.autor}</td>
                        <td>${libro.editorial}</td>
                        <td>${libro.isbn}</td>
                        <td>${libro.fechaPublicacion}</td>
                        <td> 
                            <button class="btn btn-danger btn-sm" onclick="eliminarLibro(${libro.id})">🗑️ Eliminar</button> 
                            <button class="btn btn-warning btn-sm" onclick="buscarLibro(${libro.id})">✏️ Editar</button> 
                            <button class="btn btn-success btn-sm" onclick="carrito.agregarLibro(${libro.id})">Agregar</button> 
                        </td>
                    </tr>
                `;
                tbody.innerHTML += fila;
            });
        });
}
let libros = []; // Variable para almacenar la lista de libros
// Función para agregar un libro
function agregarLibro() {
    const titulo = document.getElementById("titulo").value;
    const autor = document.getElementById("autor").value;
    const editorial = document.getElementById("editorial").value;
    const isbn = document.getElementById("isbn").value;
    const fechaPublicacion = document.getElementById("fechaPublicacion").value;
    
    const nuevoLibro = {
        titulo,
        autor,
        editorial,
        isbn,
        fechaPublicacion: parseInt(fechaPublicacion) || new Date().getFullYear()
    };
    // Enviar el nuevo libro al servidor
    // Se utiliza la API Fetch para enviar los datos al servidor
    fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(nuevoLibro)
    })// Enviar el nuevo libro al servidor
    .then(response => response.json())
    .then(data => {
        alert("Libro agregado exitosamente");
        listarLibros();// Actualizar la tabla de libros
        limpiarFormulario();// Limpiar el formulario
    });
}
// Función para eliminar un libro
function eliminarLibro(id) {
    fetch(`${API_URL}/${id}`, { method: "DELETE" })
        .then(response => {
            if (response.ok) {
                alert("Libro eliminado exitosamente");
                listarLibros();
            }
        });
}
// Función para buscar un libro por su ID y cargarlo en el formulario
// Se utiliza la API Fetch para obtener los datos del libro desde el servidor
let libroEnEdicionId = null; // Variable para almacenar el ID del libro en edición
function buscarLibro(id) {
    fetch(`${API_URL}/${id}`)
        .then(response => response.json())
        .then(libro => {
            document.getElementById("titulo").value = libro.titulo;
            document.getElementById("autor").value = libro.autor;
            document.getElementById("editorial").value = libro.editorial;
            document.getElementById("isbn").value = libro.isbn;
            document.getElementById("fechaPublicacion").value = libro.fechaPublicacion;

             // Guardar el ID del libro en edición
             libroEnEdicionId = libro.id;
             
            
            const boton = document.getElementById("botonFormulario");
            if (boton) {
                boton.textContent = "Actualizar Libro";
                boton.onclick = function() {
                    actualizarLibro(libro.id);
                };
            }
        });
}
// Función para actualizar un libro
// Se utiliza la API Fetch para enviar los datos actualizados al servidor
function actualizarLibro(id) {
    const titulo = document.getElementById("titulo").value;
    const autor = document.getElementById("autor").value;
    const editorial = document.getElementById("editorial").value;
    const isbn = document.getElementById("isbn").value;
    const fechaPublicacion = document.getElementById("fechaPublicacion").value;

    const libroActualizado = {
        id: id,
        titulo: titulo,
        autor: autor,
        editorial: editorial,
        isbn: isbn,
        fechaPublicacion: parseInt(fechaPublicacion) || new Date().getFullYear()
    };

    fetch(`${API_URL}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(libroActualizado)
    })
    .then(response => response.json())
    .then(data => {
        alert("Libro actualizado exitosamente");
        listarLibros();
        limpiarFormulario();
    });
}
// Función para limpiar el formulario después de agregar o actualizar un libro
// Se utiliza para restaurar el formulario a su estado inicial
function limpiarFormulario() {
    document.getElementById("titulo").value = "";
    document.getElementById("autor").value = "";
    document.getElementById("editorial").value = "";
    document.getElementById("isbn").value = "";
    document.getElementById("fechaPublicacion").value = "";

    // Restaurar botón
    const boton = document.getElementById("botonFormulario");
    boton.innerText = "Agregar Libro";
    boton.setAttribute("onclick", "agregarLibro()");

    // Resetear la variable global
    libroEnEdicionId = null; // Resetear el ID después de limpiar
}

// Cargar libros al abrir la página

listarLibros();
