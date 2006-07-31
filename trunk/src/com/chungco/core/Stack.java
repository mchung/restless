
package com.chungco.core;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/**
 * A Stack. What do they say again, favor composition over inheritance?
 * 
 * @author Marc Chung <mchung@gmail.com>
 * @param <X>
 *            A type
 */
public class Stack<X> implements Cloneable {

    final List<X> mStk;

    public Stack() {

        mStk = new ArrayList<X>();
    }

    public Stack<X> push(final X... xs) {

        for (final X x : xs) {
            mStk.add(x);
        }
        return this;
    }

    public Stack<X> push(final X x) {

        mStk.add(x);
        return this;
    }

    public X peek() {

        int len = mStk.size();

        if (len == 0)
            throw new EmptyStackException();
        return mStk.get(len - 1);
    }

    public boolean empty() {

        return mStk.size() == 0;
    }

    public X pop() {

        int len = mStk.size();

        X obj = peek();
        mStk.remove(len - 1);

        return obj;
    }

    public int size() {

        return mStk.size();
    }

    @Override
    public Stack<X> clone() {

        final Stack<X> clone = new Stack<X>();

        // Start at list.0 and move to list.length
        for (X x : mStk) {
            clone.push(x);
        }
        return clone;
    }

    /**
     * CDR() will return a copy of the stack with every item but the head.
     * <code>
     * If your stack grows to the right:
     * [x, y, z, a, b|.cdr will return [x, y, z, a|
     * [x, u|.cdr will return [x|
     * [x| will return [|
     * [| will return [|
     * </code>
     */
    public Stack<X> cdr() {

        // Will the real CDR please stand up?
        final Stack<X> j = clone();
        if (j.size() > 0) {
            j.pop();
        }
        return j;
    }

    @Override
    public String toString() {

        return mStk.toString();
    }

}
