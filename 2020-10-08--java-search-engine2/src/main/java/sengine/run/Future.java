package sengine.run;

import java.util.concurrent.atomic.AtomicReference;

public class Future<T> {
    private final AtomicReference<T> sharedRef;

    public Future(AtomicReference<T> _sharedRef) {
        sharedRef = _sharedRef;
    }

    public boolean ready() {
        return sharedRef.get() != null;
    }

    public Future<T> waitUntilReady(long waitInMs) {
        while (!ready()) {
            try {
                Thread.sleep(waitInMs);
            } catch (InterruptedException ignore) {
            }
        }
        return this;
    }

    public Future<T> waitUntilReady() {
        return waitUntilReady(10);

    }

    public Future<T> waitUntilReadyOrTimeout(long timeoutInMs, long waitInMs) {
        while (!ready()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ignore) {
            }

            timeoutInMs -= waitInMs;
            if (timeoutInMs <= 0) {
                break;
            }
        }
        return this;
    }

    public Future<T> waitUntilReadyOrTimeout(long timeoutInMs) {
        return waitUntilReadyOrTimeout(timeoutInMs, 10);

    }

    public T getOr(T or) {
        if (ready()) {
            return sharedRef.get();
        } else {
            return or;
        }
    }

    public T getOrNull() {
        return getOr(null);
    }
}
