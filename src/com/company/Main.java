package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        BinaryTree<Integer> testTree = new BinaryTree<>();

        testTree.root = new BinaryTree.Node<>(1);
        testTree.root.left = new BinaryTree.Node<>(2);
        testTree.root.right = new BinaryTree.Node<>(3);
        testTree.root.left.left = new BinaryTree.Node<>(4);
        testTree.root.left.right = new BinaryTree.Node<>(5);
        testTree.root.right.left = new BinaryTree.Node<>(6);


        System.out.println(testTree.toString());

        PrintWriter outFile = new PrintWriter("treeOutput.txt");

        outFile.write(testTree.toString());

        outFile.close();

        testTree.root = null;
        File inFile = new File("treeOutput.txt");
        Scanner scanner = new Scanner(inFile);
        BinaryTree<String> stringTestTree = new BinaryTree<>();
        stringTestTree = BinaryTree.readBinaryTree(scanner);

        System.out.println(stringTestTree.toString());

        scanner.close();

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("tree"));

        out.writeObject(stringTestTree);

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("tree"));

        stringTestTree = (BinaryTree<String>) in.readObject();


    }
}
