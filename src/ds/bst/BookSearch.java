package ds.bst;

import ds.list.SingleLinkedList;

public class BookSearch {
	private BST<String, String> bst;
	
	public BookSearch(){
		bst = new BST<String, String>();
	}
	
	public void add(String name, String location){
		bst.insert(name, location);		
		System.out.println("ADD: "+name+"/"+location );
	}
	
	public String remove(String name){
		String location = bst.remove(name);
		if(location == null) System.out.println("BookSearch can not find the book");
		else System.out.println("REMOVE: "+name);
		return location;
	}
	
	public String get(String name){
		String location= bst.find(name);
		if(location == null) System.out.println("BookSearch can not find the book");
		else System.out.println("GET: "+name+"/"+location);
		return location;
	}
	
	public int size(){
		System.out.println("CURRENT_SIZE: "+ bst.size());
		return bst.size();
	}
	
	public int order(){
		SingleLinkedList<String> nameList = bst.inorder(new SingleLinkedList<String>());
		int size = nameList.length();
		if(size == 0) System.out.println("BookSearch does not have any book");
		for(int i = 0; i<size; i++){
			System.out.println("ORDER: "+ nameList.getValue());
			nameList.next();	
		}
		return size;
	}
	
	public String first(){
		String first = bst.first();
		if(first == null)System.out.println("BookSearch does not have any book");
		else System.out.println("FIRST: "+first); 
		return first;
	}
	
	public String last(){
		String last = bst.last();
		if(last == null)System.out.println("BookSearch does not have any book");
		else System.out.println("LAST: "+last); 
		return last;
	}
	
	public int range(String from, String to){
		SingleLinkedList<String> rangeList = bst.rangeSearch(from, to);
		int size = rangeList.length();
		if(size == 0) System.out.println("BookSearch can not find the book");
		for(int i = 0; i<size; i++){
			System.out.println(rangeList.getValue());
			rangeList.next();
		}
		return size;
	}
	
}
