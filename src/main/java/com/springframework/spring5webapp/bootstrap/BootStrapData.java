package com.springframework.spring5webapp.bootstrap;

import com.springframework.spring5webapp.domain.Author;
import com.springframework.spring5webapp.domain.Book;
import com.springframework.spring5webapp.domain.Publisher;
import com.springframework.spring5webapp.repositories.AuthorRepository;
import com.springframework.spring5webapp.repositories.BookRepository;
import com.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
//this tells spring that this class should be detected as a spring manage component (spring managed component)
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    // once spring implements this component that will bring it into spring context
    // it will inject instances of those repos Author and Book
    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // initialize some data
        System.out.println("Started in BootStrap:");

        Publisher publisher = new Publisher();
        publisher.setName("SFG");
        publisher.setCity("Amsterdam");
        publisher.setState("NH");

        publisherRepository.save(publisher);

        Author author = new Author("Eric", "Evans");
        Book book = new Book("Domain Driven Design", "132423534");
        author.getBooks().add(book);
        book.getAuthors().add(author);

        book.setPublisher(publisher);
        publisher.getBooks().add(book);

        // save them into h2 DB
        authorRepository.save(author);
        bookRepository.save(book);
        publisherRepository.save(publisher);

        Author rod = new Author("Rod", "Johnson");
        Book sp = new Book("J2EE Development without EJB", "4567457457");
        rod.getBooks().add(sp);
        sp.getAuthors().add(rod);

        sp.setPublisher(publisher);
        publisher.getBooks().add(sp);

        // save them into h2 DB
        authorRepository.save(rod);
        bookRepository.save(sp);
        publisherRepository.save(publisher);

        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Publisher Number of books: " + publisher.getBooks().size());
    }
}
