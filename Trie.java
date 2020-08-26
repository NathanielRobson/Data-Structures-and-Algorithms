import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




public class Trie {

    class TrieNode {

        private final Map<Character, TrieNode> children = new HashMap<>();

        private boolean endOfWord;

        Map<Character, TrieNode> getChildren() {
            return children;
        }

        boolean isEndOfWord() {
            return endOfWord;
        }

        void setEndOfWord(boolean endOfWord) {
            this.endOfWord = endOfWord;
        }
    }

    // Usual Single Pointer to the root
    private TrieNode root;

    // Constructor
    Trie() {
        root = new TrieNode();
    }

    // Insert
    public void addWord(String word) {
        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            current = current.getChildren().computeIfAbsent(word.charAt(i), c -> new TrieNode());
        }
        current.setEndOfWord(true);
    }

    public List<String> getWords(String prefix) {
        TrieNode current = root;
        List<String> ret = new ArrayList<>();
        if (prefix != null) {
            prefix = prefix.toLowerCase();
            for (int i = 0, len = prefix.length(); i < len && current != null; ++i) {
                current = current.children.get(prefix.charAt(i));
            }
            if (current != null) {
                List<String> list = getWords();
                for (String s : list) {
                    ret.add(prefix + s.substring(1));
                }
            }
        }
        return ret;
    }

    public List<String> getWords() {
        TrieNode current = root;
        List<String> ret = new ArrayList<>();
        if (this.) {
            ret.add(String.valueOf(val));
        }
        for (TrieNode node : children) {
            if (node != null) {
                List<String> list = node.getWords();
                for (String s : list) {
                    ret.add(val + s);
                }
            }
        }
        return ret;
    }

    public String toString() {
        return String.valueOf(val);
    }

    public boolean delete(String word) {
        return delete(root, word, 0);
    }

    boolean containsNode(String word) {
        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.getChildren().get(ch);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return current.isEndOfWord();
    }

    boolean isEmpty() {
        return root == null;
    }

    private boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.isEndOfWord()) {
                return false;
            }
            current.setEndOfWord(false);
            return current.getChildren().isEmpty();
        }
        char ch = word.charAt(index);
        TrieNode node = current.getChildren().get(ch);
        if (node == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode = delete(node, word, index + 1) && !node.isEndOfWord();

        if (shouldDeleteCurrentNode) {
            current.getChildren().remove(ch);
            return current.getChildren().isEmpty();
        }
        return false;
    }


    /*
        Test it
     */
    public static void main(String...args) {

        Trie trie = new Trie();

        trie.addWord("Programming");
        trie.addWord("is");
        trie.addWord("a");
        trie.addWord("way");
        trie.addWord("of");
        trie.addWord("life!");


        System.out.println(trie.containsNode("3"));
        System.out.println(trie.containsNode("vida"));
        System.out.println(trie.containsNode("life!"));
        System.out.println(trie.containsNode("Programming"));

        trie.delete("Programming");


        System.out.println(trie.containsNode("Programming"));
    }


    //@Test
    //public void givenATrie_WhenAddingElements_ThenTrieNotEmpty() {
    //    Trie1 trie = createTrie();
    //
    //    assertFalse(trie.isEmpty());
    //}


}