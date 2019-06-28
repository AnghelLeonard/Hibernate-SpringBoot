package com.app.service;

import com.app.repository.BatchRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
public class BatchService {

    private final BatchRepository batchRepository;

    public BatchService(BatchRepository batchRepository) {
        this.batchRepository = batchRepository;
    }

    private static final Logger logger = Logger.getLogger(BatchService.class.getName());

    public void batchFile(String fileName) throws IOException {

        List<String> lines = Files.readAllLines(Path.of(fileName));

        logger.info("Start inserting ...");
        StopWatch watch = new StopWatch();
        watch.start();

        batchRepository.batch(lines);

        watch.stop();

        logger.info(() -> "Stop inserting. \n Total time: {0} ms ({1} s)"
                + watch.getTotalTimeMillis() + ", " + watch.getTotalTimeSeconds());
    }

}
