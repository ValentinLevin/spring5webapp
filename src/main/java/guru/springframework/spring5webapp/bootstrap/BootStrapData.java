package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(
            AuthorRepository authorRepository,
            BookRepository bookRepository,
            PublisherRepository publisherRepository
    ) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher piterPublisher = new Publisher("Piter");
        this.publisherRepository.save(piterPublisher);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        ddd.getAuthors().add(eric);
        ddd.setPublisher(piterPublisher);

        this.authorRepository.save(eric);
        this.bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "65482313");
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(piterPublisher);

        this.authorRepository.save(rod);
        this.bookRepository.save(noEJB);
        this.publisherRepository.save(piterPublisher);

        Iterable<Publisher> publisherList = this.publisherRepository.findAll();
        piterPublisher = publisherList.iterator().next();

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + this.bookRepository.count());
        System.out.println("Number of Authors: " + this.authorRepository.count());
        System.out.println("Number of Publishers: " + this.publisherRepository.count());
        System.out.println("Number of books in Publisher entity: " + piterPublisher.getBooks().size());
    }
}
