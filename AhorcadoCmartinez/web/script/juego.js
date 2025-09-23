function AhorcadoDivertido() {  
    let palabraActual = palabraSession ? palabraSession.toUpperCase() : "";
    let pistas = [
        pistaSession1 || "Sin pista",
        pistaSession2 || "Sin pista",
        pistaSession3 || "Sin pista"
    ];

    let palabraAdivinada = [];
    let intentosRestantes = 7;
    let errores = 0;
    let juegoActivo = false;
    let tiempoRestante = 30;
    let intervaloTiempo = null;

    const elementos = {
        btnIniciar: document.getElementById('inicio'),
        btnPausar: document.getElementById('pausa'),
        btnReiniciar: document.getElementById('reiniciar'),
        btnSiguiente: document.getElementById('siguiente'),
        contenedorPalabra: document.getElementById('palabra'),
        contenedorPistas: document.getElementById('pistas'),
        cronometro: document.getElementById('cronometro'),
        imagenAhorcado: document.getElementById('imagenAhorcado'),
        mensajeJuego: document.getElementById('mensaje'),
        modalPerdiste: document.getElementById('modalPerdiste'),
        palabraPerdida: document.getElementById('palabraPerdida')
    };

    function iniciarJuego() {
        palabraAdivinada = new Array(palabraActual.length).fill('_');
        intentosRestantes = 7;
        errores = 0;
        juegoActivo = false;
        tiempoRestante = 30;
        elementos.imagenAhorcado.src = "img/ahorcado0.png";
        mostrarPistas();
        actualizarPantalla();
        elementos.mensajeJuego.textContent = "Presiona INICIO para comenzar";
        elementos.modalPerdiste.style.display = 'none'; 
    }

    function mostrarPistas() {
        elementos.contenedorPistas.innerHTML = pistas.map(p => `<p>${p}</p>`).join('');
    }

    function iniciar() {
        if (!palabraActual) {
            window.location.href = 'PalabraAleatoria';
            return;
        }
        if (!juegoActivo) {
            juegoActivo = true;
            iniciarTemporizador();
        }
    }

    function pausar() {
        if (!juegoActivo) return;
        if (intervaloTiempo) {
            clearInterval(intervaloTiempo);
            intervaloTiempo = null;
            elementos.btnPausar.textContent = "CONTINUAR";
        } else {
            iniciarTemporizador();
            elementos.btnPausar.textContent = "PAUSAR";
        }
    }

    function reiniciar() {
        clearInterval(intervaloTiempo);
        iniciarJuego();
    }

    function siguientePalabra() {
        window.location.href = 'PalabraAleatoria'; 
    }

    function iniciarTemporizador() {
        intervaloTiempo = setInterval(() => {
            tiempoRestante--;
            actualizarPantalla();
            if (tiempoRestante <= 0) terminarJuego(false);
        }, 1000);
    }

    function hacerIntento(letra) {
        if (!juegoActivo || !letra) return;
        letra = letra.toUpperCase();
        if (palabraAdivinada.includes(letra)) return;

        if (palabraActual.includes(letra)) {
            for (let i = 0; i < palabraActual.length; i++) {
                if (palabraActual[i] === letra) palabraAdivinada[i] = letra;
            }
            if (!palabraAdivinada.includes('_')) terminarJuego(true);
        } else {
            intentosRestantes--;
            errores++;
            elementos.imagenAhorcado.src = `img/ahorcado${errores}.png`;
            if (intentosRestantes <= 0) terminarJuego(false);
        }
        actualizarPantalla();
    }

    function terminarJuego(gano) {
        juegoActivo = false;
        clearInterval(intervaloTiempo);
        elementos.mensajeJuego.textContent = gano
            ? `¡Ganaste! La palabra era: ${palabraActual}`
            : `¡Perdiste! La palabra era: ${palabraActual}`;

        if (!gano) {
            elementos.palabraPerdida.textContent = palabraActual;
            elementos.modalPerdiste.style.display = 'block';
            elementos.imagenAhorcado.style.display = 'none'; 
        }
    }

    function mostrarModal() {
        const modal = document.getElementById('modalPerdiste');
        modal.style.display = 'block';
    }

    function cerrarModal() {
        const modal = document.getElementById('modalPerdiste');
        modal.style.display = 'none';  
    }

    document.getElementById('closeModal').addEventListener('click', cerrarModal);

    function actualizarPantalla() {
        elementos.contenedorPalabra.innerHTML = palabraAdivinada.map(l => `<span class="letter-box">${l}</span>`).join('');
        elementos.cronometro.textContent = `Tiempo: ${tiempoRestante}s | Intentos: ${intentosRestantes}`;
    }

    elementos.btnIniciar.addEventListener('click', iniciar);
    elementos.btnPausar.addEventListener('click', pausar);
    elementos.btnReiniciar.addEventListener('click', reiniciar);
    elementos.btnSiguiente.addEventListener('click', siguientePalabra);

    document.addEventListener('keypress', (e) => {
        if (juegoActivo && /^[A-ZÑa-zñ]$/.test(e.key)) {
            hacerIntento(e.key);
        }
    });

    iniciarJuego();
}

document.addEventListener('DOMContentLoaded', () => {
    window.ahorcadoJuego = new AhorcadoDivertido(); 
});
