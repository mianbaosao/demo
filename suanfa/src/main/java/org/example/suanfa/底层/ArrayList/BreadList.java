package org.example.suanfa.底层.ArrayList;

import java.util.Arrays;

public class BreadList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public BreadList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    public BreadList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("初始容量必须大于0");
        }
        this.elements = new Object[initialCapacity];
    }

    // 添加元素
    public void add(E element) {
        ensureCapacity(size + 1);
        elements[size++] = element;
    }

    // 获取元素
    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
    }

    // 删除元素
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        checkIndex(index);
        E oldValue = (E) elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null; // 清除引用，帮助GC
        return oldValue;
    }

    // 扩容检查
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = elements.length + (elements.length >> 1); // 1.5倍扩容
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    // 索引检查
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    // 返回当前元素数量
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(elements, size));
    }
}