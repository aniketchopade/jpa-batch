package com.bookstore.service;


import java.util.ArrayList;
import java.util.List;

import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.repository.AuthorBatchRepository;
import com.bookstore.repository.AuthorRepository;
import com.bookstore.repository.GeneralInfoRepository;

import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final GeneralInfoRepository generalinfoRepository;
    private final AuthorRepository authorRepository;
    private final AuthorBatchRepository authorBatchRepository;

    public BookstoreService(GeneralInfoRepository generalinfoRepository, 
                            AuthorRepository authorRepository,
                            AuthorBatchRepository authorBatchRepository
                            ) {
        this.generalinfoRepository = generalinfoRepository;
        this.authorRepository = authorRepository;
        this.authorBatchRepository = authorBatchRepository;
    }

    public void batchPersistAuthors() {

        long startTime = System.nanoTime();

        List<Author> authors = new ArrayList<>();
        
        for (int i = 0; i < 200; i++) {
            Author a = new Author();
            a.setAge(50);
            a.setId((long) i);
            a.setName("Author " + i);

            List<Book> books = new ArrayList<>();
            
            for (int j = 0; j < 100; j++) {
                long id = (i+1) * 100 + j;
                books.add(new Book( id ,"Kane and Able" + j, "Suspense", a));                            
            }
            a.setBooks(books);
            authors.add(a);
        }

        authorBatchRepository.saveInBatch(authors);

        long stopTime = System.nanoTime();
        System.out.println(stopTime - startTime);


    }

    public void batchAuthors() {

        long startTime = System.nanoTime();

        List<Author> authors = new ArrayList<>();
        
        for (int i = 0; i < 200; i++) {
            Author a = new Author();
            a.setAge(50);
            a.setId((long) i);
            a.setName("Author " + i);

            List<Book> books = new ArrayList<>();
            
            for (int j = 0; j < 100; j++) {
                long id = (i+1) * 100 + j;
                books.add(new Book( id,"Kane and Able" + j, "Suspense", a));            
                
            }
            a.setBooks(books);
            authors.add(a);
        }

        authorRepository.saveAll(authors);

        long stopTime = System.nanoTime();
        System.out.println(stopTime - startTime);

    
        // List<GeneralInfo> gis = new ArrayList<>();

        // for (int i = 0; i < 1000; i++) {
        //     GeneralInfo gi = new GeneralInfo();
        //     gi.setId((long) i + 1);
        //     gi.setName("Name_" + i);
        //     gi.setGenre("Genre_" + i);
        //     gi.setAge(18 + i);

        //     gis.add(gi);
        // }

        // generalinfoRepository.saveInBatch(gis);
    }
}
