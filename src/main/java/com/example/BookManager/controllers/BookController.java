package com.example.BookManager.controllers;

import com.example.BookManager.model.constants.Book;
import com.example.BookManager.model.constants.User;
import com.example.BookManager.service.HostHolder;
import com.example.BookManager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private HostHolder hostHolder;

    @RequestMapping(path = {"/index"},method = {RequestMethod.GET})
    public String bookList(Model model){
        User host = hostHolder.getUser();
        if(host != null){
            model.addAttribute("host",host);
        }
        loadAllBooksView(model);
        return "book/books";
    }

    @RequestMapping(path = {"/books/add"},method = {RequestMethod.GET})
    public String addBook(){
        return "book/addbook";
    }

    @RequestMapping(path = {"/books/add/do"},method = {RequestMethod.POST})
    public String doAddBook(
            @RequestParam("name") String name,
            @RequestParam("author") String author,
            @RequestParam("price") String price
            ){
        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setPrice(price);
        bookService.addBooks(book);

        return "redirect:/index";
    }

     @RequestMapping(path = {"/books/{bookId:[0-9]+}/delete"})
     public String deleteBook(
     @PathVariable("bookId") int bookId
     ){
         bookService.deleteBooks(bookId);
         return "redirect:/index";
     }

     @RequestMapping(path = {"/books/{bookId:[0-9]+}/recover"})
     public String recoverBook(
     @PathVariable("bookId") int bookId
     ){
         bookService.recoverBooks(bookId);
         return "redirect:/index";
     }

    private void loadAllBooksView(Model model){
        model.addAttribute("books",bookService.getAllBooks());
    }
}
