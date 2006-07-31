
package com.chungco.core;

import java.util.EmptyStackException;

import com.chungco.core.Stack;

import junit.framework.Assert;
import junit.framework.TestCase;

public class StackTest extends TestCase {

    /*
     * Test method for 'com.chungco.adt.Stack.Stack()'
     */
    public void testStack() {

        try {
            final Stack stack = new Stack();
            stack.peek();
            Assert.fail();
        } catch (EmptyStackException ese) {
            Assert.assertTrue(true);
        }

        try {
            final Stack stack = new Stack();
            stack.pop();
            Assert.fail();
        } catch (EmptyStackException ese) {
            Assert.assertTrue(true);
        }

    }

    /*
     * Test method for 'com.chungco.adt.Stack.push(X)'
     */
    public void testPush() {

        final Stack<String> stack = new Stack<String>();

        try {
            final String x = new String("x");
            stack.push(x);
            final String y = stack.pop();
            Assert.assertSame(x, y); // Should be pointing to the same object
        } catch (EmptyStackException ese) {
            Assert.fail();
        }
    }

    /*
     * Test method for 'com.chungco.adt.Stack.peek()'
     */
    public void testPeek() {

        final Stack<String> stack = new Stack<String>();

        try {
            final String x = new String("x");
            stack.push(x);
            final String y = stack.peek();
            Assert.assertEquals(stack.size(), 1);
            Assert.assertSame(x, y); // Should be pointing to the same object

            final String z = stack.pop();
            Assert.assertEquals(x, z);
            Assert.assertEquals(y, z);
        } catch (EmptyStackException ese) {
            Assert.fail();
        }
    }

    /*
     * Test method for 'com.chungco.adt.Stack.empty()'
     */
    public void testEmpty() {

        final Stack<String> stack = new Stack<String>();

        try {
            final String x = new String("x");
            Assert.assertEquals(stack.empty(), true);
            stack.push(x);
            Assert.assertEquals(stack.empty(), false);
            stack.peek();
            Assert.assertEquals(stack.empty(), false);
            stack.pop();
            Assert.assertEquals(stack.empty(), true);
        } catch (EmptyStackException ese) {
            Assert.fail();
        }

    }

    /*
     * Test method for 'com.chungco.adt.Stack.pop()'
     */
    public void testPop() {

        final Stack<String> stack = new Stack<String>();
        try {
            stack.pop();
        } catch (EmptyStackException ese) {
            Assert.assertTrue(true);
        }

        try {
            final String x = new String("x");
            stack.push(x);
            final String y = stack.pop();
            Assert.assertEquals(x, y);
        } catch (EmptyStackException ese) {
            Assert.fail();
        }
    }

    public void testCdr() {

        final Stack<String> stack = new Stack<String>();
        stack.push("x", "y", "z", "a", "b");
        final Stack<String> expStk = new Stack<String>();
        expStk.push("x").push("y", "z").push("a");

        stack.pop();

        Assert.assertEquals(expStk.size(), stack.size());

        for (int i = 0, j = stack.size(); i < j; i++) {
            final String act = stack.pop();
            final String exp = expStk.pop();
            Assert.assertEquals(exp, act);
        }

    }

}
