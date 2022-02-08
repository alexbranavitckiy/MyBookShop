package com.example.MyBookShopApp.controllers;


import com.example.MyBookShopApp.data.Dto.TagDto;
import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.data.Dto.BooksPageDto;
import com.example.MyBookShopApp.data.Dto.SearchWordDto;
import com.example.MyBookShopApp.data.other.Tag;
import com.example.MyBookShopApp.erss.EmptySearchExceprtion;
import com.example.MyBookShopApp.myAnnotations.GlobalData;
import com.example.MyBookShopApp.services.MappingService;
import com.example.MyBookShopApp.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@GlobalData
public class TagController {

    private final TagService tagService;
    private final MappingService mappingService;

    @Autowired
    public TagController(MappingService mappingService, TagService tagService) {
        this.mappingService = mappingService;
        this.tagService = tagService;
    }

    @GetMapping(value = {"/api/books/tag/"})
    public String tagsPage(@RequestParam(required = false, value = "ID") Integer ID, Model model) throws EmptySearchExceprtion {
        if (ID != null) {
            Tag tag = tagService.findTagById(ID);
            model.addAttribute("category", tag.getNameTag());
            model.addAttribute("tagBooks", tagService.getPageOfTagSortBooks(0, 10, tag, "pubDate").getContent());
            model.addAttribute("ID", ID);
            return "tags/index";
        } else throw new EmptySearchExceprtion("Not null id tag");
    }

    @GetMapping(value = {"/api/books/tag{ID}"})
    @ResponseBody
    public BooksPageDto tagsPageRest(@RequestParam(required = false, value = "offset") Integer offset,
                                     @RequestParam(required = false, value = "limit") Integer limit,
                                     @PathVariable(value = "ID", required = false) Integer IDPage, Model model) throws EmptySearchExceprtion {
        if (offset < 0) return new BooksPageDto();
        List<Book> bookPage = tagService.getPageOfTagSortBooks(offset, limit, tagService.findTagById(IDPage), "pubDate").getContent();
        model.addAttribute("ID", IDPage);
        model.addAttribute("tagBooks", bookPage);
        return new BooksPageDto(bookPage);
    }

    @ModelAttribute("tagList")
    public List<TagDto> getTagListDesc() {
        return  this.mappingService.mapToListTagDto(this.tagService.findAllTagsAndSortSizeDesc());
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @ModelAttribute("sizeSearch")
    public String sizeList() {
        return "";
    }

    @ModelAttribute("flagButton")
    public boolean flagButton() {
        return true;
    }

    @ModelAttribute("tagBooks")
    public List<Book> tagBooks() {
        return null;
    }

    @ModelAttribute("allTags")
    public boolean allTags() {
        return false;
    }

    @ModelAttribute("category")
    public String categoryTag() {
        return "category";
    }
}
