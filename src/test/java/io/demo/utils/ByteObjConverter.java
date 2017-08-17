package io.demo.utils;

import java.io.*;

/**
 * Author Lance.
 * Date: 2017-08-17 16:50
 * Desc:
 */
public class ByteObjConverter {

    /**
     * @desc:字节转对象
     * @author lance
     * @time: 2017-08-17 16:50:31
     */
    public static Object byteToObject(byte[] bytes) {
        Object obj = null;
        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
        ObjectInputStream oi = null;
        try {
            oi = new ObjectInputStream(bi);
            obj = oi.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                oi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    /**
     * @desc:对象转字节
     * @author lance
     * @time: 2017-08-17 16:51:23
     */
    public static byte[] objectToByte(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = null;
        try {
            oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);
            bytes = bo.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                oo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return (bytes);
    }
}
