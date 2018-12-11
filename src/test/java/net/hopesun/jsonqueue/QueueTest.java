package net.hopesun.jsonqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author ahmad3ttallah
 */
public class QueueTest {
    private static ObjectMapper objectMapper;
    
    @BeforeClass
    public static void setUpClass() {
        objectMapper = new ObjectMapper();
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
        
    }
    
    @Test
    public void test() {
        try {
            Queue<DataHolder> queue = getQueue();
            String queueString = objectMapper.writeValueAsString(queue);
            String firstItem = "\"0\":{\"data\":1}".trim();
            Assert.assertTrue(queueString.trim().contains(firstItem));
            int val = queue.pop().data;
            Assert.assertEquals(1, val);
            queueString = objectMapper.writeValueAsString(queue);
            Assert.assertFalse(queueString.trim().contains(firstItem));
            val = queue.pop().data;
            Assert.assertEquals(2, val);
            queueString = objectMapper.writeValueAsString(queue);
            firstItem = "\"2\":{\"data\":3}".trim();
            Assert.assertTrue(queueString.trim().contains(firstItem));
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
            // There is an issue then the test should not pass
            Assert.assertTrue(false);
        }
    }
    
    @Test
    public void testParsing() {
        try {
            Queue<DataHolder> queue
                        = Queue.parseQueue(
                                    "{\"queueContent\":{\"0\":{\"data\":1},\"1\":{\"data\":2},\"2\":{\"data\":3},\"3\":{\"data\":4},\"4\":{\"data\":5},\"5\":{\"data\":6},\"6\":{\"data\":7},\"7\":{\"data\":8},\"8\":{\"data\":9},\"9\":{\"data\":10}},\"front\":0,\"rear\":10}", 
                                    new TypeReference<Queue<DataHolder>>() {});
            int val = queue.pop().data;
            Assert.assertEquals(1, val);
            val = queue.pop().data;
            Assert.assertEquals(2, val);
        } catch (IOException ex) {
            ex.printStackTrace();
            // There is an issue then the test should not pass
            Assert.assertTrue(false);
        }
    }
    
    /**
     * This a queue of DataHolder objects which holds integer values from 1 to 10
     * @return 
     */
    public static Queue<DataHolder> getQueue() {
        Queue<DataHolder> queue = new Queue<>();
        for (int i = 1; i < 11; i++) {
            queue.push(new DataHolder(i));
        }
        return queue;
    }
    
    public static class DataHolder {
        private int data;

        public DataHolder() {}
        
        public DataHolder(int data) {
            this.data = data;
        }
        
        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return data + "";
        }
    }
}
