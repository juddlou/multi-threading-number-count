package count;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class CountMain {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<Integer,Integer> map=new ConcurrentHashMap<>();
        int[] arr={1,3,5,1,3,6,8,9,0,3,5,6,7,8,6,4,1,3,5,1,3,6,8,9,0,3,5,6,7,8,6,7,4,1,3,5,1,3,6,8,9,0,3,5,6,7,8,6,4,1,3,5,1,3,6,8,9,0,3,5,6,7,8,6,4};
        CountDownLatch latch=new CountDownLatch(arr.length%8==0?arr.length/8:arr.length/8+1);
        MyThread myThread=null;
        for(int i=0;i<arr.length;i+=8){
            if(i+8<arr.length){
                myThread=new MyThread(i,i+8,latch,map,arr);
            }else{
                myThread=new MyThread(i,arr.length,latch,map,arr);
            }
            new Thread(myThread).start();
        }
        latch.await();
        System.out.println(map);
    }
}
