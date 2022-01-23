package com.example.MyBookShopApp.services.Impl;

import com.example.MyBookShopApp.data.Dto.TreeGenreDto;
import com.example.MyBookShopApp.data.genre.GenreEntity;
import com.example.MyBookShopApp.repository.GenreEntityRepository;
import com.example.MyBookShopApp.services.GenreServices;
import com.example.MyBookShopApp.services.MappingService;
import org.modelmapper.internal.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreServicesImpl implements GenreServices {


    @Autowired
    private GenreEntityRepository genreEntityRepository;

    @Autowired
    private MappingService mappingService;

    @Override
    public List<GenreEntity> getAllGenre() {
        return genreEntityRepository.findAll();
    }


    @Override
    public  String getGenreByName(String slug){

        return genreEntityRepository.getBySlug(slug).getName();
    }


    @Override
    public List<GenreEntity> getAllGenreAndSortByBook() {
        return genreEntityRepository.findAll();
    }
    //    public List<TreeGenreDto> generationTreeGenre() {
    //        List<TreeGenreDto> list2 = getAllGenreDtoList();
    //     //   list2.sort(Comparator.comparingInt(TreeGenreDto::getSizeBook));
    //        TreeGenreDto node = new TreeGenreDto();
    //        if (list2.size() > 0) {
    //            for (TreeGenreDto test : list2) {
    //                TreeGenreDto tn = new TreeGenreDto(test.getId(), test.getParentId(), test.getSlug(), test.getName(), test.getSizeBook());
    //                node.add(tn);
    //            }
    //        }
    //        return node.getChildren();
    //    }


    @Override
    public List<TreeGenreDto> getAllGenreDtoList() {
        List<TreeGenreDto> treeGenreDtoDtoList = new ArrayList<>();
        genreEntityRepository.findAllByOrderByListBookDesc().forEach(x -> {
            treeGenreDtoDtoList.add(mappingService.mapToTreeGenreDto(x));
        });
        return treeGenreDtoDtoList;
    }


}
