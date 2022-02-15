package com.example.MyBookShopApp.dtoModel.convector;


import com.example.MyBookShopApp.data.other.Statistics;
import com.example.MyBookShopApp.dtoModel.MapperDto;
import com.example.MyBookShopApp.dtoModel.convector.BookСonvectorImpl;
import com.example.MyBookShopApp.dtoModel.other.StatisticsDtoModel;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class StatisticsConvectorImpl implements MapperDto<StatisticsDtoModel, Statistics> {


    private final ModelMapper mapper;
    private final BookСonvectorImpl bookСonvector;

    @Autowired
    private StatisticsConvectorImpl(ModelMapper mapper,  BookСonvectorImpl bookСonvector) {
        this.mapper = mapper;
        this.bookСonvector = bookСonvector;

    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Statistics.class, StatisticsDtoModel.class)
                .addMappings(m -> m.skip(StatisticsDtoModel::setBook)).setPostConverter(toStatisticsDtoConverterWithBooksDto());
    }


    public Converter<Statistics, StatisticsDtoModel> toStatisticsDtoConverterWithBooksDto() {
        return context -> {
            Statistics source = context.getSource();
            StatisticsDtoModel destination = context.getDestination();
            mapSpecificFieldsStatisticsWithBooksDto(source, destination);
            return context.getDestination();
        };
    }

    public void mapSpecificFieldsStatisticsWithBooksDto(Statistics source, StatisticsDtoModel destination) {
        destination.setBook(Objects.isNull(source) || Objects.isNull(source.getBook()) ? null : bookСonvector.toDto(source.getBook()));
    }



    @Override
    public Statistics toEntity(StatisticsDtoModel dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Statistics.class);
    }

    @Override
    public StatisticsDtoModel toDto(Statistics entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, StatisticsDtoModel.class);
    }


    public StatisticsDtoModel convertToDtoWithBook(Statistics entity) {
        return mapper.map(entity, StatisticsDtoModel.class);
    }


}
