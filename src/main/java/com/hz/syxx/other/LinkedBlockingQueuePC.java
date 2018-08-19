package com.hz.syxx.other;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by Pefan_Li
 * Created Time 2018/8/18 19:57.
 */
class Toast {
    public enum Status {DRY, BUTTERED, JAMMED}

    private Status status;
    private final int id;

    public Toast(int id, Status status) {
        this.id = id;
        this.status = status;
    }

    public void butter() {
        this.status = Status.BUTTERED;
    }

    public void jam() {
        this.status = Status.JAMMED;
    }

    public Status getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Toast, id: " + this.getId() + " status: " + this.getStatus();
    }
}

class Toaster implements Runnable {
    private LinkedBlockingQueue<Toast> dryQueue;
    int count = 0;

    public Toaster(LinkedBlockingQueue<Toast> dryQueue) {
        this.dryQueue = dryQueue;
    }

    @Override
    public void run() {
        /**
         * While loop block must be included into TRY block!!!
         */
        try {
            while (!Thread.interrupted()) {
                TimeUnit.SECONDS.sleep(3);
                Toast toast = new Toast(++count, Toast.Status.DRY);
                System.out.println(toast.toString());
                this.dryQueue.put(toast);
            }
        } catch (InterruptedException e) {
            System.out.println("Toaster interrupt and off duty.");
        }
        System.out.println("Toaster off");

    }
}

class Butter implements Runnable {
    private LinkedBlockingQueue<Toast> dryQueue, butteredQueue;

    public Butter(LinkedBlockingQueue<Toast> dryQueue, LinkedBlockingQueue<Toast> butteredQueue) {
        this.dryQueue = dryQueue;
        this.butteredQueue = butteredQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast toast = this.dryQueue.take();
                toast.butter();
                System.out.println(toast.toString());
                butteredQueue.put(toast);
            }
        } catch (InterruptedException e) {
            System.out.println("Butter interrupt and off duty.");
        }
        System.out.println("Butter off");

    }
}

class Jammer implements Runnable {
    private LinkedBlockingQueue<Toast> butteredQueue, jammedQueue;

    public Jammer(LinkedBlockingQueue<Toast> butteredQueue, LinkedBlockingQueue<Toast> jammedQueue) {
        this.butteredQueue = butteredQueue;
        this.jammedQueue = jammedQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast toast = this.butteredQueue.take();
                toast.jam();
                System.out.println(toast.toString());
                jammedQueue.put(toast);
            }
        } catch (InterruptedException e) {
            System.out.println("Jammer interrupt and off duty.");
        }
        System.out.println("Jammer off");
    }
}

class Customer implements Runnable {
    private LinkedBlockingQueue<Toast> jammedQueue;

    public Customer(LinkedBlockingQueue<Toast> jammedQueue) {
        this.jammedQueue = jammedQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast toast = this.jammedQueue.take();
                System.out.println("Customer buy toast, " + toast.toString());
            }
        } catch (InterruptedException e) {
            System.out.println("Customer interrupt and leave.");
        }
        System.out.println("Customer off");

    }
}

public class LinkedBlockingQueuePC {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<Toast> dryQueue = new LinkedBlockingQueue<Toast>(),
                butteredQueue = new LinkedBlockingQueue<Toast>(),
                jammedQueue = new LinkedBlockingQueue<Toast>();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Toaster(dryQueue));
        executorService.execute(new Butter(dryQueue, butteredQueue));
        executorService.execute(new Jammer(butteredQueue, jammedQueue));
        executorService.execute(new Customer(jammedQueue));
        TimeUnit.SECONDS.sleep(5);
        executorService.shutdown();
        if (executorService.awaitTermination(30, TimeUnit.SECONDS)) {
            System.out.println("Shop Close!");
        } else {
            executorService.shutdownNow();
            System.out.println("Shop Close! Thanks and look forward your coming next time.");
        }

    }
}
