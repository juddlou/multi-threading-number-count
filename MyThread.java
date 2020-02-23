package count;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class MyThread implements Runnable {
    private int startIndex;
    private int endIndex;
    private CountDownLatch latch;
    private ConcurrentHashMap<Integer,Integer> map;
    private int[] arr;
    private Map<Integer,Integer> count=new HashMap<>();

    public MyThread(int startIndex, int endIndex, CountDownLatch latch, ConcurrentHashMap<Integer, Integer> map, int[] arr) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.latch = latch;
        this.map = map;
        this.arr = arr;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i=startIndex;i<endIndex;i++){
            if(count.get(arr[i])==null){
                count.put(arr[i],1);
            }else{
                count.put(arr[i],count.get(arr[i])+1);
            }
        }
        synchronized (MyThread.class){
            for(Map.Entry<Integer,Integer> tmp:count.entrySet()){
                if(map.get(tmp.getKey())==null){
                    map.put(tmp.getKey(),tmp.getValue());
                }else{
                    map.put(tmp.getKey(),map.get(tmp.getKey())+tmp.getValue());
                }
            }
        }
        latch.countDown();
    }
}
