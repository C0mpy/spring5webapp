package compi.springframework.spring5webapp.bootstrap;

import compi.springframework.spring5webapp.model.Author;
import compi.springframework.spring5webapp.model.Book;
import compi.springframework.spring5webapp.model.Publisher;
import compi.springframework.spring5webapp.repositories.AuthorRepository;
import compi.springframework.spring5webapp.repositories.BookRepository;
import compi.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository,
                        PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234");

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);

        Publisher harp = new Publisher("Harper Collins", "123 NY");
        publisherRepository.save(harp);
        ddd.setPublisher(harp);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE development without EJB", "23444");

        rod.getBooks().add(noEJB);
        authorRepository.save(rod);

        Publisher worx = new Publisher("Worx", "321 SF");
        publisherRepository.save(worx);
        noEJB.setPublisher(worx);
        bookRepository.save(noEJB);
    }
}
