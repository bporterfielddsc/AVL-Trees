package com.company;

public class binarySearchTree<E extends Comparable<E>> extends BinaryTree<E> implements searchTree<E>
{
    //region Private Member Variables
    protected boolean addReturn;
    protected E deleteReturn;

    //endregion

    //region Public Methods
    @Override
    public boolean add(E item)
    {
        return false;
    }

    @Override
    public boolean contains(E target)
    {
        return false;
    }

    @Override
    public E find(E target)
    {
        return find(root, target);
    }

    @Override
    public E delete(E target)
    {
        return null;
    }

    @Override
    public boolean remove(E target)
    {
        return false;
    }
    //endregion

    //region Private Methods

    private E find(Node<E> localRoot, E target)
    {
        if(localRoot == null)
        {
            return null;
        }

        int compResult = target.compareTo(localRoot.data);

        if(compResult == 0)
        {
            return localRoot.data;
        }
        else if(compResult < 0)
        {
           return find(localRoot.left, target);
        }
        else
        {
            return find(localRoot.right, target);
        }
    }

    private Node<E> delete(Node<E> localRoot, E target)
    {
        if(localRoot == null)
        {
            //item isnt in the tree
            deleteReturn = null;
            return localRoot;
        }

        int compResult = target.compareTo(localRoot.data);

        if(compResult < 0)
        {
            localRoot.left = delete(localRoot.left,target);
            return localRoot;
        }

        else if(compResult > 0)
        {
            localRoot.right = delete(localRoot.right,target);
            return localRoot;
        }

        else
        {
            deleteReturn = localRoot.data;

            if(localRoot.left == null)
            {
                //if there is no left child, return the right child
                return  localRoot.right;
            }

            else if(localRoot.right == null)
            {
                //if there is no right child, return the left child
                return localRoot.left;
            }

            else
            {
                //Node being deleted has two children, replace the data with the inorder predecessor
                if(localRoot.left.right == null)
                {
                    localRoot.data = localRoot.left.data;

                    localRoot.left = localRoot.left.left;

                    return localRoot;
                }

                else
                {
                    localRoot.data = findLargestChild(localRoot.left);
                    return localRoot;
                }
            }
        }
    }

    //endregion

    private E findLargestChild(Node<E> parent)
    {
        if(parent.right.right == null)
        {
            E returnValue = parent.right.data;
            parent.right = parent.right.left;
            return returnValue;
        }
        else
        {
            return findLargestChild(parent.right);
        }
    }
}
