function AhorcadoDivertido() {  
    let currentWord = palabraSession ? palabraSession.toUpperCase() : "";
    let currentHints = [
        pistaSession1 || "Sin pista",
        pistaSession2 || "Sin pista",
        pistaSession3 || "Sin pista"
    ];

    let guessedWord = [];
    let attemptsLeft = 7;
    let wrongGuesses = 0;
    let gameActive = false;
    let timer = 30;
    let timerInterval = null;

    const elements = {
        startBtn: document.getElementById('inicio'),
        pauseBtn: document.getElementById('pausa'),
        restartBtn: document.getElementById('reiniciar'),
        nextWordBtn: document.getElementById('siguiente'),
        wordContainer: document.getElementById('palabra'),
        hintContainer: document.getElementById('pistas'),
        timerDisplay: document.getElementById('cronometro'),
        hangmanImage: document.getElementById('imagenAhorcado'),
        gameMessage: document.getElementById('mensaje')
    };

    function initGame() {
        guessedWord = new Array(currentWord.length).fill('_');
        attemptsLeft = 7;
        wrongGuesses = 0;
        gameActive = false;
        timer = 30;
        elements.hangmanImage.src = "img/ahorcado0.png";
        displayHints();
        updateDisplay();
        elements.gameMessage.textContent = "¡Presiona INICIO para comenzar!";
    }

    function displayHints() {
        elements.hintContainer.innerHTML = currentHints.map(p => `<p>${p}</p>`).join('');
    }

    function startGame() {
        if (!currentWord) {
            window.location.href = 'PalabraAleatoria';
            return;
        }
        if (!gameActive) {
            gameActive = true;
            startTimer();
        }
    }

    function pauseGame() {
        if (!gameActive) return;
        if (timerInterval) {
            clearInterval(timerInterval);
            timerInterval = null;
            elements.pauseBtn.textContent = "▶ CONTINUAR";
        } else {
            startTimer();
            elements.pauseBtn.textContent = "⏸ PAUSAR";
        }
    }

    function restartGame() {
        clearInterval(timerInterval);
        initGame();
    }

    function nextWord() {
        window.location.href = 'PalabraAleatoria'; 
    }

    function startTimer() {
        timerInterval = setInterval(() => {
            timer--;
            updateDisplay();
            if (timer <= 0) endGame(false);
        }, 1000);
    }

    function makeGuess(letter) {
        if (!gameActive || !letter) return;
        letter = letter.toUpperCase();
        if (guessedWord.includes(letter)) return;

        if (currentWord.includes(letter)) {
            for (let i = 0; i < currentWord.length; i++) {
                if (currentWord[i] === letter) guessedWord[i] = letter;
            }
            if (!guessedWord.includes('_')) endGame(true);
        } else {
            attemptsLeft--;
            wrongGuesses++;
            elements.hangmanImage.src = `img/ahorcado${wrongGuesses}.png`;
            if (attemptsLeft <= 0) endGame(false);
        }
        updateDisplay();
    }

    function endGame(won) {
        gameActive = false;
        clearInterval(timerInterval);
        elements.gameMessage.textContent = won
            ? `Ganaste: ${currentWord}`
            : `Perdiste. Palabra: ${currentWord}`;
    }

    function updateDisplay() {
        elements.wordContainer.innerHTML = guessedWord.map(l => `<span class="letter-box">${l}</span>`).join('');
        elements.timerDisplay.textContent = `Tiempo: ${timer}s | Intentos: ${attemptsLeft}`;
    }

    elements.startBtn.addEventListener('click', startGame);
    elements.pauseBtn.addEventListener('click', pauseGame);
    elements.restartBtn.addEventListener('click', restartGame);
    elements.nextWordBtn.addEventListener('click', nextWord);

    document.addEventListener('keypress', (e) => {
        if (gameActive && /^[A-ZÑa-zñ]$/.test(e.key)) {
            makeGuess(e.key);
        }
    });

    initGame();
}

document.addEventListener('DOMContentLoaded', () => {
    window.ahorcadoGame = new AhorcadoDivertido(); 
});
