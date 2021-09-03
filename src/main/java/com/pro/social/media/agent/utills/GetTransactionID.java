package com.pro.social.media.agent.utills;

import java.util.concurrent.atomic.AtomicInteger;

public class GetTransactionID {
	private static final AtomicInteger counter = new AtomicInteger(0);

    public static int getTransactionID(){
        return counter.incrementAndGet();
    }
}
