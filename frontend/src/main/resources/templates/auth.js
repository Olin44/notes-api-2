// auth.js

// Funkcja do zapisywania tokenu w localStorage
function saveTokenToLocalStorage(token) {
    if (token) {
        localStorage.setItem('jwtToken', token);
        console.log('Token zapisany w localStorage:', token);
    } else {
        console.error('Nie można zapisać tokenu. Token jest pusty.');
    }
}

// Funkcja do pobierania tokenu z localStorage
function getTokenFromLocalStorage() {
    return localStorage.getItem('jwtToken');
}


function fetchNotes() {
    const token = getTokenFromLocalStorage();
    
    fetch('http://localhost:8080/notes', {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + token,
            'Content-Type': 'application/json'
        }
    })
    .then(response => response.json())
    .then(data => {
        console.log('Dane z notatek:', data);
    })
    .catch(error => {
        console.error('Błąd podczas pobierania notatek:', error);
    });
}
