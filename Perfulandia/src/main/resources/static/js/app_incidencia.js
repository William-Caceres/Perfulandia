function registrarIncidencia(){
    fetch("http://localhost:8080/api/v2/incidencia", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            correo:   document.getElementById("correo").value,
            comentario:    document.getElementById("comentario").value

        })
    })  .then(res => res.json())
        .then(data => alert("Formulario enviado correctamente con la id: " + data.id));
}