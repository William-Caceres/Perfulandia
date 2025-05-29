const nombre = sessionStorage.getItem("nombreUsuario");
    if (nombre){
        document.getElementById("mensaje").textContent = `Bienvenido a la tienda, ${nombre}`;
    }
    function cerrarSesion(){
        sessionStorage.clear();
        window.location.href = "login.html"
    }