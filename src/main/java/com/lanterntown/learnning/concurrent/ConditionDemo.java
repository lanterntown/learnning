package com.lanterntown.learnning.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：wangsiting
 * @date ：Created in 2021-01-12 21:26
 * @description：
 * @modified By：
 * @version: $
 */
public class ConditionDemo {
    public static void main(String[] args) {
        Depot depot = new Depot();

        Producer producer = new Producer(depot);
        Customer customer = new Customer(depot);

        producer.produce(10);
        customer.consume(5);
        producer.produce(15);
        customer.consume(10);
        customer.consume(15);
        producer.produce(10);

    }

}

/**
 * 仓库
 *
 */
class Depot {
    /**
     * 仓库大小
     */
    private int depotSize;

    /**
     * 独占锁
     */
    private Lock lock;

    /**
     * 仓库容量
     */
    private int capacity;

    private Condition fullCondition;
    private Condition emptyCondition;

    public Depot(){
        this.depotSize = 0;
        this.lock = new ReentrantLock();
        this.capacity = 15;
        this.fullCondition = lock.newCondition();
        this.emptyCondition = lock.newCondition();
    }

    /**
     * 商品入库
     * @param value 入库商品数量
     */
    public void put(int value){
        lock.lock();
        try {
            //当前需要入库的商品数量
            int left = value;
            while (left>0){
                //库存已满时，“生产者”等待“消费者”消费
                while (depotSize >= capacity){
                    fullCondition.await();
                }
                //获取当前仓库可以入库的商品数量
                int inc = depotSize + left > capacity ? capacity - depotSize : left;
                depotSize += inc;
                left -= inc;
                System.out.println(Thread.currentThread().getName() + "----要入库数量: " + value +";;实际入库数量：" + inc + ";;仓库货物数量：" + depotSize + ";;没有入库数量：" + left);

                //入库新的商品了之后，通知消费者可以消费了
                emptyCondition.signal();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            lock.unlock();
        }
    }

    /**
     * 商品出库
     * @param value 需要出库的商品数量
     */
    public void get(int value){
        lock.lock();

        try {
            int left = value;
            while (left>0){
                //仓库里没有商品了，消费者等待生产者生产
                while (depotSize<=0){
                    emptyCondition.await();
                }
                //获取可以出库的商品数量
                int dec = depotSize < left? depotSize : left;
                depotSize -= dec;
                left -= dec;
                System.out.println(Thread.currentThread().getName() + "----要消费的数量：" + value +";;实际消费的数量: " + dec + ";;仓库现存数量：" + depotSize + ";;有多少件商品没有消费：" + left);

                //告诉生产者可以生产了
                fullCondition.signal();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            lock.unlock();
        }
    }
}

/**
 * 生产者
 */
class Producer {
    private Depot depot;

    public Producer(Depot depot){
        this.depot = depot;
    }

    public void produce(final int value){
        new Thread(){
            @Override
            public void run(){
                depot.put(value);
            }
        }.start();
    }
}

/**
 * 消费者
 */
class Customer {
    private Depot depot;

    public Customer(Depot depot){
        this.depot = depot;
    }

    public void consume(final int value){
        new Thread(){
            public void run(){
                depot.get(value);
            }
        }.start();
    }
}
