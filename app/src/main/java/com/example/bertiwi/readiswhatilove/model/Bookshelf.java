package com.example.bertiwi.readiswhatilove.model;

import java.util.ArrayList;

/**
 * Created by Nil Ordoñez on 7/3/18.
 */

public class Bookshelf {
    private String title;
    private ArrayList<Book> mBookArrayList;


    /**
     *
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
