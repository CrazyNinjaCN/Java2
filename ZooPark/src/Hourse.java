public class Hourse extends Animal implements IHourse {
    public Hourse(float h, float w, String c, Boolean p) {
        height = h;
        weight = w;
        color = c;
        predator = p;
    }
    void print(){
        System.out.println("height: " + height + " weight:" + weight + " color: " + color + " predator: " + predator);
    }
    void printParam(){
        System.out.println("param1: " + param1 + " param2: " + param2 + " param3:" + param3);
    }
}
