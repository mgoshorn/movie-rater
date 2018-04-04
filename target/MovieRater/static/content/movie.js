class Movie {

    constructor(id, name, year) {
        this.id = id;
        this.name = name;
    }

    equals(other) {
        return JSON.stringify(this) === JSON.stringify(other);
    }
}