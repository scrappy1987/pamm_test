package controllers;

/**
 * Created by a560832 on 20/11/2015.
 */
public abstract class EndpointTestCase implements Runnable {

    public void run() {
        test();
    }

    protected abstract void test();
}
