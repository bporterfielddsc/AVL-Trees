package com.company;

public class BinarySearchTreeWithRotate<E extends Comparable<E>> extends binarySearchTree<E>
{
    protected Node<E> rotateRight(Node<E> root)
    {
        Node<E> temp = root.left;
        root.left = temp.right;
        temp.right = root;
        return temp;
    }

    protected Node<E> rotateLeft(Node<E> root)
    {
        Node<E> temp = root.right;
        root.right = temp.left;
        temp.left = root;
        return temp;
    }
}
