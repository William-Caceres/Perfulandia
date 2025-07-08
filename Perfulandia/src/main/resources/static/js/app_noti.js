const API_NOT = "http://192.168.1.88:8080/api/v1/notificaciones";

// Función para obtener las notificaciones del usuario.
async function obtener_notificaciones() {
    const usuario_activo = sessionStorage.getItem("nombreUsuario");
    if (!usuario_activo) {
        alert("No hay usuario activo.");
        return;
    } else {

        const response = await fetch(`${API_NOT}/destinatario/${usuario_activo}`);
        const notificaciones = await response.json();
        const contenedor_notificaciones = document.getElementById("div_notificaciones");
        contenedor_notificaciones.innerHTML = "";

        // Nos basamos en un repositorio de github como modelo y según debería funcionar para mostrarnos un diseño decente de de columnas en notificaciones
        if (Array.isArray(notificaciones) && notificaciones.length > 0) {
            contenedor_notificaciones.innerHTML = `
                <h2>Notificaciones</h2>
                <table id="tabla_notificaciones">
                    <thead>
                        <tr>
                            <th>Destinatario</th>
                            <th>Mensaje</th>
                        </tr>
                    </thead>
                    <tbody></tbody>
                </table>
            `;
            const tbody = document.querySelector("#tabla_notificaciones tbody");
            notificaciones.forEach(notificacion => {
                const fila = `
                    <tr>
                        <td>${notificacion.destinatario}</td>
                        <td style="line-height: 1; overflow-wrap: break-word; word-break: normal; white-space: pre-line;">
                            ${notificacion.mensaje.replace(/\n/g, "<br>")}</td>
                        <td> 
                            <button onclick= "eliminarNoti(${notificacion.id})">Borrar notificacion</button>
                        </td>
                    </tr>`;
                tbody.innerHTML += fila;
            });
            //Si funcionó :D
        } else {
            contenedor_notificaciones.innerHTML = `<h2>No hay notificaciones</h2>`;
        }
    }
}

// Función para redirigir al apartado de notificaciones.
function redirigir_a_notificaciones() {
    window.location.href = "notificaciones.html";
}

function eliminarNoti(id) {
    fetch(`${API_NOT}/eliminar/${id}`, {method: "DELETE"}).then(response => {
        if (response.ok) {
            alert("Se ha eliminado la Notificacion");
            obtener_notificaciones();
        }
    });
}
// Funcion para eliminar la notificacion

//me tiraba error, investigué y se debería solucionar con el comando window (espero funcione)
// no funcionó :( , pero quedará registro del intento en el siguiente code

/* SIN USAR
function eliminar_notificacion(id) {
    if (!sessionStorage.getItem("nombreUsuario")) {
        alert("No hay usuario activo.");
        return;
    }
    const usuario_activo = sessionStorage.getItem("nombreUsuario");
    if (!usuario_activo) {
        console.error("No hay usuario activo.");
        return [];
    }
    fetch(`${API_NOT}/eliminar/${id}`, {method: "DELETE"});
    alert("La notificación ha sido eliminada");
    obtener_notificaciones();
}

function eliminarNotificacion(id){
    fetch
}
*/

// Llamar a la función cuando el DOM esté listo.
document.addEventListener("DOMContentLoaded", () => {
    obtener_notificaciones();
});
