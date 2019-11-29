package com.company;

public class AVLTree<E extends Comparable<E>> extends BinarySearchTreeWithRotate<E>
{
    //Flag to indicate that the height of the tree has increased
    private boolean increase;

    @Override
    public boolean add(E item)
    {
        increase = false;
        root = add((AVLNode<E>)root, item);
        return addReturn;
    }

    private AVLNode<E> add(AVLNode<E> localRoot, E item)
    {
        //If root is NULL
        if(localRoot == null)
        {
            addReturn = true;
            increase = true;
            return new AVLNode<E>(item);
        }

        //If root is equal to the value
        if(item.compareTo(localRoot.data) == 0)
        {
            addReturn = false;
            increase = false;
            return localRoot;
        }
        else if(item.compareTo(localRoot.data) < 0)
        {
            localRoot.left = add((AVLNode<E>)localRoot.left, item);

            if(increase)
            {
                decrementBalance(localRoot);

                if(localRoot.balance < AVLNode.LEFT_HEAVY)
                {
                    increase = false;
                    return rebalanceLeft(localRoot);
                }
            }
            return localRoot;
        }
        else
        {
            localRoot.right = add((AVLNode<E>)localRoot.right, item);

            if (increase)
            {
                incrementBalance(localRoot);

                if (localRoot.balance > AVLNode.RIGHT_HEAVY)
                {
                    increase = false;
                    return rebalanceRight(localRoot);
                }
            }
            return localRoot;
        }
    }

    private void incrementBalance(AVLNode<E> localRoot)
    {
        localRoot.balance++;
        if(localRoot.balance == AVLNode.BALANCED)
        {
            increase = false;
        }
    }

    private AVLNode<E> rebalanceLeft(AVLNode<E> localRoot)
    {
        AVLNode leftChild = (AVLNode) localRoot.left;
        if(leftChild.balance > AVLNode.BALANCED)
        {
            AVLNode leftRightChild = (AVLNode) leftChild.right;
            if (leftRightChild.balance < AVLNode.BALANCED)
            {
                leftChild.balance = AVLNode.BALANCED;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.RIGHT_HEAVY;
            }
            else
            {
                leftChild.balance = AVLNode.LEFT_HEAVY;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
        }
        else
        {
            leftChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
        }
        return (AVLNode) rotateRight(localRoot);

    }

    private AVLNode<E> rebalanceRight(AVLNode<E> localRoot)
    {
        AVLNode rightChild = (AVLNode) localRoot.right;
        if(rightChild.balance > AVLNode.BALANCED)
        {
            AVLNode rightLeftChild = (AVLNode) rightChild.left;
            if (rightLeftChild.balance < AVLNode.BALANCED)
            {
                rightChild.balance = AVLNode.BALANCED;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.RIGHT_HEAVY;
            }
            else
            {
                rightChild.balance = AVLNode.LEFT_HEAVY;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
        }
        else
        {
            rightChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
        }
        return (AVLNode) rotateLeft(localRoot);

    }


    private void decrementBalance(AVLNode<E> localRoot)
    {
        localRoot.balance--;
        if(localRoot.balance == AVLNode.BALANCED)
        {
            increase = false;
        }
    }

    //region Inner Classes

    private static class AVLNode<E> extends Node<E>
    {
        //Constant to indicate left-heavy
        public static final int LEFT_HEAVY = -1;
        //Constant to indicate balance
        public static final int BALANCED = 0;
        //Constant to indicate right-heavy
        public static final int RIGHT_HEAVY = 1;
        //Right subtree height - left subtree height
        private int balance;

        public AVLNode(E item)
        {
            super(item);
            balance = BALANCED;
        }

        @Override
        public String toString()
        {
            return balance + ": " + super.toString();
        }
    }

    //endregion
}
