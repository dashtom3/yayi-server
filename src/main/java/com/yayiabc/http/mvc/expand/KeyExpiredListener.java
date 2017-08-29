package com.yayiabc.http.mvc.expand;
import redis.clients.jedis.JedisPubSub;  
  
/**  
 * Created by denglinjie on 2017/8/28.
 * 类  
    /**  
     * Created by denglinjie  
     */  
    public class KeyExpiredListener extends JedisPubSub {  
        @Override  
        public void unsubscribe() {  
        	System.out.println(1);
            super.unsubscribe();  
        }  
      
        @Override  
        public void unsubscribe(String... channels) {  
        	System.out.println(2);
            super.unsubscribe(channels);  
        }  
      
        @Override  
        public void subscribe(String... channels) {  
        	System.out.println(3);
            super.subscribe(channels);  
        }  
      
        @Override  
        public void psubscribe(String... patterns) { 
        	System.out.println(4);
            super.psubscribe(patterns);  
        }  
      
        @Override  
        public void punsubscribe() { 
        	System.out.println(5);
            super.punsubscribe();  
        }  
      
        @Override  
        public void punsubscribe(String... patterns) {  
        	System.out.println(6);
            super.punsubscribe(patterns);  
        }  
      
        @Override  
        public void onMessage(String channel, String message) {  
        	System.out.println(7);
            System.out.println("channel:" + channel + "receives message :" + message);  
            
           // this.unsubscribe();  
        }  
      
        @Override  
        public void onPMessage(String pattern, String channel, String message) {  
        	System.out.println(8);
        	System.out.println("onPMessage pattern "
                    + pattern + " " + channel + " " + message);
        	// this.unsubscribe();
        }  
      
        @Override  
        public void onSubscribe(String channel, int subscribedChannels) {  
        	System.out.println(9);
            System.out.println("channel:" + channel + "is been subscribed:" + subscribedChannels);  
        }  
      
        @Override  
        public void onPUnsubscribe(String pattern, int subscribedChannels) {  
        	System.out.println(10);
        }  
      
        @Override  
        public void onPSubscribe(String pattern, int subscribedChannels) {  
        	System.out.println(11);
        	System.out.println("onPSubscribe "
                    + pattern + " " + subscribedChannels);
        }  
      
        @Override  
        public void onUnsubscribe(String channel, int subscribedChannels) {  
        	System.out.println(12);
            System.out.println("channel:" + channel + "is been unsubscribed:" + subscribedChannels);  
        }
}
