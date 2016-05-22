package ds.bst;

import java.lang.Comparable;
import ds.list.SingleLinkedList;

/** Binary Search Tree implementation for Dictionary ADT */
public class BST<Key extends Comparable<? super Key>, E>
implements Dictionary<Key, E> {
	
	private BSTNode<Key,E> root; // Root of the BST
	int nodecount;             // Number of nodes in the BST

	/** Constructor */
	public BST() { 
		root = null;
		nodecount = 0;
	}

	/** Reinitialize tree */
	public void clear() { 
		root = null;
		nodecount = 0;
	}

	/** Insert a record into the tree.
      @param k Key value of the record.
      @param e The record to insert. */
	public void insert(Key k, E e) {
		root = inserthelp(root, k, e);
		nodecount++;

	}

	/** Remove a record from the tree.
      @param k Key value of record to remove.
      @return The record removed, null if there is none. */
	public E remove(Key k) {
		E temp = findhelp(root, k);
		if(temp != null){
			root = removehelp(root,k);
			nodecount--;
		}
		return temp;
	}

	/** Remove and return the root node from the dictionary.
      @return The record removed, null if tree is empty. */
	public E removeAny() {
		if (root == null) return null;
		E temp = root.element();
		root = removehelp(root, root.key());
		nodecount--;
		return temp;
	}

	/** @return Record with key value k, null if none exist.
      @param k The key value to find. */
	public E find(Key k) { 
		return findhelp(root, k);
	}

	/** @return The number of records in the dictionary. */
	public int size() { 
		return nodecount;
	}
	private E findhelp(BSTNode<Key,E> rt, Key k) {
		if(rt == null) return null;
		if( rt.key().compareTo(k) >0 ) return findhelp((BSTNode<Key, E>) rt.left(), k);
		else if(rt.key().compareTo(k) ==0) return rt.element();
		else return findhelp((BSTNode<Key, E>) rt.right(), k);
	}

	public SingleLinkedList<Key> inorder(SingleLinkedList<Key> list) { 
		inorderHelper(root, list); 
		return list;
	}
	
	private void inorderHelper(BSTNode<Key, E> rt, SingleLinkedList<Key> list){
		if (rt==null) return;
		inorderHelper(rt.left(), list);	
		list.append(rt.key());
		inorderHelper(rt.right(), list);			
	}
	
	public SingleLinkedList<Key> rangeSearch(Key from, Key to) { 
		return rangeSearchHelper(root, from, to); 
	}
	
	private SingleLinkedList<Key> rangeSearchHelper(BSTNode<Key, E> rt, Key from, Key to){
		SingleLinkedList<Key> list = inorder(new SingleLinkedList<Key>());
		SingleLinkedList<Key> rangeList = new SingleLinkedList<Key>();
		
		if(list.length() == 0) return rangeList;
		while( !(list.getValue().equals(from)) && list.currPos()<list.length()) list.next();
		if (list.currPos() == list.length()) return rangeList; //from값이 존재하지 않을 때
		while(!(list.getValue().equals(to)) && list.currPos()<=list.length()){
			rangeList.append(list.getValue());
			list.next();
		}	
		return rangeList;
	}
	
	
	/** @return The current subtree, modified to contain
   the new item */
	private BSTNode<Key,E> inserthelp(BSTNode<Key,E> rt, Key k, E e) {
		if (rt == null) return new BSTNode<Key, E>(k, e);
		if (rt.key().compareTo(k) >0) rt.setLeft(inserthelp((BSTNode<Key, E>) rt.left(), k, e));
		else rt.setRight(inserthelp((BSTNode<Key, E>) rt.right(), k, e));
		return rt;
	}
	
	/** Remove a node with key value k
    @return The tree with the node removed */
	private BSTNode<Key,E> removehelp(BSTNode<Key,E> rt,Key k) {
		if (rt == null) return null;
		if (rt.key().compareTo(k)>0) rt.setLeft(removehelp((BSTNode<Key, E>) rt.left(),k));
		else if (rt.key().compareTo(k)<0) rt.setRight(removehelp((BSTNode<Key, E>) rt.right(),k));
		else{
			if(rt.left() == null) return (BSTNode<Key, E>) rt.right();
			else { //two children
				BSTNode<Key, E> temp = getmin((BSTNode<Key, E>) rt.right());
				rt.setKey(temp.key());
				rt.setRight(deletemin((BSTNode<Key, E>) rt.right()));
			}
		}
		return rt;	
	}
	
	public String first(){
		BSTNode<Key,E> firstNode = getmin(root);
		return (String) firstNode.key();
	}
	
	private BSTNode<Key,E> getmin(BSTNode<Key,E> rt) {
		if(rt.left() == null ) return rt;
		return getmin((BSTNode<Key, E>) rt.left());
	}
	
	private BSTNode<Key,E> deletemin(BSTNode<Key,E> rt) {
		if(rt.left()==null) return (BSTNode<Key, E>) rt.right();
		rt.setLeft(deletemin((BSTNode<Key, E>) rt.left()));
		return rt;
	}
	
	public String last(){
		BSTNode<Key,E> lastNode = getmax(root);
		return (String) lastNode.key();
	}
	
	private BSTNode<Key,E> getmax(BSTNode<Key,E> rt) {
		if(rt.right() == null ) return rt;
		return getmax((BSTNode<Key, E>) rt.right());
	}
}