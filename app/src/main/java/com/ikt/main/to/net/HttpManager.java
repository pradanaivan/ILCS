package com.ikt.main.to.net;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HttpManager {
    private static int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    // Sets the amount of time an idle thread waits before terminating
    private static final int KEEP_ALIVE_TIME = 1;
    // Sets the Time Unit to seconds
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
    // A queue of Runnables
    private final BlockingQueue<Runnable> mHttpWorkQueue;
    private ThreadPoolExecutor mHttpRequestThreadPool;

    public static final int READ_TIME_OUT = 30000;
    public static final int CONNECT_TIME_OUT = 40000;

    private static HttpManager sInstance;

    public static HttpManager getInstance() {
        if (null == sInstance) {
            sInstance = new HttpManager();
        }
        return sInstance;
    }

    private HttpManager() {
        // Instantiates the queue of Runnables as a LinkedBlockingQueue
        mHttpWorkQueue = new LinkedBlockingQueue<Runnable>();

        // Creates a thread pool manager
        mHttpRequestThreadPool = new ThreadPoolExecutor(
            NUMBER_OF_CORES, // Initial pool size
            NUMBER_OF_CORES, // Max pool size
            KEEP_ALIVE_TIME,
            KEEP_ALIVE_TIME_UNIT,
            mHttpWorkQueue);
    }

    public HttpTask doRequest(HttpTask task) {
        // init HTTP Header
//        List<BasicHeader> headers = new ArrayList<BasicHeader>();
        // headers.add(new BasicHeader("Content-Type", "multipart/form-data"));
//        task.setHeaders(headers);

//        task.getPostData().add(new BasicNameValuePair("val", "dev"));
//        task.getPostData().add(new BasicNameValuePair("key", "ZjQAHh877aioc7eKINYdVubQ3J4RACYx"));
//        Log.d("POST", "TASK" + task.getPostData().size());

        mHttpRequestThreadPool.execute(task);
        return task;
    }

    public void cancelAllQueue() {
        HttpTask[] taskArray = new HttpTask[mHttpWorkQueue.size()];
        mHttpWorkQueue.toArray(taskArray);
        int taskArraylen = taskArray.length;
        synchronized (sInstance) {
            for (int taskArrayIndex = 0; taskArrayIndex < taskArraylen; taskArrayIndex++) {
                HttpTask task = taskArray[taskArrayIndex];
                task.cancelTask();
            }
        }
    }

}
