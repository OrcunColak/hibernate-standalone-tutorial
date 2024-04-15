package com.colak;

import com.colak.jpa.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class HibernateApp {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Book book = new Book();
        book.setName("Sample Book");
        book.setAuthor("John Doe");

        saveBook(entityManager, book);
        findAllBooks(entityManager);

        // Close EntityManager and EntityManagerFactory
        entityManager.close();
        entityManagerFactory.close();
    }

    private static void saveBook(EntityManager entityManager, Book book) {
        // Begin transaction
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        // Persist the book entity
        entityManager.persist(book);

        // Commit transaction
        transaction.commit();
    }

    private static void findAllBooks(EntityManager entityManager) {
        // Begin transaction
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        // Execute named query to find all books
        TypedQuery<Book> query = entityManager.createNamedQuery("Book.findAll", Book.class);
        List<Book> books = query.getResultList();

        // Output the results
        for (Book book : books) {
            log.info("Name: {}, Author: {}", book.getName(), book.getAuthor());
        }

        // Commit transaction
        transaction.commit();
    }

}