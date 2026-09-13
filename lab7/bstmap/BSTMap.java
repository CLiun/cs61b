package bstmap;

import edu.princeton.cs.algs4.BST;
import org.antlr.v4.runtime.tree.Tree;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private int treeSize;
    private TreeNode root;

    private class TreeNode {
        K key;
        V value;
        TreeNode left;
        TreeNode right;


        TreeNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.right = null;
            this.left = null;
        }
    }

    BSTMap() {
        treeSize = 0;
        root = null;
    }

    @Override
    public void clear() {
        treeSize = 0;
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        TreeNode curr = root;
        while (curr != null) {
            int cmp = curr.key.compareTo(key);
            if (cmp == 0) {
                return true;
            } else if (cmp > 0) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        TreeNode curr = root;
        while (curr != null) {
            int cmp = curr.key.compareTo(key);
            if (cmp == 0) {
                return curr.value;
            } else if (cmp > 0) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return treeSize;
    }

    @Override
    public void put(K key, V value) {
        root = putHelper(root, key, value);
        treeSize += 1;
    }
    private TreeNode putHelper(TreeNode r, K key, V value) {
        if (r == null) {
            return new TreeNode(key, value);
        }
        int cmp = r.key.compareTo(key);
        if (cmp == 0) {
            r.value = value;
        } else if (cmp > 0) {
            r.left = putHelper(r.left, key, value);
        } else {
            r.right = putHelper(r.right, key, value);
        }
        return r;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        keySetHelper(root, keys);
        return keys;
    }
    private void keySetHelper(TreeNode r, Set<K> keys) {
        if (r == null) {
            return;
        }
        keySetHelper(r.left, keys);
        keys.add(r.key);
        keySetHelper(r.right, keys);
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
