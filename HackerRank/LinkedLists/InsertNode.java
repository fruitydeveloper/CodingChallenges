    /*
     * For your reference:
     *
     * SinglyLinkedListNode {
     *     int data;
     *     SinglyLinkedListNode next;
     * }
     *
     */
    static SinglyLinkedListNode insertNodeAtPosition(SinglyLinkedListNode head, int data, int position) {
        SinglyLinkedListNode node = new SinglyLinkedListNode(data);
        if(head == null) {
            return node;
        }
        SinglyLinkedListNode prev = head;
        while(--position > 0 && prev != null) {
            prev = prev.next;
        }
        node.next = prev.next;
        prev.next = node;

        return head;
    }