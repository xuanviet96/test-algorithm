import java.util.HashMap;
import java.util.Map;

public class Main {
    public static class Node {
        int val;
        Node next;
        Node(int val) {
            this.val = val;
            this.next = null;
        }
        Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }
    public static int longestString(String input) {
        char[] inputChar = input.toCharArray();
        int maxLength = 0;
        String output = "";
        for(int i=0; i < inputChar.length; i++) {
            if(output.contains(String.valueOf(inputChar[i]))) {
                output = output.substring(output.indexOf(inputChar[i]) + 1);
            }
            output = output + inputChar[i];
            maxLength = Math.max(output.length(), maxLength);
        }
        return maxLength;
    }

    public static String atm(int input) {
        int temp =input;
        int[] moneyType = {2000, 500, 200, 100};
        int[] notes = {5, 10, 10, 10};
        Map<Integer, Integer> result = new HashMap<>();
        int note = 0;
        String output = "";

        if(input % 100 != 0) {
            return "Not a valid amount";
        }
        for(int i =0; i< moneyType.length; i++) {
            if(temp >= moneyType[i]) {
                note = temp / moneyType[i];
                if(note <= notes[i]) {
                    temp = temp - note * moneyType[i];
                    result.put(moneyType[i], note);
                } else {
                    result.put(moneyType[i], notes[i]);
                    temp = temp - notes[i] * moneyType[i];
                }
            }
        }
        if(temp != 0) {
            return "Amount not available in ATM";
        }
        for(Map.Entry<Integer, Integer> entry : result.entrySet()) {
            output += entry.getValue() + " notes of " + entry.getKey();
        }
        return output;
    }
    public static String intToRoman(int num) {
        String[] romans = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
        int[] values = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        int index = values.length - 1;
        String output = "";
        while(num > 0) {
            while(values[index] <= num) {
                output = output + romans[index];
                num = num - values[index];
            }
            index--;
        }
        return output;
    }
    public static Node recursivelyReversedLinkedList(Node head){
        //base condition
        if(head == null) {
            return null;
        }
        Node nextNode = head.next;
        //one node condition
        if( head.next == null) {
            return head;
        }
        // general condition
        Node newHead = recursivelyReversedLinkedList(nextNode);
        nextNode.next = head;
        head.next = null;
        return newHead;
    }
    public static boolean hasCycle(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            // move slow reference by 1 node
            slow = slow.next;
            // move fast reference by 2 nodes
            fast = fast.next.next;
            // if two references meet
            // then there is a loop
            if (fast == slow)
                return true;
        }
        return false;
    }
    public static void printLinkedList(Node head) {
        //head is not null
        while(head != null) {
            System.out.printf("%d ", head.val);
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        // connect each node of linked list to next node
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        //make loop in linked list
//        n4.next = n2;
        //print ont all linked list before
        printLinkedList(n1);
        //TODO: test reversed linked list
        Node n5 = recursivelyReversedLinkedList(n1);
        printLinkedList(n5);
        //TODO: test has cycle in linked list
        System.out.println(hasCycle(n1));

        //TODO: test int to Roman function
        System.out.println(intToRoman(1994));
        //TODO: test longest string function
        System.out.println(longestString("abcabcbb"));
    }
}