package com.yayiabc.http.mvc.expand;
import redis.clients.jedis.JedisPubSub;  
  
/**  
 * Created by denglinjie on 2017/8/28.
 * 类  
 */  
public class KeyExpiredListener extends JedisPubSub {

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        System.out.println("onPSubscribe "
                + pattern + " " + subscribedChannels);
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {
         //onPMessage pattern __key*__:* __keyevent@0__:expired notify
        System.out.println("onPMessage pattern "
                        + pattern + " " + channel + " " + message);
    }

    
    
	@Override
	public void onMessage(String arg0, String arg1) {
	}
	@Override
	public void onPUnsubscribe(String arg0, int arg1) {
	}
	@Override
	public void onSubscribe(String arg0, int arg1) {
	}
	@Override
	public void onUnsubscribe(String arg0, int arg1) {
	}
}