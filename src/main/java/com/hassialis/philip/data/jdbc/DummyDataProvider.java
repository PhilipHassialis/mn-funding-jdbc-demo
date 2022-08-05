package com.hassialis.philip.data.jdbc;

import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Singleton
public class DummyDataProvider {

    private final TransactionRepository transactionRepository;
    private static final Logger LOG = LoggerFactory.getLogger(DummyDataProvider.class);

    public DummyDataProvider(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Scheduled(fixedDelay = "1s")
    void generate() {
        transactionRepository.save(new Transaction(UUID.randomUUID().toString(), "SYMBOL", randomValue()));
        LOG.info("Content {}",transactionRepository.findAll().size());
    }

    private BigDecimal randomValue() {
        return BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(1,100));
    }

}
