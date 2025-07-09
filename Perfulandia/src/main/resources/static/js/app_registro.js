function registrar(){
    fetch("http://192.168.1.10/api/v1/usuarios/registrar", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            nombre:   document.getElementById("nombre").value,
            email:    document.getElementById("email").value,
            password: document.getElementById("password").value
        })
    })  .then(res => res.json())
        .then(data => alert("Usuario se creo con la ID: " + data.id));
}