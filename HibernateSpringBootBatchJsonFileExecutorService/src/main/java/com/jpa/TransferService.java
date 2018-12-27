package com.jpa;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
public class TransferService {

    private static final int THRESHOLD = 19200; // 300 inserts in a batch * 64    

    @Autowired
    private ApplicationContext applicationContext;

    // you can also use, Runtime.getRuntime().availableProcessors()
    private static final ExecutorService executor
            = Executors.newFixedThreadPool(4);

    private static final Logger logger = Logger.getLogger(TransferService.class.getName());

    public void transferFile(String fileName) {

        String thisLine;
        List<String> lines = new ArrayList<>(THRESHOLD);

        logger.info("Start inserting ...");        

        StopWatch watch = new StopWatch();
        watch.start();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((thisLine = br.readLine()) != null && !thisLine.trim().isEmpty()) {

                lines.add(thisLine);

                if (lines.size() == THRESHOLD) {
                    executing(lines);
                    lines.clear();
                }
            }

            if (!lines.isEmpty()) {
                executing(lines);
                lines.clear();
            }
        } catch (FileNotFoundException ex) {
            logger.log(Level.SEVERE, "File not found exception", ex);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "IO exception", ex);
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        watch.stop();

        logger.log(Level.INFO, "Stop inserting. \n Total time: {0} ms ({1} s)",
                new Object[]{watch.getTotalTimeMillis(), watch.getTotalTimeSeconds()});
    }

    private void executing(List<String> lines) {
        BatchRepository daoThread
                = applicationContext.getBean(BatchRepository.class, new ArrayList<>(lines));
        executor.execute(daoThread);
    }
}
