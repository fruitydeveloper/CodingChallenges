    // Complete the reverse function below.

    /*
     * For your reference:
     *
     * DoublyLinkedListNode {
     *     int data;
     *     DoublyLinkedListNode next;
     *     DoublyLinkedListNode prev;
     * }
     *
     */
    static DoublyLinkedListNode reverse(DoublyLinkedListNode head) {
        if(head == null) return null;

        DoublyLinkedListNode newHead = head;
        DoublyLinkedListNode node = head;
        while(node != null) {
            DoublyLinkedListNode temp = node.prev;
            node.prev = node.next;
            node.next = temp;
            newHead = node;
            node = node.prev;
        }

        return newHead;
    }