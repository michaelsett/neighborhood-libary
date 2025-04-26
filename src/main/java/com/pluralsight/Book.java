package com.pluralsight;

public class Book {
    private int id;
    private String isbn;
    private String title;
    private boolean isCheckedOut;
    private String checkedOutTo;

    // Constructors

public Book(int id, String isbn, String title) {
    this.id = id;
    this.isbn = isbn;
    this.title = title;
    this.isCheckedOut = false;
    this.checkedOutTo = null;


    }



    //Getters & Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        isCheckedOut = checkedOut;
    }

    public String getCheckedOutTo() {
        return checkedOutTo;
    }

    public void setCheckedOutTo(String checkedOutTo) {
        this.checkedOutTo = checkedOutTo;
    }

    // Methods
    public void checkOut(String name) {
        this.isCheckedOut = true;
        this.checkedOutTo = null;
    }
    public void checkIn() {
        this.isCheckedOut = false;
        this.checkedOutTo = null;
    }
}

