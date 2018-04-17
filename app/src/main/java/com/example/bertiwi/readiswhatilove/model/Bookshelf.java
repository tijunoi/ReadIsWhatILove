package com.example.bertiwi.readiswhatilove.model;

import java.util.ArrayList;

/**
 * Modelo bookshelf que sirve para almacenar los {@link Book} que se obtienen de Google Books API.
 * @author Nil Ordoñez
 */
public class Bookshelf {
    private String title;
    private ArrayList<Book> mBookArrayList;


    /**
     *Constructor de la clase bookshelf. Se le tiene que proporcional la array de {@link ArrayList<Book>} creada previamente.
     * @param title Titulo de la colección de libros.
     * @param bookArrayList Conjunto de libros que forman la colección.
     */
    public Bookshelf(String title, ArrayList<Book> bookArrayList) {
        this.title = title;
        mBookArrayList = bookArrayList;
    }

    /**
     * Devuelve el titulo de la colección de libros.
     * @return el titulo de la colección de libros
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setea el titulo de la colección de libros.
     * @param title el titulo de la colección de libros
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Devuelve el conjunto de libros que forman la colección.
     * @return el conjunto de libros que forman la colección
     */
    public ArrayList<Book> getBookArrayList() {
        return mBookArrayList;
    }

    /**
     * Devuelve el conjunto de libros que forman la colección.
     * @param bookArrayList el conjunto de libros que forman la colección
     */
    public void setBookArrayList(ArrayList<Book> bookArrayList) {
        mBookArrayList = bookArrayList;
    }
}
