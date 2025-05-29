const nombre = sessionStorage.getItem("nombreUsuario");
    if (nombre){
        document.getElementById("mensaje").textContent = `Hola ${nombre}, bienvenido a PerfulandiaSPA`;
        document.getElementById("mensajeBoton").textContent = `Cerrar sesion`;
    }
    function cerrarSesion(){
        sessionStorage.clear();
        window.location.href = "login.html"
    }