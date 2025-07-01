package org.example.suanfa.leetcode.lianbiao;

import java.util.HashMap;
import java.util.Map;

/**
 * @author heweitao538 2025/7/1
 */
public class Lru {
    static class DLinkedNode{
        private String key;
        private String value;
        DLinkedNode pre;
        DLinkedNode next;
    }
     int capacity=0;
     int curCnt=0;
    DLinkedNode head,tail;
     Map<String,DLinkedNode> cache = new HashMap<>();
     
    public Lru(int capacity){
        this.curCnt=0;
        this.capacity=capacity;

        this.head=new DLinkedNode();
        this.tail=new DLinkedNode();
        
        head.pre=null;
        head.next=tail;
        tail.pre=head;
        tail.next=null;
    }
    
    public String get(String key){
        if(cache.get(key)==null){
            return null;
        }else{
            moveToFirst(key);
            return cache.get(key).value;
        }
    }

    public void set(String key,String value){
      DLinkedNode exit = cache.get(key);
      if(exit==null){
          DLinkedNode newNode=new DLinkedNode();
          newNode.key=key;
          newNode.value=value;
          cache.put(key,newNode);
          addToFirstNode(newNode);
          curCnt++;
          if(curCnt>capacity){
             DLinkedNode last = removeLast();
             cache.remove(last.key);
              curCnt--;
          }

      }else{
          cache.get(key).value=value;
          moveToFirst(key);
      }
    }

    private void moveToFirst(String key) {
       deleteNode(cache.get(key));
       addToFirstNode(cache.get(key));
    }

    private void addToFirstNode(DLinkedNode newNode) {
        newNode.pre = head;
        newNode.next = head.next;

        head.next.pre = newNode;
        head.next = newNode;
    }

    private void deleteNode(DLinkedNode node){
       DLinkedNode pre=node.pre;
       DLinkedNode next=node.next;

       pre.next=next;
       next.pre=pre;
    }
    private DLinkedNode removeLast() {
        DLinkedNode last=tail.pre;
        deleteNode(last);
        return last;
    }

    public static void main(String[] args) {
        Lru lru=new Lru(3);
        lru.set("1","1");
        lru.set("2","2");
        lru.set("3","3");
        lru.set("4","4");
        System.out.println(lru.get("1"));
    }

}
