package Exercise2;

import java.util.NoSuchElementException;

public class BST {
    private BTNode<Integer> root;
    private boolean located;
    private int mycount;
    private int index;
    private int n;

    public BST() {
        root = null;
    }

    public boolean find(Integer i) {
        BTNode<Integer> n = root;
        boolean found = false;

        while (n != null && !found) {
            int comp = i.compareTo(n.data);
            if (comp == 0) {
                found = true;
            } else if (comp < 0) {
                n = n.left;
            } else {
                n = n.right;
            }
        }

        return found;
    }

    public boolean insert(Integer i) {
        BTNode<Integer> parent = root, child = root;
        boolean goneLeft = false;

        while (child != null && i.compareTo(child.data) != 0) {
            parent = child;
            if (i.compareTo(child.data) < 0) {
                child = child.left;
                goneLeft = true;
            } else {
                child = child.right;
                goneLeft = false;
            }
        }

        if (child != null)
            return false;  // number already present
        else {
            BTNode<Integer> leaf = new BTNode<Integer>(i);
            if (parent == null) { // tree was empty
                root = leaf;
            } else if (goneLeft) {
                parent.left = leaf;
            } else {
                parent.right = leaf;
            }
            return true;
        }
    }

    private void RecursionFind(BTNode<Integer> node, int n) {
        if (node.left != null) {
            RecursionFind(node.left, n);
        }

        if (node.right != null) {
            RecursionFind(node.right, n);
        }

        if (node.data > n) {
            mycount++;
        }
    }

    public int greater(int n) {
        BTNode<Integer> root = this.root;
        mycount = 0;

        //Empty bst
        if (root == null) {
            return 0;
        }

        RecursionFind(root, n);

        return mycount;
    }

    private void TraverseTreeOrder(BTNode<Integer> node, int pos) {
        //If tree is not empty and element not found traverse tree until found node
        if (node != null && !located) {
            TraverseTreeOrder(node.left, pos);

            if (index == pos) {
                n = node.data;
                located = true;
            }

            index++;
            TraverseTreeOrder(node.right, pos);
        }
    }

    public int nth(int i) {
        index = 0;
        located = false;
        TraverseTreeOrder(this.root, i);

        if (!located) {
            //if nth element is not in BST throw NoSuchElementException
            throw new NoSuchElementException("No Element In BST Found");
        }
        return n;
    }
}

class BTNode<T> {
    T data;
    BTNode<T> left, right;

    BTNode(T o) {
        data = o;
        left = right = null;
    }
}