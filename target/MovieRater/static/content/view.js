let views = Object.freeze({
    movieSelectView: document.getElementById('movie-list'),
    ratingView: document.getElementById('movie-rater')
});

let view = {
    currentView: views.movieSelectView,

    changeView(to) {
        this.transition(this.currentView, to);        
        this.currentView = to;
    },

    transition(from, to) {
        from.classList.add('hidden');
        to.classList.remove('hidden');
    }
}

let generateMovieList = function() {
    console.log('generating');
    let element = document.getElementById('movie-list');

    // Remove all children
    while(element.firstChild) {
        element.removeChild(element.firstChild);
    }

    // Fill in with new data
    console.log(movies.movies);
    for(let movie of movies.movies) {
        console.log(movie);
        element.appendChild(getMovieContainer(movie));
    }
}

let getMovieContainer = function(movie) {
    // Create elements
    let container = document.createElement('div');
    container.classList.add('movie-card');
    let image = document.createElement('img');
    let p = document.createElement('p');

    // Append elements
    container.appendChild(image);
    container.appendChild(p);

    // Set data
    image.setAttribute('src', apiHome + 'static/images/movie-' + movie.id + '.jpg');
    p.innerText = movie.name;

    // return card
    return container;
}