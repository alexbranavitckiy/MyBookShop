package com.example.MyBookShopApp.controllers;


import com.example.MyBookShopApp.data.ApiResponse;
import com.example.MyBookShopApp.data.Dto.*;
import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.data.other.Statistics;
import com.example.MyBookShopApp.data.other.Tag;
import com.example.MyBookShopApp.erss.BookStoreApiWrongException;
import com.example.MyBookShopApp.erss.EmptySearchExceprtion;
import com.example.MyBookShopApp.myAnnotations.GlobalData;
import com.example.MyBookShopApp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
@GlobalData
public class PageController {

    private final BookService bookService;
    private final TagService tagService;
    private final ResourceStorage storage;
    private final MappingService mappingService;
    private final StatisticsServices statisticsServices;


    @Autowired
    public PageController(StatisticsServices statisticsServices, MappingService mappingService, ResourceStorage storage, BookService bookService, TagService tagService) {
        this.storage = storage;
        this.statisticsServices = statisticsServices;
        this.mappingService = mappingService;
        this.tagService = tagService;
        this.bookService = bookService;
    }

    @GetMapping("/books/recommended")
    @ResponseBody
    public RecommendedBooksPageDto getBooksPageRecommended(@RequestParam("offset") Integer offset,
                                                           @RequestParam("limit") Integer limit) {
        return new RecommendedBooksPageDto(bookService.getPageOfNameSortBooks(offset, limit, "priceOld").getContent());
    }


    @GetMapping("/books/{slug}")
    public String bookPage(@PathVariable("slug") String slug, Model model, HttpServletResponse httpResponse) {
        Optional<Book> bookOptional = bookService.getBookBySlug(slug);
        if (bookOptional.isPresent()) {
            model.addAttribute("slugBook", bookOptional.get());
            Statistics statistics = bookOptional.get().getStatistics();
            model.addAttribute("averageValue",  statistics.getAverageValue());
            model.addAttribute("averageNumber", NumberFormat.getInstance().format(statistics.getNumberAverage()));
        }
        return "books/slug";
    }


    @GetMapping("/books/popular")
    @ResponseBody
    public RecommendedBooksPageDto getBooksPagePopular(@RequestParam("offset") Integer offset,
                                                       @RequestParam("limit") Integer limit) {
        return new RecommendedBooksPageDto(this.statisticsServices.getPageOfNameSortStatisticsBooksByPopularAndMapping(offset, limit, "coefficient"));
    }

    @PostMapping("/books/{slug}/img/save")
    public String saveNewBookImage(@RequestParam("file") MultipartFile file, @PathVariable("slug") String slug) throws IOException {
        String savePath = storage.saveNewBookImage(file, slug);
        Optional<Book> bookOptional = bookService.getBookBySlug(slug);
        bookOptional.ifPresent(book -> book.setImage(savePath));
        bookOptional.ifPresent(bookService::saveBook);
        return "redirect:/books/" + slug;
    }


    @GetMapping("/books/download/{hash}")
    public ResponseEntity<ByteArrayResource> bookFile(@PathVariable("hash") String hash) throws IOException {
        Path path = storage.getBookFilePath(hash);
        Logger.getLogger(this.getClass().getSimpleName()).info("book file path: " + path);
        MediaType mediaType = storage.getBookFileMime(hash);
        Logger.getLogger(this.getClass().getSimpleName()).info("book file mime type: " + mediaType);
        byte[] data = storage.getBookFileByteArray(hash);
        Logger.getLogger(this.getClass().getSimpleName()).info("book file data len: " + data.length);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
                .contentType(mediaType)
                .contentLength(data.length)
                .body(new ByteArrayResource(data));
    }


    @GetMapping("/books/recent")
    @ResponseBody
    public RecommendedBooksPageDto getBooksRecent(@RequestParam(required = false, value = "offset") Integer offset,
                                                  @RequestParam(required = false, value = "limit") Integer limit,
                                                  @RequestParam(required = false, value = "from") String from,
                                                  @RequestParam(required = false, value = "to") String to) {
        if (from != null && to != null && !from.isEmpty() && !to.isEmpty()) {
            return new RecommendedBooksPageDto(bookService.getPageOfDateBooks(offset, limit,
                    from.substring(6, 10) + "-" + from.substring(3, 5) + "-" + from.substring(0, 2),
                    to.substring(6, 10) + "-" + to.substring(3, 5) + "-" + to.substring(0, 2)).getContent());
        }
        return new RecommendedBooksPageDto(this.statisticsServices.getPageOfNameSortStatisticsBooksByPopularAndMapping(offset, limit, "coefficient"));
    }

    @ModelAttribute("tagList")
    public List<TagDto> getTagListDesc() {
        return this.mappingService.mapToListTagDto(this.tagService.findAllTagsAndSortSizeDesc());
    }

    @ModelAttribute("recommendedBooks")
    public List<BookDto> recommendedBooks() {
        return bookService.getPageOfNameSortBooksDto(0, 6, "priceOld").getContent();
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @ModelAttribute("popularBooks")
    public List<Book> popularBooks() {
        return this.statisticsServices.getPageOfNameSortStatisticsBooksByPopularAndMapping(0, 10, "coefficient");
    }

    @ModelAttribute("resentBooks")
    public List<BookDto> bestsellersBooks() {
        return bookService.getPageOfNameSortBooksDto(0, 10, "pubDate").getContent();
    }


    @GetMapping("/books/recentPage")
    public String recentPage() {
        return "books/recent";
    }

    @GetMapping("/books/popularPage")
    public String popularPage() {
        return "books/popular";
    }

    @ModelAttribute("booksList")
    public List<Book> bookList() {
        return bookService.getBooksData();
    }

    @GetMapping("/documents/index")
    public String documentsPage() {
        return "documents/index";
    }

    @GetMapping("/tags/index")
    public String tagsPage() {
        return "tags/index";
    }

    @GetMapping("/books/slugmy")
    public String  slugmy() {
        return "books/slugmy";
    }

    @GetMapping("/my")
    public String myPage() {
        return "my";
    }

    @GetMapping("/signin")
    public String signupPage() {
        return "signin";
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }

    @GetMapping("/faq")
    public String faqPage() {
        return "faq";
    }

    @GetMapping("/contacts")
    public String contactsPage() {
        return "contacts";
    }

    @GetMapping("/profile")
    public String profilePage() {
        return "profile";
    }

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

}
