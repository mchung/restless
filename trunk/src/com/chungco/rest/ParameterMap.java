package com.chungco.rest;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A ParameterMap contains a set of key->value parameter mappings that are
 * shared between {@link com.chungco.rest.IRestCommand} implementations at
 * runtime. This class uses the a {@link java.util.concurrent.ConcurrentHashMap}
 * as it's backing store.
 * 
 * @author Marc Chung <mchung@gmail.com>
 */
public class ParameterMap {

    private final Map<String, String> mConfigMap;

    public ParameterMap() {

        mConfigMap = new ConcurrentHashMap<String, String>();
    }

    public void setParam(final String pKey, final String pVal) {

        mConfigMap.put(pKey, pVal);
    }

    public String getParam(final String pKey) {

        return mConfigMap.get(pKey);
    }

    public String removeParam(final String pKey) {

        return mConfigMap.remove(pKey);
    }

    public Set<String> keys() {

        return mConfigMap.keySet();
    }

    public void clear() {

        mConfigMap.clear();
    }

    @Override
    public ParameterMap clone() {

        final ParameterMap p = new ParameterMap();
        for (final String key : mConfigMap.keySet()) {
            final String val = mConfigMap.get(key);
            p.setParam(key, val);
        }
        return p;
    }

}
