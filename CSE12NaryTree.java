/**
 * Name: Kathy Chen
 * ID: A17030814
 * Email: ktchen@ucsd.edu
 * Sources used: 
 * https://docs.oracle.com/javase/10/docs/api/java/util/Queue.html
 * https://docs.oracle.com/javase/10/docs/api/java/util/PriorityQueue.html
 * https://learn.zybooks.com/zybook/UCSDCSE12Winter2022/chapter/6/section/2?content_resource_id=56801376
 * 
 * File description: 
 * This is the CSE12NaryTree java file for the CSE 12 programming final. It 
 * contains the generic CSE12NaryTree class with a protected class Node within.
 */

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * A CSE12NaryTree class holds references to the root, the size, and the max
 * num of children allowed per node. It contains helper methods utilized within
 * the core methods, methods to modify the tree as well as a method to return a
 * sorted traversal of the tree. Additionally, it also has a protected class 
 * Node within.
 */
public class CSE12NaryTree<E extends Comparable<E>> {
    
    /**
     * This inner class encapsulates the data and children for a Node.
     * Do NOT edit this inner class.
     */
    protected class Node{
        E data;
        List<Node> children;
    
        /**
         * Initializes the node with the data passed in
         * 
         * @param data The data to initialize the node with
         */
        public Node(E data) {
            this.data = data;
            this.children = new ArrayList<>();
        }
    
        /**
         * Getter for data
         * 
         * @return Return a reference to data
         */
        public E getData() {
            return data;
        }

        /**
         * Setter for the data
         * 
         * @param data Data that this node is set to
         */
        public void setData(E data) {
            this.data = data;
        }

        /**
         * Getter for children
         * 
         * @return reference to the list of children
         */
        public List<Node> getChildren() {
            return children;
        }

        /**
         * Returns the number of children
         * 
         * @return number of children
         */
        public int getNumChildren() {
            // assume there are no nulls in list
            return children.size();
        }

        /**
         * Add the given node to this node's list of children
         * 
         * @param node The node to add
         */
        public void addChild(Node node) {
            children.add(node);
        }
    
    }
    
    Node root;
    int size;
    int N;

    /**
     * Constructor that initializes an empty N-ary tree, with the given N
     * 
     * @param N The N the N-tree should be initialized with
     */
    public CSE12NaryTree(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.root = null;
        this.size = 0;
        this.N = N;
    }

    /**
     * Add new node containing element to the Nary tree in level order
     * @param element - data of new node to be added to Nary Tree
     */
    public void add(E element) {
        //throw exception if element is null
        if(element == null){
            throw new NullPointerException();
        }
        
        //if tree empty, set root to new element
        if(this.size == 0){
            this.root = new Node(element);
            this.size++;
            return;
        }

        //create queue for traversal
        Queue<Node> traversal = new LinkedList<Node>();
        traversal.add(this.root);


        //check every element of NaryTree until element found or none are equal
        while(!traversal.isEmpty()){
            Node curr = traversal.poll();
            
            //Add node where there is room in children level
            if(curr.getNumChildren()< this.N){
                curr.addChild(new Node(element));
                continue;
            }
            //if element not found yet, check children of curr
            else{
                for(Node child: curr.getChildren()){
                    traversal.add(child);
                }
            }
        }
        //update size var
        this.size++;
    }

    /**
     * Check whether element is in the Nary tree 
     * @param element - element to be checked if in Nary tree
     */
    public boolean contains(E element) {
        //throw exception if element is null
        if(element == null){
            throw new NullPointerException();
        }

        //create queue for traversal
        Queue<Node> traversal = new LinkedList<Node>();
        traversal.add(this.root);

        //if tree empty, return false
        if(this.size == 0){
            return false;
        }

        //check every element of NaryTree until element found or none are equal
        while(!traversal.isEmpty()){
            Node curr = traversal.poll();
            if(curr.getData().equals(element)){
                return true;
            }
            //if element not found yet, check children of curr
            if(curr.getNumChildren()!=0){
                for(Node child: curr.getChildren()){
                    traversal.add(child);
                }
            }
        }
        return false;
    }

    /**
     * Return an ArrayList of all elements sorted in ascending order
     * @return ArrayList of elements in ascending order
     */
    public ArrayList<E> sortTree(){
        //return empty ArrayList if tree is empty
        ArrayList<E> ret = new ArrayList<E>();
        if(this.size == 0){
            return ret;
        }
        if(this.size == 1){
            ret.add((E)this.root.getData());
            return ret;
        }

        //create queue for traversal, and tree to be heapSorted
        Queue<Node> traversal = new LinkedList<Node>();
        Node curr = this.root;
        traversal.add(curr);
        PriorityQueue<E> heapTree = new PriorityQueue<E>();

        //add all elements of NaryTree to tree to be Heap Sorted
        while(!traversal.isEmpty()){
            Node node = traversal.poll();
            heapTree.add(node.getData());

            if(curr.getNumChildren()!=0){
                for(Node child: node.getChildren()){
                    traversal.add(child);
                }
            }
        }
        
        //add values from heapTree to arrayList
        for(E values: heapTree){
            ret.add(values);
        }

        //sort arrayList by ascending order then return
        this.heapSort(ret, ret.size());
        return ret;
    }

    /**
     * Private helper method to heapSort arrayList in ascending order
     * @param nums - arrayList to be sorted in ascending order
     * @param numsSize - size of arrayList to be sorted
     */
    private void heapSort(ArrayList<E> nums, int numsSize){
        //percolate down through each level
        for(int i = numsSize / 2 - 1; i >= 0; i--){
            MinHeapPercolateDown(i, nums, numsSize);
        }
        //remove values from heap
        for(int i = numsSize - 1; i > 0; i--){
            E temp = nums.get(0);
            nums.set(0, nums.get(i));
            nums.set(i, temp);
            MinHeapPercolateDown(0, nums, i);
        }
    }

    /**
     * Private helper method to percolate down values to maintain min-heap
     * properties
     * @param nodeIndex - index of element to be percolated down
     * @param nums - arrayList of elements to be percolated through
     * @param numsSize - size of arrayList to be percolated through
     */
    private void MinHeapPercolateDown(int nodeIndex, ArrayList<E> nums, int numsSize){
        int childIndex = 2 * nodeIndex + 1;
        E value = nums.get(nodeIndex);
        
        while (childIndex < numsSize) {
            // Find the min among the node and all the node's children
            E minValue = value;
            int minIndex = -1;
            for (int i = 0; i < 2 && i + childIndex < numsSize; i++) {
                if (nums.get(i + childIndex).compareTo(minValue) > 0) {
                    minValue = nums.get(i + childIndex);
                    minIndex = i + childIndex;
                }
            }
        
            if (minValue == value) {
                return;
            }
            // Swap values of nodeIndex with the min value
            else {
                E temp = nums.get(nodeIndex);
                nums.set(nodeIndex, nums.get(minIndex));
                nums.set(minIndex, temp);
                nodeIndex = minIndex;
                childIndex = 2 * nodeIndex + 1;
            }
        }
    }
    
}
