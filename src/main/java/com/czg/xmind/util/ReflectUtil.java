package com.czg.xmind.util;

import com.czg.xmind.AbstractComponent;
import com.czg.xmind.Context;
import com.czg.xmind.Tool;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ReflectUtil {

    private static final Map<String, AbstractComponent> cache = new ConcurrentHashMap<>();

    public static <T extends AbstractComponent> T getInstance(String name, Context context) {
        try {
            AbstractComponent abstractComponent = cache.get(name);
            if (abstractComponent == null) {
                Constructor constructor = Class.forName(name)
                        .getDeclaredConstructor(Context.class);
                boolean accessible = constructor.isAccessible();
                if (!accessible) {
                    constructor.setAccessible(true);
                }
                abstractComponent = (AbstractComponent) constructor.newInstance(context);
                cache.put(name, abstractComponent);
                if (!accessible) {
                    constructor.setAccessible(false);
                }
            }
            return (T) abstractComponent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T getInstance(Class<T> tClass) {
        try {
            return tClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T getInstance(String name) {
        try {
            return (T) Class.forName(name).newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
