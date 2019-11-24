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
    static DoublyLinkedListNode sortedInsert(DoublyLinkedListNode head, int data) {
        DoublyLinkedListNode node = new DoublyLinkedListNode(data);
        if(head == null) return node;
        if(head.data > data) {
            node.next = head;
            head.prev = node;
            return node;
        }
        DoublyLinkedListNode prev = head;
        while(prev != null && prev.next != null &&  prev.data < data) {
            prev = prev.next;
        }
        if(prev != null && prev.data > node.data) prev = prev.prev;
        if(prev != null && prev.next != null) {
            prev.next.prev = node;
            node.next = prev.next;
        }
        prev.next = node;
        node.prev = prev;
        
        return head;
    }