package net.hopesun.jsonqueue;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ahmad3ttallah
 * @param <T>
 */
public class Queue<T> {
    private Map<Integer, T> queueContent;
    private int front;
    private int rear;
    
    public Queue<T> push(T data) {
        if(data == null)
            throw new NullPointerException("Queue des not accept null values");
        getQueueContent().put(rear, data);
        rear++;
        return this;
    }
    
    public T pop() {
        T temp = getQueueContent().get(front);
        
        getQueueContent().remove(front);
        if(getQueueContent().size() > 0)
            front++;
        else
            front = rear = 0;
        return temp;
    }

    public static <T> Queue<T> from(T... items) {
        Queue<T> queue = new Queue<>();
        for (T item : items)
            queue.push(item);
        return queue;
    }
    
    public static <T> Queue<T> parseQueue(String JSON, TypeReference<Queue<T>> reference) throws IOException {
        Queue<T> queue = new ObjectMapper().readValue(JSON, reference);
        return queue;
    }
    
    public Map<Integer, T> getQueueContent() {
        if(queueContent == null)
            queueContent = new HashMap<>();
        return queueContent;
    }

    public Integer getFront() {
        return front;
    }

    public void setFront(Integer front) {
        this.front = front;
    }

    public int getRear() {
        return rear;
    }

    public void setRear(int rear) {
        this.rear = rear;
    }
}
