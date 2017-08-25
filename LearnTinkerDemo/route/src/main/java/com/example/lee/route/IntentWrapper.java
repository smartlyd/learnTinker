package com.example.lee.route;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.SparseArray;

import com.example.lee.route.annotations.ClassName;
import com.example.lee.route.annotations.Key;
import com.example.lee.route.annotations.RequestCode;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;

/**
 * Created by Lee on 2017/8/24.
 */

public class IntentWrapper {


    private int mFlags;
    private Intent intent;
    private String mClassName;
    private Context context;
    private Bundle mExtras;
    private int mRequestCode = -1;


    public IntentWrapper(int mFlags, String mClassName, Context context, Bundle mExtras, int mRequestCode) {
        this.mFlags = mFlags;
        this.mClassName = mClassName;
        this.context = context;
        this.mExtras = mExtras;
        this.mRequestCode = mRequestCode;


        intent = new Intent();
        intent.setClassName(context, mClassName);
        intent.putExtras(mExtras);
        intent.setFlags(mFlags);
    }


    public String getmClassName() {
        return mClassName;
    }

    public void setmClassName(@Nullable String mClassName) {
        intent.setClassName(context, mClassName);
    }


    public Intent getIntent() {
        return intent;
    }

    public Context getContext() {
        return context;
    }

    public Bundle getmExtras() {
        return mExtras;
    }


    public void addFlags(int flag) {
        intent.addFlags(flag);
    }

    public void start() {
        if (mRequestCode == -1) {
            startActivity();
        } else {
            startActivityForResult(mRequestCode);
        }
    }

    public void startActivityForResult(int mRequestCode) {
        if (!(context instanceof Activity))
            throw new RuntimeException("startActivityForResult only work for activity context!");
        ((Activity) context).startActivityForResult(intent, mRequestCode);
    }

