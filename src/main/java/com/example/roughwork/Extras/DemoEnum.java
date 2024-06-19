package com.example.roughwork.Extras;

import java.util.Scanner;

public class DemoEnum {

    public enum MessageType {
        A(Priority.HIGH),
        B(Priority.MEDIUM),
        C(Priority.LOW),
        D(Priority.LOW);

        private Priority priority;

        MessageType(Priority priority) {
            this.priority = priority;
        }

        public Priority getPriority() {
            return priority;
        }
    }

    public enum Priority {
        HIGH, MEDIUM, LOW
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Please, enter message type to check its priority: ");
            String userInput = sc.next();

            if (isValidMessageType(userInput)) {
                char messageTypeChar = userInput.toUpperCase().charAt(0);
                MessageType messageType = getMessageType(messageTypeChar);
                System.out.println("Priority: " + messageType.getPriority());
                break;
            } else {
                System.out.println("Please, enter a valid message type. Only 'A', 'B', 'C', or 'D' are allowed.");
            }
        }
    }

    private static boolean isValidMessageType(String userInput) {
        return userInput.length() == 1 && "ABCD".contains(userInput.toUpperCase());
    }

    private static MessageType getMessageType(char messageTypeChar) {
        for (MessageType m : MessageType.values()) {
            if (m.name().charAt(0) == messageTypeChar) {
                return m;
            }
        }
        return null;
    }
}
