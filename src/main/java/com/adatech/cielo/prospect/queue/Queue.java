package com.adatech.cielo.prospect.queue;

public interface Queue<T> {

    T enqueue(T element);
    T dequeue();
    T front();
    T rear();
    boolean isEmpty();
    boolean isFull();

    int size();
}
