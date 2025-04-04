package org.example.suanfa.底层.Hashmap;

public class BreadHashMap <K,V> {
    // 初始容量
    private static final int DEFAULT_CAPACITY = 16;
    // 负载因子
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    // 哈希桶数组
    private Node<K, V>[] table;
    // 当前元素数量
    private int size;
    // 扩容阈值
    private int threshold;
    // 链表节点类
    static class Node<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
    private BreadHashMap(){
        table = (Node<K, V>[]) new Node[DEFAULT_CAPACITY];
        threshold = (int) (DEFAULT_LOAD_FACTOR * DEFAULT_CAPACITY);
    }
    public int hash(K key) {
        if (key == null) return 0;
        int h = key.hashCode();
        return h ^ (h >>> 16);
    }


    public void put(K key, V value) {
        // 延迟初始化哈希表
        if (table == null) {
            table = (Node<K, V>[]) new Node[DEFAULT_CAPACITY];
            threshold = (int) (DEFAULT_CAPACITY * DEFAULT_LOAD_FACTOR); // 计算扩容阈值
        }
        int hash = hash(key);
        int index = hash & (table.length - 1); // 计算桶位置
        // 处理哈希冲突（链表法）
        Node<K, V> node = table[index];
        Node<K, V> prev = null;
        while (node != null) {
            // 键已存在则更新值
            if (node.hash == hash && (node.key == key || key.equals(node.key))) {
                node.value = value;
                return; // 更新后直接返回
            }
            prev = node;
            node = node.next;
        }
        // 键不存在时插入链表头部（头插法）
      Node<K, V> newNode = new Node<>(hash, key, value, null);
        if (prev == null) {
            table[index] = newNode;  // 桶为空，直接作为头节点
        } else {
            prev.next = newNode;    // 链接到链表尾部
        }
        size++;
        // 检查是否需要扩容
        if (size > threshold) {
            resize();
        }
    }
    /**
     * 哈希表扩容
     */
    private void resize() {
        int newCapacity = table.length * 2;
        Node<K, V>[] newTable = (Node<K, V>[]) new Node[newCapacity];
        // 重新哈希所有节点
        for (Node<K, V> head : table) {
            while (head != null) {
                Node<K, V> next = head.next;
                int newIndex = head.hash & (newCapacity - 1);
                head.next = newTable[newIndex]; // 头插法
                newTable[newIndex] = head;
                head = next;
            }
        }
        table = newTable;
        threshold = (int) (newCapacity * DEFAULT_LOAD_FACTOR);
    }
}