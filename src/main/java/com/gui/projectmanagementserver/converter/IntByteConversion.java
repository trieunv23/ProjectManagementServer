package com.gui.projectmanagementserver.converter;

import java.nio.ByteBuffer;

public class IntByteConversion {
    public static byte[] intToBytes(int value) {
        return ByteBuffer.allocate(Integer.BYTES).putInt(value).array();
    }

    public static int bytesToInt(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getInt();
    }
}
