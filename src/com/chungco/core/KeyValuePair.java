package com.chungco.core;

/**
 * A Key-Value pair
 * 
 * @author Marc Chung <mchung@gmail.com>
 * @param <K>
 *            The key
 * @param <V>
 *            The value
 */
public class KeyValuePair<K, V> {

    private final K k;

    private final V v;

    public KeyValuePair(final K pK, final V pV) {

        k = pK;
        v = pV;
    }

    public K key() {

        return k;
    }

    public V value() {

        return v;
    }

    @Override
    public String toString() {

        return "<K,V>=<" + key() + ", " + value() + ">";
    }

}
