package com.example.MyBookShopApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.mappers.ModelMapper;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


@Configuration
public class MappingConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static <R, E> List<R> convertList(List<E> list, Function<E, R> converter) {
        return list.stream().map(converter).collect(Collectors.toList());
    }










}
