const API_URL = "http://192.168.1.10:8080/api/v1/usuarios/login";

function login(){
    fetch(API_URL, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            email:    document.getElementById("email").value,
            password: document.getElementById("password").value

        })
    })
    .then(res => res.json())
    .then(data => {
        if(data.result === "OK"){
            sessionStorage.setItem("nombreUsuario", data.nombre);
            window.location.href = "/index.html";
        } else {
            alert("Credenciales incorrectas, verifique sus datos");
        }
    });
}