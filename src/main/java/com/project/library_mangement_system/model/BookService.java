package com.project.library_mangement_system.model;

import com.project.library_mangement_system.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private IssuedBookService issuedBookService;

    public BookService(BookRepository bookRepository) {
        this.bookRepository=bookRepository;
    }

    public Long getTotalCount() {
        return bookRepository.count();
    }

    public Long getTotalIssuedBooks() {
        return bookRepository.countByStatus(Constants.BOOK_STATUS_ISSUED);
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book get(Long id) {
        return bookRepository.findById(id).get();
    }

    public Book getByTag(String tag) {
        return bookRepository.findByTag(tag);
    }

    public List<Book> get(List<Long> ids) {
        return bookRepository.findAllById(ids);
    }

    public List<Book> getByCategory(Category category) {
        return bookRepository.findByCategory(category);
    }

    public List<Book> geAvailabletByCategory(Category category) {
        return bookRepository.findByCategoryAndStatus(category, Constants.BOOK_STATUS_AVAILABLE);
    }

    public Book addNew(Book book) {
        book.setCreateDate(new Date());
        book.setStatus( Constants.BOOK_STATUS_AVAILABLE );
        return bookRepository.save(book);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void delete(Book book) {
        bookRepository.delete(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
    public boolean hasUsage(Book book) {
        return issuedBookService.getCountByBook(book)>0;
    }
}
