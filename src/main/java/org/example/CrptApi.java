package org.example;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CrptApi {
    private final int requestLimit;
    private final long intervalMillis;
    private long lastRequestTimestamp;
    private final Lock lock = new ReentrantLock();

    public CrptApi(TimeUnit timeUnit, int requestLimit) {
        if (requestLimit <= 0) {
            throw new IllegalArgumentException("Request limit must be positive");
        }
        this.requestLimit = requestLimit;
        this.intervalMillis = timeUnit.toMillis(1);
        this.lastRequestTimestamp = System.currentTimeMillis();
    }

    public void createDocument(String documentJson, String signature) {
        lock.lock();
        try {
            long currentTime = System.currentTimeMillis();
            long timeSinceLastRequest = currentTime - lastRequestTimestamp;

            if (timeSinceLastRequest < intervalMillis) {
                try {
                    Thread.sleep(intervalMillis - timeSinceLastRequest);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("Sending API request with JSON: " + documentJson);
            System.out.println("Signature: " + signature);

            lastRequestTimestamp = System.currentTimeMillis();
        } finally {
            lock.unlock();
        }
    }


}
