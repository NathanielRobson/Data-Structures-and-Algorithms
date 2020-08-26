import java.util.*;

public class Trie1 {
    public class TrieNode {

        char value;
        ArrayList<TrieNode> children = new ArrayList<>();
        TrieNode parent;

        //boolean to indicate whether it is the end of the word
        boolean endOfWord = false;

        public TrieNode(char value, boolean endOfWord, TrieNode parent) {
            this.value = value;
            this.endOfWord = endOfWord;
            this.parent = parent;
        }

        public TrieNode() {
        }

        public void addChild(char charToAdd, boolean endOfWord) {
            children.add(new TrieNode(charToAdd, endOfWord, this));
        }
    }
    private TrieNode root;

    //Initialises the trie to be empty
    public Trie1() {
        //root = new tree node
        this.root = new TrieNode();
    }

    //Adds a word to the trie datastructure
    public boolean addWord(String word) {
        word = word.toUpperCase();
        //
        if (word == null || word.isEmpty()) {
            return false;
        }
        boolean allLetters = word.chars().allMatch(Character::isLetter);
        if (!allLetters) {
            System.out.println("Word does not contain only letters - returning false");
            return false;
        }

        TrieNode node = root;

        int j;
        for (int i = 0; i < word.length(); i++) {
            char char1 = word.charAt(i);
            for (j = 0; j < node.children.size(); j++) {
                if (node.children.get(j).value == char1) {
                    if (i == word.length() - 1) {
                        node.children.get(j).endOfWord = true;
                    }
                    break;
                }
            }
            if (j == node.children.size()) {
                if (i == word.length() - 1) {
                    node.addChild(char1, true);
                } else {
                    node.addChild(char1, false);
                }
            }
            node = node.children.get(j);
        }
        return true;
    }

    public boolean find(String word) {
        word = word.toUpperCase();
        TrieNode node = root;
        int len = word.length();
        int count = 0;
        int j = 0;
        for (int i = 0; i < word.length(); i++) {
            char char1 = word.charAt(i);
            for (j = 0; j < node.children.size(); j++) {
                if (node.children.get(j).value == char1) {
                    count++;
                    node = node.children.get(j);
                    j = 0;
                    break;
                }
            }
        }

        return count == len && node.endOfWord;
    }

    public List<String> getWords(char c) {
        c = ((Character) c).toString().toUpperCase().charAt(0);

        TrieNode node = root;
        boolean found = false;

        for (int i = 0; i < node.children.size(); i++) {
            if (found) {
                node = node.children.get(i);
                found = false;
                i = 0;
                continue;
            }

            if (node.children.get(i).value == c) {
                node = node.children.get(i);
                break;
            }
            if (i == node.children.size() - 1) {
                i = 0;
                found = true;
            }
        }

        Stack myStack = new Stack();
        for (TrieNode child : node.children) {
            ArrayList childList = new ArrayList();
            myStack.push(childList);
            readNode(child, myStack);
        }

        List<String> toReturn = new ArrayList<>();
        while (!myStack.empty()) {
            String temp = ((Character) c).toString();
            ArrayList current = (ArrayList) myStack.pop();
            for (int i = 0; i < current.size(); i++) {
                temp += current.get(i);
            }
            toReturn.add(temp);
        }
        Collections.sort(toReturn);

        return toReturn;

    }

    private void readNode(TrieNode node, Stack stackOfLists) {
        ArrayList current = (ArrayList) stackOfLists.pop();
        if (node.children.isEmpty()) {
            current.add(node.value);
            stackOfLists.push(current);
        } else {
            for (TrieNode child : node.children) {
                ArrayList childList = new ArrayList();
                for (Object o : current) {
                    childList.add(o);
                }
                if (node.endOfWord) {
                    current.add(node.value);
                    stackOfLists.push(current);
                }
                childList.add(node.value);
                stackOfLists.push(childList);
                readNode(child, stackOfLists);
            }
        }
    }


    public boolean delete(String word) {

        boolean allLetters = word.chars().allMatch(Character::isLetter);
        if (!allLetters) {
            System.out.println("Delete word does not contain only letters - returning false");
            return false;
        }

        if (find(word)) {

            TrieNode node = root;

            if (node.children.isEmpty()) {
                return false;
            }

            word = word.toLowerCase();

            if (word.length() > 1) {
                String restOfWord = word.substring(0, word.length() - 1);
                delete(restOfWord);
            }

            int charCount = 0;
            try {
                for (int i = 0; i < node.children.size(); i++) {
                    if (node.children.get(i).value == word.charAt(charCount)) {
                        charCount++;
                        node = node.children.get(i);
                        i = 0;
                    }
                    if (charCount == word.length() - 1) {
                        if (node.children.get(i).value == word.charAt(charCount)) {
                            node.children.remove(i);
                        }
                    }
                }
            } catch (Exception e) {
                //nothing
            }
        } else return false;
            return true;
        }
}
