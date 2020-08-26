public class MainTest {
    public static void main(String[] args) {
        Trie1 test1 = new Trie1();
        test1.addWord("yo");
        test1.delete("yo");
        System.out.println(test1.delete("yo"));System.out.println(test1.delete("yo"));
        System.out.println(        test1.getWords('y'));
    }
}
