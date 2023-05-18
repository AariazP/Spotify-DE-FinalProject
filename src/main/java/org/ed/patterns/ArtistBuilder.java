package org.ed.patterns;

import org.ed.model.Artist;

public class ArtistBuilder {
    
    private UserBuilder userBuilder;
    private Artist artist;
    
    
    public ArtistBuilder() {
        this.artist = new Artist();
        this.userBuilder = new UserBuilder();
        userBuilder.setUser(this.artist);
    }
    
    public ArtistBuilder isBand(Boolean isBand) {
        this.artist.setIsBand(isBand);
        return this;
    }

    /*
     * The following methods are the setters for the user's attributes.
     */
    public ArtistBuilder name(String name) {
        userBuilder.name(name);
        return this;
    }

    public ArtistBuilder userName(String userName) {
        userBuilder.userName(userName);
        return this;
    }

    public ArtistBuilder id(Long id) {
        userBuilder.id(id);
        return this;
    }

    public ArtistBuilder password(String password) {
        userBuilder.password(password);
        return this;
    }

    public ArtistBuilder email(String email) {
        userBuilder.email(email);
        return this;
    }

    public ArtistBuilder nationality(String nationality) {
        userBuilder.nationality(nationality);
        return this;
    }

    public Artist build() {
        return this.artist;
    }
    
    
    
}
