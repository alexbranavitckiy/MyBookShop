package com.example.MyBookShopApp.services.Impl;

import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.data.other.Tag;
import com.example.MyBookShopApp.erss.EmptySearchExceprtion;
import com.example.MyBookShopApp.repository.BookRepository;
import com.example.MyBookShopApp.repository.TagRepository;
import com.example.MyBookShopApp.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagServicesImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Page<Book> getPageOfTagSortBooks(Integer offset, Integer limit, Tag tag, String nameSort) {
        Pageable nextPage = PageRequest.of(offset, limit, Sort.by(Sort.Direction.DESC, nameSort));
        return bookRepository.findBookByTagsContaining(tag, nextPage);
    }

    public Page<Book> getPageOfTagIdSortBooks(Integer offset, Integer limit, Tag tag, String nameSort) {
        Pageable nextPage = PageRequest.of(offset, limit, Sort.by(Sort.Direction.DESC, nameSort));
        return bookRepository.findBookByTagsContaining(tag, nextPage);
    }


    @Override
    public Tag findTagById(int id) throws EmptySearchExceprtion {
        Optional<Tag> tagOptional = tagRepository.getTagById(id);
        if (tagOptional.isPresent()) {
            return tagOptional.get();
        }else throw new EmptySearchExceprtion("Not found id_tag...");
    }

    @Override
    public Optional<List<Tag>> findAllTag() {//work
        return Optional.of(tagRepository.findAll());
    }

    @Override
    public List<Tag> findAllTagsAndSortSizeDesc() {
        return tagRepository.getTagsOrderByDesc();
    }

    @Override
    public List<Tag> findAllTags() {
        return tagRepository.findAll();
    }


    @Override
    public Optional<List<Tag>> findTagByBook(Book book) {

        //findBookByTagsContaining

        return Optional.empty();
    }

    @Override
    public boolean saveTag(Tag tag) {
        return false;
    }

    @Override
    public boolean saveTagAndSubscribeBook(Tag tag, Integer bookId) {
        return false;
    }

    @Override
    public boolean removeTagByName(String name) {
        return false;
    }

    @Override
    public boolean removeTagById(Integer id) {
        return false;
    }
}
