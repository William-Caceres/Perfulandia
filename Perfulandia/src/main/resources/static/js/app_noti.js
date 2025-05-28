const API_URL = "http://localhost:8080/api/v2/notificaciones";


//Función para obtener las notificaciones del usuario.

async function obtener_notificaciones() {
    if (!sessionStorage.getItem("nombreUsuario")) {
        alert("No hay usuario activo.");
        return;
    }
    const usuario_activo = sessionStorage.getItem("nombreUsuario");
    if (!usuario_activo) {
        console.error("No hay usuario activo.");
        return [];
    }
    const response = await fetch(`${API_URL}/destinatario/${usuario_activo}`);
    const notificaciones = await response.json();
    const contenedor_notificaciones = document.getElementById("div_notificaciones");
    contenedor_notificaciones.innerHTML = "";
    if (Array.isArray(notificaciones) && notificaciones.length > 0) {
        contenedor_notificaciones.innerHTML = `
            <h2>Notificaciones</h2>
            <table id="tabla_notificaciones">
                <thead>
                    <tr>
                        <th>Destinatario</th>
                        <th>Mensaje</th>
                        <th>Acciones</th>
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
                    <td style="line-height: 1; overflow-wrap: break-word; word-break: normal; white-space: pre-line;">${notificacion.mensaje.replace(/\n/g, "<br>")}</td>
                    <td>
                        <button onclick="eliminar_notificacion(${notificacion.id})">Eliminar</button>
                    </td>
                </tr>
            `;
            tbody.innerHTML += fila;
        });
    }else {
        contenedor_notificaciones.innerHTML = `
            <h2>No hay notificaciones</h2>
        `;
    }
}

// Esta funcion la utilizamos para redirigir al aparatado de notificaciones
function reedirigir_a_notificaciones() {
    window.location.href = "/notificaciones.html";
}