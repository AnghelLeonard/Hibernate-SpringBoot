package com.bookstore.service;

import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import com.bookstore.strategy.Delivery;

@Service
public class DeliverService implements Deliverable {

    private final BookRepository bookRepository;
    private final List<Delivery> deliverStrategies;

    private final Map<Class<? extends Book>, Delivery> deliverStrategiesMap = new HashMap<>();

    public DeliverService(BookRepository bookRepository, List<Delivery> deliverStrategies) {
        this.bookRepository = bookRepository;
        this.deliverStrategies = deliverStrategies;
    }

    @PostConstruct
    public void init() {
        deliverStrategies.forEach((deliverStrategy) -> {
            deliverStrategiesMap.put(deliverStrategy.ofBook(), deliverStrategy);
        });
    }

    @Override
    public void process() {

        List<Book> allBooks = bookRepository.findAll();

        for (Book book : allBooks) {
            Delivery deliveryStrategy = deliverStrategiesMap.get(book.getClass());
            deliveryStrategy.deliver(book);
        }
    }
}
