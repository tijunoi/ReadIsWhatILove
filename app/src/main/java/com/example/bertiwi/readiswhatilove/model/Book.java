package com.example.bertiwi.readiswhatilove.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Date;

/**
 * Created by Bertiwi on 28/02/2018.
 */



public class Book implements Parcelable{
    private String id;
    private String title;
    private String author;
    private String publisher;
    private String publishedDate;
    private String description;
    private Double averageRating;
    private String image;

    public Book() {
    }

    /**
     *
     * @param id : id del libro
     * @param title : titulo del libro
     * @param author : autor del libro
     * @param publisher : editorial del libro
     * @param publishedDate : fecha en que se ha publicado el libro
     * @param description : sinopsis de la historia del libro
     * @param averageRating : puntuación media que ha obtenido el libro en google
     * @param image : portada del libro
     */
    public Book(String id, String title, String author, String publisher, String publishedDate, String description, Double averageRating, String image) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.description = description;
        this.averageRating = averageRating;
        this.image = image;
    }

    /**
     * {@link Parcelable} implementation
     * @param in
     */
    protected Book(Parcel in) {
        id = in.readString();
        title = in.readString();
        author = in.readString();
        publisher = in.readString();
        publishedDate = in.readString();
        description = in.readString();
        if (in.readByte() == 0) {
            averageRating = null;
        } else {
            averageRating = in.readDouble();
        }
        image = in.readString();
    }


    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    /**
     * Devuelve la id del libro.
     * @return la id del libro
     */
    public String getId() {
        return id;
    }

    /**
     * Setea la id del libro.
     * @param id la id del libro
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Devuelve el titulo del libro.
     * @return el titulo del libro
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setea el titulo del libro.
     * @param title el titulo del libro
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Devuelve el autor del libro
     * @return el autor del libro
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Settea el autor del libro.
     * @param author el autor del libro
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Devuelve la editorial del libro.
     * @return la editorial del libro
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Setea la editorial del libro.
     * @param publisher la editorial del libro
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Devuelve la editorial del libro.
     * @return la editorial del libro
     */
    public String getPublishedDate() {
        return publishedDate;
    }

    /**
     * Setea la fecha de publicación del libro.
     * @param publishedDate la fecha de publicación del libro
     */
    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }


    /**
     * Devuelve la descripción del libro.
     * @return la descripción del libro
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setea la descripción del libro.
     * @param description la descripción del libro
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Devuelve la puntuación media del libro.
     * @return la puntuación media del libro
     */
    public Double getAverageRating() {
        return averageRating;
    }

    /**
     * Setea la publicación media del libro.
     * @param averageRating la publicación media del libro
     */
    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    /**
     * Devuelve la imagen de portada del libro.
     * @return la imagen de portada del libro
     */
    public String getImage() {
        return image;
    }

    /**
     * Setea la imagen de portada del libro.
     * @param image la imagen de portada del libro
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     *
     * @return
    una máscara de bits que indica el conjunto de tipos de objetos especiales ordenados por esta instancia de objeto Parcelable.
     */
    @Override
    public int describeContents() {
        return 0;
    }


    /**
     *
     * @param dest el Parcel en el que se debe escribir el objeto.
     * @param flags
    Indicadores adicionales sobre cómo se debe escribir el objeto.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(publisher);
        dest.writeString(publishedDate);
        dest.writeString(description);
        if (averageRating == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(averageRating);
        }
        dest.writeString(image);
    }
}
