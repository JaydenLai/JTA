package com.hz.syxx.other;

import javax.persistence.Column;

/**
 * Created by Pefan_Li
 * Created Time 2018/8/16 21:19.
 */
public class ProducerConsumer {
    private int resource = 0;
    private boolean available = false;

    synchronized void produce(){
        while (available) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        resource++;
        available = true;
        System.out.println("produce, resource left:" + resource);
        notifyAll();
    }

    synchronized void consume(){
        while (!available) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        resource--;
        available = false;
        System.out.println("consume, resource left:" + resource);
        notifyAll();
    }

    public static void main(String[] args) {
        ProducerConsumer producerConsumer = new ProducerConsumer();
        Thread producer = new Thread(new Producer(producerConsumer));
        Thread consumer = new Thread(new Consumer(producerConsumer));
        producer.start();
        consumer.start();
    }

}

class Producer implements Runnable {
    private ProducerConsumer producerConsumer;

    public Producer(ProducerConsumer producerConsumer){
        this.producerConsumer = producerConsumer;
    }

    @Override
    public void run() {
        for(int i = 0; i<20; i++){
            producerConsumer.produce();
        }
    }
}

class Consumer implements Runnable {
    private ProducerConsumer producerConsumer;

    public Consumer(ProducerConsumer producerConsumer){
        this.producerConsumer = producerConsumer;
    }

    @Override
    public void run() {
        for(int i = 0; i<20; i++){
            producerConsumer.consume();
        }
    }
}