    public void startActivity() {
        if (!(context instanceof Activity))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * intent wrapper's builder
     */
    public static final class Builder {
        private int mFlags;
        private Context context;
        private int requestCode;
        private String mClassName;
        private Method method;
        private Object[] mArgs;

        public Builder(Context context, Method method, Object[] args) {
            this.context = context;
            this.method = method;
            this.mArgs = args;
        }

        public Builder addFlags(int flag) {
            mFlags |= flag;
            return this;
        }

        @SuppressWarnings("Since15")
        public IntentWrapper build() {
            //方法注解
            Annotation[] methodAnnotations = method.getAnnotations();
            for (Annotation annotation : methodAnnotations) {
                parseMethodAnnotation(annotation);
            }

            if (TextUtils.isEmpty(mClassName))
                throw new RuntimeException("ClassName Annotaition is required");
            //参数类型
            Type[] types = method.getGenericParameterTypes();
            //参数名称
            Annotation[][] parameterAnnotationsArray = method.getParameterAnnotations();
            Bundle bundleExtras = new Bundle();
            for (int i = 0; i < types.length; i++) {
                String key = null;
                Annotation[] parameterAnnotations = parameterAnnotationsArray[i];
                for (Annotation annotation : parameterAnnotations) {
                    if (annotation instanceof Key) {
                        key = ((Key) annotation).value();
                        break;
                    }
                }
                parseParameter(bundleExtras, types[i], key, mArgs[i]);
            }
            return new IntentWrapper(mFlags, mClassName, context, bundleExtras,
                    method.isAnnotationPresent(RequestCode.class) ? requestCode : -1);
        }

        private void parseParameter(Bundle bundleExtra, Type type, String key, Object arg) {
            Class<?> rawParameterType = getRawType(type);

            if (rawParameterType == String.class) {
                bundleExtra.putString(key, arg.toString());
            } else if (rawParameterType == String[].class) {
                bundleExtra.putStringArray(key, (String[]) arg);
            } else if (rawParameterType == int.class || rawParameterType == Integer.class) {
                bundleExtra.putInt(key, Integer.parseInt(arg.toString()));
            } else if (rawParameterType == int[].class || rawParameterType == Integer[].class) {
                bundleExtra.putIntArray(key, (int[]) arg);
            } else if (rawParameterType == short.class || rawParameterType == Short.class) {
                bundleExtra.putShort(key, Short.parseShort(arg.toString()));
            } else if (rawParameterType == short[].class || rawParameterType == Short[].class) {
                bundleExtra.putShortArray(key, (short[]) arg);
            } else if (rawParameterType == long.class || rawParameterType == Long.class) {
                bundleExtra.putLong(key, Long.parseLong(arg.toString()));
            } else if (rawParameterType == long[].class || rawParameterType == Long[].class) {
                bundleExtra.putLongArray(key, (long[]) arg);
            } else if (rawParameterType == char.class) {
                bundleExtra.putChar(key, arg.toString().toCharArray()[0]);
            } else if (rawParameterType == char[].class) {
                bundleExtra.putCharArray(key, arg.toString().toCharArray());
            } else if (rawParameterType == double.class || rawParameterType == Double.class) {
                bundleExtra.putDouble(key, Double.parseDouble(arg.toString()));
            } else if (rawParameterType == double[].class || rawParameterType == Double[].class) {
                bundleExtra.putDoubleArray(key, (double[]) arg);
            } else if (rawParameterType == float.class || rawParameterType == Float.class) {
                bundleExtra.putFloat(key, Float.parseFloat(arg.toString()));
            } else if (rawParameterType == float[].class || rawParameterType == Float[].class) {
                bundleExtra.putFloatArray(key, (float[]) arg);
            } else if (rawParameterType == byte.class || rawParameterType == Byte.class) {
                bundleExtra.putByte(key, Byte.parseByte(arg.toString()));
            } else if (rawParameterType == byte[].class || rawParameterType == Byte[].class) {
                bundleExtra.putByteArray(key, (byte[]) arg);
            } else if (rawParameterType == boolean.class || rawParameterType == Boolean.class) {
                bundleExtra.putBoolean(key, Boolean.parseBoolean(arg.toString()));
            } else if (rawParameterType == boolean[].class || rawParameterType == Boolean[].class) {
                bundleExtra.putBooleanArray(key, (boolean[]) arg);
            } else if (rawParameterType == Bundle.class) {
                if (TextUtils.isEmpty(key)) {
                    bundleExtra.putAll((Bundle) arg);
                } else {
                    bundleExtra.putBundle(key, (Bundle) arg);
                }
            } else if (rawParameterType == SparseArray.class) {
                if (type instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                    Type actualTypeArgument = actualTypeArguments[0];

                    if (actualTypeArgument instanceof Class) {
                        Class<?>[] interfaces = ((Class) actualTypeArgument).getInterfaces();
                        for (Class<?> interfaceClass : interfaces) {
                            if (interfaceClass == Parcelable.class) {
                                bundleExtra.putSparseParcelableArray(key, (SparseArray<Parcelable>) arg);
                                return;
                            }
                        }
                        throw new RuntimeException("SparseArray的泛型必须实现Parcelable接口");
                    }
                } else {
                    throw new RuntimeException("SparseArray的泛型必须实现Parcelable接口");
                }
            } else if (rawParameterType == ArrayList.class) {
                if (type instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    Type[] actualTypeArguments = parameterizedType.getActualTypeArguments(); // 泛型类型数组
                    if (actualTypeArguments == null || actualTypeArguments.length != 1) {
                        throw new RuntimeException("ArrayList的泛型必须实现Parcelable接口");
                    }

                    Type actualTypeArgument = actualTypeArguments[0]; // 获取第一个泛型类型
                    if (actualTypeArgument == String.class) {
                        bundleExtra.putStringArrayList(key, (ArrayList<String>) arg);
                    } else if (actualTypeArgument == Integer.class) {
                        bundleExtra.putIntegerArrayList(key, (ArrayList<Integer>) arg);
                    } else if (actualTypeArgument == CharSequence.class) {
                        bundleExtra.putCharSequenceArrayList(key, (ArrayList<CharSequence>) arg);
                    } else if (actualTypeArgument instanceof Class) {
                        Class<?>[] interfaces = ((Class) actualTypeArgument).getInterfaces();
                        for (Class<?> interfaceClass : interfaces) {
                            if (interfaceClass == Parcelable.class) {
                                bundleExtra.putParcelableArrayList(key, (ArrayList<Parcelable>) arg);
                                return;
                            }
                        }
                        throw new RuntimeException("ArrayList的泛型必须实现Parcelable接口");
                    }
                } else {
                    throw new RuntimeException("ArrayList的泛型必须实现Parcelable接口");
                }
            } else {
                if (rawParameterType.isArray()) // Parcelable[]
                {
                    Class<?>[] interfaces = rawParameterType.getComponentType().getInterfaces();
                    for (Class<?> interfaceClass : interfaces) {
                        if (interfaceClass == Parcelable.class) {
                            bundleExtra.putParcelableArray(key, (Parcelable[]) arg);
                            return;
                        }
                    }
                    throw new RuntimeException("Object[]数组中的对象必须全部实现了Parcelable接口");
                } else {
                    //其他接口
                    Class<?>[] interfaces = rawParameterType.getInterfaces();
                    for (Class<?> interfaceClass : interfaces) {
                        if (interfaceClass == Serializable.class) {
                            bundleExtra.putSerializable(key, (Serializable) arg);
                        } else if (interfaceClass == Parcelable.class) {
                            bundleExtra.putParcelable(key, (Parcelable) arg);
                        } else {
                            throw new RuntimeException("Bundle不支持的类型, 参数: " + key);
                        }
                    }
                }

            }
        }

        private Class<?> getRawType(Type type) {
            if (type == null) throw new NullPointerException("type == null");

            if (type instanceof Class<?>) {
                // Type is a normal class.
                return (Class<?>) type;
            }
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;

                // I'm not exactly sure why getRawType() returns Type instead of Class. Neal isn't either but
                // suspects some pathological case related to nested classes exists.
                Type rawType = parameterizedType.getRawType();
                if (!(rawType instanceof Class)) throw new IllegalArgumentException();
                return (Class<?>) rawType;
            }
            if (type instanceof GenericArrayType) {
                Type componentType = ((GenericArrayType) type).getGenericComponentType();
                return Array.newInstance(getRawType(componentType), 0).getClass();
            }
            if (type instanceof TypeVariable) {
                // We could use the variable's bounds, but that won't work if there are multiple. Having a raw
                // type that's more general than necessary is okay.
                return Object.class;
            }
            if (type instanceof WildcardType) {
                return getRawType(((WildcardType) type).getUpperBounds()[0]);
            }

            throw new IllegalArgumentException("Expected a Class, ParameterizedType, or "
                    + "GenericArrayType, but <" + type + "> is of type " + type.getClass().getName());

        }

        private void parseMethodAnnotation(Annotation annotation) {
            if (annotation instanceof ClassName) {
                mClassName = ((ClassName) annotation).value();
            } else if (annotation instanceof RequestCode) {
                requestCode = ((RequestCode) annotation).value();
            }
        }

    }
}
