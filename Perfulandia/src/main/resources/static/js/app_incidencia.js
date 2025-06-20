const API_INS = "http://10.155.67.163:8080/api/v2/incidencia"

function registrarIncidencia(){
    fetch(`${API_INS}/crear`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            nombre_opinion: document.getElementById("nombre_opinion").value,
            opinion:        document.getElementById("opinion").value,
            mejora:         document.getElementById("mejora").value,
            calificacion:   parseInt(document.getElementById("calificacion").value)
        })
    })  .then(res => res.json())
        .then(data => {
            alert("Opinion registrada con la ID: " + data.id);
            listarIncidencias();
        }); 
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
                                <p class="card-text">Mi opinion (${ins.nombre_opinion}),<br> ${ins.opinion}</p>
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