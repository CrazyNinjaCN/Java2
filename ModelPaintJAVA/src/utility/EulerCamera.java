
package utility;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.GLU;

import static java.lang.Math.*;
import static org.lwjgl.opengl.ARBDepthClamp.GL_DEPTH_CLAMP;
import static org.lwjgl.opengl.GL11.*;


public final class EulerCamera implements Camera {

    private float x = 0;
    private float y = 0;
    private float z = 0;
    private float pitch = 0;
    private float yaw = 0;
    private float roll = 0;
    private float fov = 90;
    private float aspectRatio = 1;
    private final float zNear;
    private final float zFar;

    private EulerCamera(Builder builder) {
        this.x = builder.x;
        this.y = builder.y;
        this.z = builder.z;
        this.pitch = builder.pitch;
        this.yaw = builder.yaw;
        this.roll = builder.roll;
        this.aspectRatio = builder.aspectRatio;
        this.zNear = builder.zNear;
        this.zFar = builder.zFar;
        this.fov = builder.fov;
    }


    public void processMouse(float mouseSpeed, float maxLookUp, float maxLookDown) {
        float mouseDX = Mouse.getDX() * mouseSpeed * 0.16f;
        float mouseDY = Mouse.getDY() * mouseSpeed * 0.16f;
        if (yaw + mouseDX >= 360) {
            yaw = yaw + mouseDX - 360;
        } else if (yaw + mouseDX < 0) {
            yaw = 360 - yaw + mouseDX;
        } else {
            yaw += mouseDX;
        }
        if (pitch - mouseDY >= maxLookDown && pitch - mouseDY <= maxLookUp) {
            pitch += -mouseDY;
        } else if (pitch - mouseDY < maxLookDown) {
            pitch = maxLookDown;
        } else if (pitch - mouseDY > maxLookUp) {
            pitch = maxLookUp;
        }
    }

    public void processKeyboard(float delta, float speedX, float speedY, float speedZ) {
        if (delta <= 0) {
            throw new IllegalArgumentException("delta " + delta + " is 0 or is smaller than 0");
        }

        boolean keyUp = Keyboard.isKeyDown(Keyboard.KEY_UP) || Keyboard.isKeyDown(Keyboard.KEY_W);
        boolean keyDown = Keyboard.isKeyDown(Keyboard.KEY_DOWN) || Keyboard.isKeyDown(Keyboard.KEY_S);
        boolean keyLeft = Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_A);
        boolean keyRight = Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || Keyboard.isKeyDown(Keyboard.KEY_D);
        boolean flyUp = Keyboard.isKeyDown(Keyboard.KEY_SPACE);
        boolean flyDown = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT);

        if (keyUp && keyRight && !keyLeft && !keyDown) {
            moveFromLook(speedX * delta * 0.003f, 0, -speedZ * delta * 0.003f);
        }
        if (keyUp && keyLeft && !keyRight && !keyDown) {
            moveFromLook(-speedX * delta * 0.003f, 0, -speedZ * delta * 0.003f);
        }
        if (keyUp && !keyLeft && !keyRight && !keyDown) {
            moveFromLook(0, 0, -speedZ * delta * 0.003f);
        }
        if (keyDown && keyLeft && !keyRight && !keyUp) {
            moveFromLook(-speedX * delta * 0.003f, 0, speedZ * delta * 0.003f);
        }
        if (keyDown && keyRight && !keyLeft && !keyUp) {
            moveFromLook(speedX * delta * 0.003f, 0, speedZ * delta * 0.003f);
        }
        if (keyDown && !keyUp && !keyLeft && !keyRight) {
            moveFromLook(0, 0, speedZ * delta * 0.003f);
        }
        if (keyLeft && !keyRight && !keyUp && !keyDown) {
            moveFromLook(-speedX * delta * 0.003f, 0, 0);
        }
        if (keyRight && !keyLeft && !keyUp && !keyDown) {
            moveFromLook(speedX * delta * 0.003f, 0, 0);
        }
        if (flyUp && !flyDown) {
            y += speedY * delta * 0.003f;
        }
        if (flyDown && !flyUp) {
            y -= speedY * delta * 0.003f;
        }
    }

    public void moveFromLook(float dx, float dy, float dz) {
        this.z += dx * (float) cos(toRadians(yaw - 90)) + dz * cos(toRadians(yaw));
        this.x -= dx * (float) sin(toRadians(yaw - 90)) + dz * sin(toRadians(yaw));
        this.y += dy * (float) sin(toRadians(pitch - 90)) + dz * sin(toRadians(pitch));
    }

    public void applyOptimalStates() {
        if (GLContext.getCapabilities().GL_ARB_depth_clamp) {
            glEnable(GL_DEPTH_CLAMP);
        }
    }

    public void applyPerspectiveMatrix() {
        glPushAttrib(GL_TRANSFORM_BIT);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluPerspective(fov, aspectRatio, zNear, zFar);
        glPopAttrib();
    }

    public void applyTranslations() {
        glPushAttrib(GL_TRANSFORM_BIT);
        glMatrixMode(GL_MODELVIEW);
        glRotatef(pitch, 1, 0, 0);
        glRotatef(yaw, 0, 1, 0);
        glRotatef(roll, 0, 0, 1);
        glTranslatef(-x, -y, -z);
        glPopAttrib();
    }

    public float x() {
        return x;
    }


    public float y() {
        return y;
    }


    public float z() {
        return z;
    }

    @Override
    public String toString() {
        return "EulerCamera [x=" + x + ", y=" + y + ", z=" + z + ", pitch=" + pitch + ", yaw=" + yaw + ", " +
                "roll=" + roll + ", fov=" + fov + ", aspectRatio=" + aspectRatio + ", zNear=" + zNear + ", " +
                "zFar=" + zFar + "]";
    }


    public static class Builder {

        private float aspectRatio = 1;
        private float x = 0, y = 0, z = 0, pitch = 0, yaw = 0, roll = 0;
        private float zNear = 0.3f;
        private float zFar = 100;
        private float fov = 90;

        public Builder() {

        }


        public Builder setAspectRatio(float aspectRatio) {
            if (aspectRatio <= 0) {
                throw new IllegalArgumentException("aspectRatio " + aspectRatio + " was 0 or was smaller than 0");
            }
            this.aspectRatio = aspectRatio;
            return this;
        }

        public Builder setFieldOfView(float fov) {
            this.fov = fov;
            return this;
        }


        public Builder setPosition(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
            return this;
        }

        public EulerCamera build() {
            if (zFar <= zNear) {
                throw new IllegalArgumentException("farClippingPane " + zFar + " is the same or less than " +
                        "nearClippingPane " + zNear);
            }
            return new EulerCamera(this);
        }
    }
}
