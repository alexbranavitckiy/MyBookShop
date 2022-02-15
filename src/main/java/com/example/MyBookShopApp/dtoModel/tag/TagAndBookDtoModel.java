package com.example.MyBookShopApp.dtoModel.tag;

import com.example.MyBookShopApp.dtoModel.book.BookDtoModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagAndBookDtoModel extends TagDtoModel {

    private List<BookDtoModel> bookDtoModelList;

    private Integer id;

    private String nameTag;
}
