package com.example.MyBookShopApp.services.Impl;

import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.data.other.Statistics;
import com.example.MyBookShopApp.dtoModel.book.BookDtoModel;
import com.example.MyBookShopApp.dtoModel.convector.StatisticsConvectorImpl;
import com.example.MyBookShopApp.erss.EmptySearchExceprtion;
import com.example.MyBookShopApp.repository.BookRepository;
import com.example.MyBookShopApp.repository.StatisticsRepository;
import com.example.MyBookShopApp.services.BookService;
import com.example.MyBookShopApp.services.MappingService;
import com.example.MyBookShopApp.services.StatisticsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class StatisticServiceImpl implements StatisticsServices {

    private final StatisticsRepository statisticsRepository;
    private final MappingService mappingService;
    private final BookService bookService;
    private final BookRepository bookRepository;
    private final StatisticsConvectorImpl statisticsConvector;

    @Autowired
    private StatisticServiceImpl(StatisticsConvectorImpl statisticsConvector, BookRepository bookRepository, BookService bookService, MappingServiceImpl mappingService, StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
        this.statisticsConvector = statisticsConvector;
        this.bookRepository = bookRepository;
        this.bookService = bookService;
        this.mappingService = mappingService;
    }

    @Override
    public List<BookDtoModel> getPageOfNameSortStatisticsBooksByPopularAndMapping(Integer offset, Integer limit, String nameSort) {
        Pageable nextPage = PageRequest.of(offset, limit, Sort.by(Sort.Direction.DESC, nameSort));
        return statisticsRepository.findAll(nextPage).getContent().stream().map(statisticsConvector::convertToDtoWithBook).collect(Collectors.toList()).stream().map(x -> x.getBook()).collect(Collectors.toList());
    }


    @Override
    public boolean addStatisticsAverageValue(int averageValue, String slugBook) {
        Optional<Book> optionalStatistics = bookRepository.findBookBySlug(slugBook);
        if (optionalStatistics.isPresent() && averageValue > 0 && averageValue < 6) {
            optionalStatistics.get().getStatistics().countAverage(averageValue);
            statisticsRepository.save(setStatisticsHistory(averageValue, optionalStatistics.get().getStatistics()));
            return true;
        }
        return false;
    }

    @Override
    public Optional<Statistics> getStatistics(String slug) {
        return statisticsRepository.getStatisticsBySlug(slug);
    }

    @Override
    public boolean addStatisticsAverageValue(int averageValue, Optional<Statistics> statistics) {
        if (statistics.isPresent() && averageValue > 0 && averageValue < 6) {
            statistics.get().countAverage(averageValue);
            return statisticsRepository.save(statistics.get()) == statisticsRepository.save(statistics.get());
        }
        return false;
    }

    @Override
    public Double getStatisticsAverageBySlug(String slug) throws EmptySearchExceprtion {
        Optional<Statistics> optionalStatistics = statisticsRepository.getStatisticsBySlug(slug);
        if (optionalStatistics.isPresent()) {
            return optionalStatistics.get().getAverageValue();
        } else throw new EmptySearchExceprtion("Value return error getStatisticsAverageBySlug");
    }

    @Override
    public boolean clearStatisticsAverageBySlug(String slug) {
        Optional<Statistics> optionalStatistics = statisticsRepository.getStatisticsBySlug(slug);
        if (optionalStatistics.isPresent()) {
            optionalStatistics.get().setAverageValue(0.0);
            return statisticsRepository.save(optionalStatistics.get()) == statisticsRepository.save(optionalStatistics.get());
        }
        return false;
    }

    @Override
    public Statistics setStatisticsHistory(int x, Statistics statistics) {
        switch (x) {
            case 1: {
                statistics.setOnePlus();
                break;
            }
            case 2: {
                statistics.setTwoPlus();
                break;
            }
            case 3: {
                statistics.setThreePlus();
                break;
            }
            case 4: {
                statistics.setFourPlus();
                break;
            }
            case 5: {
                statistics.setFivePlus();
                break;
            }

        }
        return  statistics;
    }

}
