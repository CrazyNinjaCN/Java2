package utility;

public interface Camera {
    public void processMouse(float mouseSpeed, float maxLookUp, float maxLookDown);

    public void processKeyboard(float delta, float speedX, float speedY, float speedZ);

    public void moveFromLook(float dx, float dy, float dz);

    public void applyOptimalStates();

    public void applyPerspectiveMatrix();

    public void applyTranslations();

    public float x();

    public float y();

    public float z();
}
