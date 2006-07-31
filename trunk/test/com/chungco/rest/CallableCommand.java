package com.chungco.rest;

import java.util.concurrent.Callable;

public class CallableCommand<R extends IRestResult> implements Callable<R> {

    private final IRestCommand<R> mCommand;

    public CallableCommand(final IRestCommand<R> pCommand) {

        mCommand = pCommand;

        // final Callable<R> call = new Callable<R>() {
        //
        // public R call() throws Exception {
        //
        // return pCommand.execute();
        // };
        // };
        //
        // mFuture = new FutureTask<R>(call);
    }

    public R call() throws Exception {

        return mCommand.execute();
    }

    // private final FutureTask<R> mFuture;
    //
    //
    // public void run() {
    //
    // mFuture.run();
    // }
    //
    // public R execute() throws InterruptedException, RestCommandException {
    //
    // try {
    // return mFuture.get();
    // } catch (final InterruptedException ie) {
    // Thread.currentThread().interrupt();
    // throw ie;
    // } catch (final ExecutionException e) {
    // throw new RestCommandException(e.getCause());
    // }
    // }
    //
//     public boolean cancel() {
//
//        return mCommand.cancel(true);
//    }

}
