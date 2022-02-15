package com.example.MyBookShopApp.dtoModel;



public interface MapperDto<T, D> {

    D toEntity(T dto);

    T toDto(D entity);


}
