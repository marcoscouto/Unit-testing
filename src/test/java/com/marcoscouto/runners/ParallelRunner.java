package com.marcoscouto.runners;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerScheduler;
import org.junit.runners.model.TestClass;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParallelRunner extends BlockJUnit4ClassRunner {

    public ParallelRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
        setScheduler(new ThreadPoll());
    }

    protected ParallelRunner(TestClass testClass) throws InitializationError {
        super(testClass);
    }

    private static class ThreadPoll implements RunnerScheduler {

        private ExecutorService executorService;

        public ThreadPoll() {
            this.executorService = Executors.newFixedThreadPool(2);
        }

        @Override
        public void schedule(Runnable run) {
            executorService.submit(run);
        }

        @Override
        public void finished() {
            executorService.shutdown();
            try {
                executorService.awaitTermination(10, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}
