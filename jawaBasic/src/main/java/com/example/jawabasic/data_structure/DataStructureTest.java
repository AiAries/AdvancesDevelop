package com.example.jawabasic.data_structure;

class DataStructureTest {
    public static void main(String[] args) {
        LinkedA<String> linkedA = new LinkedA<>();
        linkedA.addLast(new String("66"));
        linkedA.addLast(new String("88"));
        linkedA.addLast(new String("99"));
        linkedA.printAll();
    }
   public static class LinkedA<E>{
        Node<E> head;
        public void addLast(E e){
            if (head ==null) {
                head = new Node<>(null, null);
            }
            Node<E> cur = new Node<>(null, null);
            cur = this.head;
            while (true) {
                if (cur.next == null) {
                    cur.next = new Node<>(e, null);
                    break;
                } else {
                    cur = cur.next;
                }
            }
        }
        public void printAll(){
            Node<E> cur = new Node<>(null, null);
            if (this.head == null || this.head.next == null) {
                return;
            }
            cur = this.head;
            while (cur.next != null) {
                cur =cur.next;
                System.out.println(cur.t);
            }
        }

        static class Node<T>{
            T t;
            Node<T> next;

            public Node(T t, Node<T> next) {
                this.t = t;
                this.next = next;
            }

            public T getT() {
                return t;
            }

            public void setT(T t) {
                this.t = t;
            }
        }
    }
    public interface ILinked<E>{
        void addLast(E e);
        void addFirst(E e);
        void add(int index);

        void remove(int index);
        void removeAll();

    }
}

