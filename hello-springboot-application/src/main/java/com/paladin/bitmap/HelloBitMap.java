package com.paladin.bitmap;

import com.googlecode.javaewah.EWAHCompressedBitmap;
import java.io.*;

public class HelloBitMap {
    public static void main(String[] args) {
        tryHelloEWAH();
    }

    private static void tryHelloEWAH() {
        EWAHCompressedBitmap ewahBitmap1 = EWAHCompressedBitmap.bitmapOf(0, 2, 55, 64, 1 << 30);

        System.out.println("=============ewahBitmap1=================");

        System.out.println("ewahBitmap1的内容: " + ewahBitmap1);

        System.out.println("ewahBitmap1中是否包含64: " + ewahBitmap1.get(64));

        System.out.println("ewahBitmap1中value的个数: " + ewahBitmap1.cardinality());

        //遍历全部value
        ewahBitmap1.forEach(integer -> {
            System.out.println(integer);
        });
        System.out.println("=============ewahBitmap1=================");

        EWAHCompressedBitmap ewahBitmap2 = EWAHCompressedBitmap.bitmapOf(1, 3, 64, 1 << 30);

        System.out.println("=============ewahBitmap2=================");
        System.out.println("ewahBitmap2的内容: " + ewahBitmap2);

        System.out.println("ewahBitmap2中是否包含64: " + ewahBitmap2.get(64));

        System.out.println("ewahBitmap2中value的个数: " + ewahBitmap2.cardinality());

        //遍历全部value
        ewahBitmap2.forEach(integer -> {
            System.out.println(integer);
        });
        System.out.println("=============ewahBitmap2=================");

        System.out.println("=============位或运算=================");
        EWAHCompressedBitmap orbitmap = ewahBitmap1.or(ewahBitmap2);
        System.out.println("bitmap 1 OR bitmap 2: " + orbitmap);
        System.out.println("位或运算memory usage: " + orbitmap.sizeInBytes() + " bytes");

        System.out.println("=============位与运算=================");
        EWAHCompressedBitmap andbitmap = ewahBitmap1.and(ewahBitmap2);
        System.out.println("bitmap 1 AND bitmap 2: " + andbitmap);
        System.out.println("位与运算memory usage: " + andbitmap.sizeInBytes() + " bytes");

        System.out.println("=============序列化与反序列化=================");
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ewahBitmap1.serialize(new DataOutputStream(bos));

            EWAHCompressedBitmap ewahBitmap1new = new EWAHCompressedBitmap();
            byte[] bout = bos.toByteArray();
            ewahBitmap1new.deserialize(new DataInputStream(new ByteArrayInputStream(bout)));
            System.out.println("bitmap 1 (recovered) : " + ewahBitmap1new);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}