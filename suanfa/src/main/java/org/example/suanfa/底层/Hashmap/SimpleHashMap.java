package org.example.suanfa.底层.Hashmap;

/**
 * 建议hashmap实现
 *
 运算符	名称	描述	示例
 &	位与	两个操作数对应位都为1时，结果位才为1	5 & 3 → 1 (0101 & 0011 = 0001)
 ^	位异或	两个操作数对应位不同时，结果位为1	5 ^ 3 → 6 (0101 ^ 0011 = 0110)
 <<	左移	将左操作数的二进制位向左移动右操作数指定的位数，低位补0	5 << 1 → 10 (0101 << 1 = 1010)
 >>	右移	将左操作数的二进制位向右移动右操作数指定的位数，高位补符号位（算术右移）	-5 >> 1 → -3
 * @param <K>
 * @param <V>
 */
public class SimpleHashMap<K, V> {
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
    
    public SimpleHashMap() {
        table = (Node<K, V>[]) new Node[DEFAULT_CAPACITY];
        threshold = (int)(DEFAULT_CAPACITY * DEFAULT_LOAD_FACTOR);
    }
    
    // 计算key的哈希值
    private int hash(K key) {
        if (key == null) return 0;
        int h = key.hashCode();
        // 扰动函数，减少哈希冲突
        return h ^ (h >>> 16);
    }
    // 获取key对应的value
    //【&】对两个整数的二进制表示逐位比只有两个对应位都为1时，结果位才为1
    public V get(K key) {
        int hash = hash(key);
        int index = (table.length - 1) & hash;
        Node<K, V> node = table[index];
        while (node != null) {
            if (node.hash == hash && 
                (node.key == key || (key != null && key.equals(node.key)))) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    //插入
    public V put(K key, V value) {
        int hash = hash(key);
        int index = (table.length - 1) & hash;
        // 检查是否已存在相同key
        Node<K, V> node = table[index];
        Node<K, V> prev = null;  // 记录前驱节点

        while (node != null) {
            if (node.hash == hash &&
                    (node.key == key || (key != null && key.equals(node.key)))) {
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
            prev = node;
            node = node.next;
        }
        // 尾插法：新节点添加到链表末尾
        Node<K, V> newNode = new Node<>(hash, key, value, null);
        if (prev == null) {
            table[index] = newNode;  // 桶为空，直接作为头节点
        } else {
            prev.next = newNode;    // 链接到链表尾部
        }
        size++;
        if (size > threshold) resize();
        return null;
    }
    // 扩容方法
    private void resize() {
        Node<K, V>[] oldTable = table;
        int newCapacity = oldTable.length << 1; // 容量翻倍
        threshold = (int)(newCapacity * DEFAULT_LOAD_FACTOR);
        
        Node<K, V>[] newTable = (Node<K, V>[]) new Node[newCapacity];
        
        // 重新哈希所有元素
        for (int i = 0; i < oldTable.length; i++) {
            Node<K, V> node = oldTable[i];
            while (node != null) {
                Node<K, V> next = node.next;
                int newIndex = (newCapacity - 1) & node.hash;
                node.next = newTable[newIndex];
                newTable[newIndex] = node;
                node = next;
            }
        }
        table = newTable;
    }
    
    // 删除键值对
    public V remove(K key) {
        int hash = hash(key);
        int index = (table.length - 1) & hash;
        Node<K, V> node = table[index];
        Node<K, V> prev = null;
        while (node != null) {
            if (node.hash == hash && 
                (node.key == key || (key != null && key.equals(node.key)))) {
                if (prev == null) {
                    table[index] = node.next;
                } else {
                    prev.next = node.next;
                }
                size--;
                return node.value;
            }
            prev = node;
            node = node.next;
        }
        return null;
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
}