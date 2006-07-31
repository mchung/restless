
package com.chungco.rest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.chungco.rest.exception.RestCommandException;

// Testing various ways of executing a command
public class RemoteConnection {

    /**
     * @param <R>
     * @param pCommand
     * @return
     * @throws InterruptedException
     * @throws RestCommandException
     */
    public <R extends IRestResult> R execute(final IRestCommand<R> pCommand) throws InterruptedException, RestCommandException {

        return pCommand.execute();
    }

    public <R extends IRestResult> R call(final IRestCommand<R> pCommand) throws InterruptedException, RestCommandException {

        final CallableCommand<R> cc = new CallableCommand<R>(pCommand);
        try {
            return cc.call();
        } catch (Exception e) {
            throw new RestCommandException(e.getCause());
        }

    }

    public <R extends IRestResult> R submit(final IRestCommand<R> pCommand) throws InterruptedException, RestCommandException {

        final CallableCommand<R> cc = new CallableCommand<R>(pCommand);
        final Future<R> future = Executors.newSingleThreadExecutor().submit(cc);
        try {
            return future.get();
        } catch (final InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw ie;
        } catch (final ExecutionException e) {
            throw new RestCommandException(e.getCause());
        }

    }

    public <R extends IRestResult> Future<R> future(final IRestCommand<R> pCommand) throws InterruptedException, RestCommandException {

        final CallableCommand<R> cc = new CallableCommand<R>(pCommand);
        final Future<R> future = Executors.newSingleThreadExecutor().submit(cc);
        return future;
    }

}
