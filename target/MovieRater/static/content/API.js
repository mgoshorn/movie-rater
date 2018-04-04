const apiHome = 'http://localhost:8080/MovieRater/'; 

const API = Object.freeze({
    loadMovies: `${apiHome}Movie/`,
    loadRatings: `${apiHome}Rating/`
});

let loadMovies = function() {
    fetch(API.loadMovies).then(function(incomingMovies) {
        console.log(incomingMovies);
        movies.movies = incomingMovies;
        generateMovieList();
    }).catch(function(status) {
        console.log('Failed to load movies: ' + status);
    })
}

let loadRatings = function(movie) {
    // TODO 
}

let fetch = function(url, method = 'GET') {
    const request = new XMLHttpRequest();
    return new Promise((resolve, reject) => {
        request.onreadystatechange = function() {
            if(request.readyState === 4) {
                if(request.status === 200) {
                    resolve(JSON.parse(request.response));
                } else {
                    reject(request.status);
                }
            }
        }
        request.open(method, url, true);
        request.send();
    });   
}

window.addEventListener("load", function() {
    loadMovies();
})