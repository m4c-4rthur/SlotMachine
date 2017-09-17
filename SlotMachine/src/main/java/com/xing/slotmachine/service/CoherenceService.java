package com.xing.slotmachine.service;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class CoherenceService {
	
	public void testCoherence()
	{
            
       //     CacheFactory.ensureCluster();
		// create or get a named cache called mycache 
	       NamedCache myCache = CacheFactory.getCache("mycache");
	       
	       // put key, value pair into the cache. 
	       for(int i = 0 ; i< 100000 ; i++)
	       {
	    	   Long current = System.currentTimeMillis();
	    	   myCache.put("Name"+i,"Gene Smith"+i);
	    	   System.out.println("Putting time = " + (System.currentTimeMillis()-current));
	       }
	       for(int i = 0 ; i< 100000 ; i++)
	       {
	    	   Long current = System.currentTimeMillis();
	    	   String value = (String) myCache.get("Name"+i);
	    	   System.out.println("Value in cache is " + value + "Getting time from cache is " + (System.currentTimeMillis()-current));
	       }
               
          //     CacheFactory.shutdown();

	}
	

}
