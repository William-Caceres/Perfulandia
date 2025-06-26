const API_INS = "http://192.168.1.6:8080/api/v1/incidencia"

function registrarIncidencia(){
    if (sessionStorage.getItem("nombreUsuario") != null) {
        if (parseInt(document.getElementById("calificacion").value)>0 && parseInt(document.getElementById("calificacion").value)<= 10
            && document.getElementById("opinion").value != ""
            && document.getElementById("mejora").value != "") {
                const usuario = sessionStorage.getItem("nombreUsuario");
                fetch(`${API_INS}/crear`, {
                method: "POST",
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify({
                    usuario:        usuario,
                    opinion:        document.getElementById("opinion").value,
                    mejora:         document.getElementById("mejora").value,
                    calificacion:   parseInt(document.getElementById("calificacion").value)
                })
            })  .then(res => res.json())
                .then(data => {
                    alert("Opinion registrada con la ID: " + data.id);
                    listarIncidencias();
                }); 
        } else {
            alert("El puntaje debe estar dentro de los rangos, y no puede haber texto vacio");
        }
    } else {
        alert("No se puede registrar la opinion sin tener una sesion activa");
    }
}

function listarIncidencias(){
    fetch(`${API_INS}/listar`)
        .then(response => response.json())
        .then(incis => {
            const listaIns = document.getElementById("tabla");
            listaIns.innerHTML = "";

            incis.forEach(ins => {
                const tabla = `
                    <div class="col">
                        <div class="card h-100">
                            <div class="card-body">
                                <p class="card-text">ID registro: ${ins.id}</p>
                                <p class="card-text">Mi opinion (${ins.usuario}),<br> ${ins.opinion}</p>
                                <p class="card-text">¿Que podria mejorar?<br> ${ins.mejora}</p>
                            </div>
                            <div class="card-footer">
                                <p class="card-text">Calificación: ${ins.calificacion}</p>
                            </div>
                        </div>
                    </div>`;
                listaIns.innerHTML += tabla;
            });
        });
}

listarIncidencias()