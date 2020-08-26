package Exercise2;

public class Main {
    public static void main(String[] args) {
        BST tree = new BST();


        System.out.println(tree.greater(1));

        tree.insert(3);
        tree.insert(8);
        tree.insert(1);
        tree.insert(19);
        tree.insert(3);
        System.out.println(tree.greater(1));

        int x = tree.nth(1);
        System.out.println(x);

    }
}