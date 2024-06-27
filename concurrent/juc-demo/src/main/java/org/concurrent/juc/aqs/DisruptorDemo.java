package org.concurrent.juc.aqs;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Order;

import java.util.concurrent.*;

public class DisruptorDemo {
    public static void main(String[] args) {
        Disruptor<OrderEvent> disruptor = new Disruptor<>(
                new OrderEventFactory(), 1024*1024 ,
                Executors.defaultThreadFactory(),
                ProducerType.MULTI, // 单生产者
                new YieldingWaitStrategy() // 等待策略
                );
        // 如果设置多消费者，消息会被重复消费
        disruptor.handleEventsWith(new OrderEventHandler());
        // WorkHandler 可以使每个消息只被消费一次
//        disruptor.handleEventsWithWorkerPool(new OrderEventHandler(), new OrderEventHandler());
        disruptor.start();
        RingBuffer<OrderEvent> ringBuffer = disruptor.getRingBuffer();
        new Thread(()->{
            OrderEventProducer producer = new OrderEventProducer(ringBuffer);

            for (int i = 0; i < 100; i++) {
                producer.onData(i, "info " + i);
            }
        }, "producer1").start();
        new Thread(()->{
            OrderEventProducer producer = new OrderEventProducer(ringBuffer);

            for (int i = 0; i < 100; i++) {
                producer.onData(i, "info2 " + i);
            }
        }, "producer2").start();

//        disruptor.shutdown();
    }

    static class SimpleThreadFactory implements ThreadFactory {
        public Thread newThread(@NotNull Runnable r) {
            return new Thread(r);
        }
    }
}

class OrderEvent {
    private long value;
    private String name;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class OrderEventFactory implements EventFactory<OrderEvent> {
    @Override
    public OrderEvent newInstance() {
        return new OrderEvent();
    }
}

class OrderEventProducer {
    private RingBuffer<OrderEvent> ringBuffer;
    public OrderEventProducer(RingBuffer<OrderEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(long value, String name) {
        long sequence = ringBuffer.next();
        try{
            OrderEvent orderEvent = ringBuffer.get(sequence);
            orderEvent.setValue(value);
            orderEvent.setName(name);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            System.out.println("生产者发送数据 value: "+value+ ", name:" + name);
            ringBuffer.publish(sequence);
        }
    }
}

class OrderEventHandler implements EventHandler<OrderEvent>, WorkHandler<OrderEvent> {

    @Override
    public void onEvent(OrderEvent orderEvent, long l, boolean b) throws Exception {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("消费者获取数据 value: " + orderEvent.getValue()+ ", name:" + orderEvent.getName());
    }

    @Override
    public void onEvent(OrderEvent orderEvent) throws Exception {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("消费者获取数据 value: " + orderEvent.getValue()+ ", name:" + orderEvent.getName());
    }
}
