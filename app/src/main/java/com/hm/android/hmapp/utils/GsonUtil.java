package com.hm.android.hmapp.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Administrator on 2017/10/10.
 */

public class GsonUtil<T> {
    private static Gson gson = null;

    static {
        if (null == gson) {
            gson = new GsonBuilder().serializeNulls().create();
        }
    }

    public static Gson getGson() {
        return gson;
    }

    public String toJson(T t) {
        return gson.toJson(t);
    }

    public T toBean(String msg, T t) {
        // 这里起初使用
        // Type type = TypeToken<T>() {}.getType());
        // return (T) gson.fromJson(msg,type);
        // 貌似java泛型不具有传递性。只能采用Class参数的方法。有大神可以解决Type的问题请留言告知。THX。
        return (T) gson.fromJson(msg, t.getClass());
    }



    public static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }

}
