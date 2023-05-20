package org.ed.model;

public enum Gender {

    POP(1), ROCK(2), PUNK(3), REGGAETON(4), ELCTRONICA(5);

    private int id;

    Gender(int id) {

        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Gender getGender(int id) {
        for (Gender genre : Gender.values()) {
            if (genre.getId() == id) {
                return genre;
            }
        }
        throw new IllegalArgumentException("No se encontró ningún género con el índice proporcionado");
    }
}
