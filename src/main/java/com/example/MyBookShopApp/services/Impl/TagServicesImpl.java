package com.example.MyBookShopApp.services.Impl;

import com.example.MyBookShopApp.data.other.Tag;
import com.example.MyBookShopApp.dtoModel.book.BookDtoModel;
import com.example.MyBookShopApp.dtoModel.convector.BookСonvectorImpl;
import com.example.MyBookShopApp.dtoModel.convector.TagConvectorImpl;
import com.example.MyBookShopApp.dtoModel.tag.TagDtoModel;
import com.example.MyBookShopApp.erss.EmptySearchExceprtion;
import com.example.MyBookShopApp.repository.BookRepository;
import com.example.MyBookShopApp.repository.TagRepository;
import com.example.MyBookShopApp.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TagServicesImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    BookСonvectorImpl bookConvertor;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TagConvectorImpl tagConvector;

    @Override
    public List<BookDtoModel> getPageOfTagSortBooks(Integer offset, Integer limit, TagDtoModel tag, String nameSort) {
        Pageable nextPage = PageRequest.of(offset, limit, Sort.by(Sort.Direction.DESC, nameSort));
        return bookRepository.findBookByTagsContaining(tagConvector.toEntity(tag), nextPage).getContent().stream().map(bookConvertor::convertToDto).collect(Collectors.toList());
    }


    @Override
    public TagDtoModel findTagById(int id) throws EmptySearchExceprtion {
        Optional<Tag> tagOptional = tagRepository.getTagById(id);
        if (tagOptional.isPresent()) {
            return tagConvector.toDto(tagOptional.get());
        } else throw new EmptySearchExceprtion("Not found id_tag...");
    }

    @Override
    public Optional<List<Tag>> findAllTag() {//work
        return Optional.of(tagRepository.findAll());
    }

    @Override
    public List<TagDtoModel> findAllTagsAndSortSizeDesc() {
        return tagRepository.getTagsOrderByDesc().stream().map(tagConvector::toDto).collect((Collectors.toList()));
    }

    @Override
    public List<Tag> findAllTags() {
        return tagRepository.findAll();
    }


}
