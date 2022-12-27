package utility;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

public class BufferTools {
    public static FloatBuffer asFlippedFloatBuffer(float... values) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(values.length);
        buffer.put(values);
        buffer.flip();
        return buffer;
    }
}
