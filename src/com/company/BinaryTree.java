package com.company;

import java.io.Serializable;
import java.util.Scanner;
import java.util.function.BiConsumer;

public class BinaryTree<E> implements Serializable
{
    protected Node<E> root;

    //region Constructors
    public BinaryTree()
    {
        root = null;
    }

    public BinaryTree(Node<E> root)
    {
        this.root = root;
    }

    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree)
    {
        root = new Node<E>(data);
        if(leftTree != null)
        {
            root.left = leftTree.root;
        }
        else
        {
            root.left = null;
        }

        if (rightTree != null)
        {
            root.right = rightTree.root;
        }
        else
        {
            root.right = null;
        }
    }
    //endregion
    //region Inner Classes

    protected static class Node<E> implements Serializable
    {
        protected E data;
        protected Node<E> left;
        protected Node<E> right;

        public Node(E data)
        {
            this.data = data;
            left = null;
            right = null;
        }

        public String toString()
        {
            return data.toString();
        }
    }

    //endregion

    //region Public Methods
    public BinaryTree<E> getLeftSubtree()
    {
        if(root != null && root.left != null)
        {
            return new BinaryTree<E>(root.left);
        }
        else
        {
            return null;
        }
    }

    public BinaryTree<E> getRightSubtree()
    {
        if(root != null && root.right != null)
        {
            return new BinaryTree<E>(root.right);
        }
        else
        {
            return null;
        }
    }

    public boolean isLeaf()
    {
        return root.left == null && root.right == null;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        preOrderTraverse(root, 1, (e, d) -> {
            for (int i = 1; i < d; i++)
            {
                sb.append(" ");
            }

            sb.append(e);
            sb.append("\n");
        });

        return sb.toString();
    }

    public static BinaryTree<String> readBinaryTree(Scanner scan)
    {
        String data = scan.nextLine().trim();

        if(data.equals("null"))
        {
            return null;
        }
        else
        {
            BinaryTree<String> leftTree = readBinaryTree(scan);
            BinaryTree<String> rightTree = readBinaryTree(scan);
            return new BinaryTree<>(data, leftTree, rightTree);
        }
    }

    public void preOrderTraverse(BiConsumer<E, Integer> consumer)
    {
        preOrderTraverse(root, 1, consumer);

    }
    //endregion


    //region Private Methods
    private void toString(Node<E> node, int depth, StringBuilder sb)
    {
        for (int i = 1; i < depth; i++)
        {
            sb.append(" ");
        }

        if(node == null)
        {
            sb.append("null\n");
        }
        else
        {
            sb.append(node.toString() + "\n");

            toString(node.left, depth+1, sb);

            toString(node.right, depth+1,sb);
        }
    }

    private void preOrderTraverse(Node<E> node, int depth, BiConsumer<E, Integer> consumer)
    {
        if(node == null)
        {
            consumer.accept(null, depth);
        }
        else
        {
            consumer.accept(node.data, depth);
            preOrderTraverse(node.left, depth+1, consumer);
            preOrderTraverse(node.right, depth+1,consumer);
        }
    }
    //endregion
}
