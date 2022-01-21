package com.example.MyBookShopApp.controllers;


import com.example.MyBookShopApp.data.Dto.SearchWordDto;
import com.example.MyBookShopApp.data.Dto.TreeGenreDto;
import com.example.MyBookShopApp.data.genre.GenreEntity;
import com.example.MyBookShopApp.data.genre.TreeGenre;
import com.example.MyBookShopApp.services.Impl.GenreServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class GenreController {


    @Autowired
    private GenreServicesImpl genreServices;

    //   @ApiOperation (value = "Список древовидной структуры")
    //  @GetMapping("/list")
    //  public ResponseEntity listUser() {
    //    TreeNode node = new TreeNode();
    //    List<Test2> treeList = test2Service.getTreeList();
    //    if (treeList.size() > 0) {
    //      for (Test2 test : treeList) {
    //                 // Инициализировать
    //        TreeNode tn = new TreeNode(test.getId(), test.getParentid(), test.getId(), test.getName());
    //        node.add(tn);
    //      }
    //    }
    //    return new ResponseEntity(PublicConstant.SUCCESS_CODE, PublicConstant.SUCCESS_MSG,
    //        node.getChildren());


    @ModelAttribute("tagList")
    public List<TreeGenre> getGenreList() {
        List<TreeGenreDto> list2 = this.genreServices.getAllGenreDto();
        TreeGenre node = new TreeGenre();
        if (list2.size() > 0) {
            for (TreeGenreDto test : list2) {
                TreeGenre tn = new TreeGenre(test.getIdTag(), test.getParentId(), test.getSlug(), test.getName());
                node.add(tn);
            }
        }
        System.out.println(node.getChildren());
        return node.getChildren();
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @ModelAttribute("sizeSearch")
    public String sizeList() {
        return "";
    }

    @GetMapping("/genres/index")
    public String recentBookPage() {
        return "genres/index";
    }


}
