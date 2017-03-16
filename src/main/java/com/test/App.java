package com.test;

import com.test.core.Entry;
import com.test.core.EntryFormatter;
import com.test.core.TimelinePresenter;

import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {

    private final Scanner in;

    private App(Scanner in) {
        this.in = in;
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Type 'quit' to quit application\n" +
                        "Make sure to separate keywords and user names using spaces\n" +
                        "posting: <user name> -> <message>\n" +
                        "reading: <user name>\n" +
                        "following: <user name> follows <another user>\n" +
                        "wall: <user name> wall" + "\n");
        App app = new App(in);
        app.start();
    }

    private void start() {
        String input = null;
        EntryFormatter wallFormatter = new WallEntryFormatter();
        EntryFormatter timeLineFormatter = new TimelineEntryFormatter();
        Map<String, TwitterlikeUser> users = new HashMap<>();
        while (!"quit".equals(input)) {
            System.out.print("> ");
            input = in.nextLine();
            String[] inputArr = splitInput(input);
            String userName = inputArr[0];
            TwitterlikeUser user = getOrCreateUser(userName, users);
            if (inputArr.length == 1) {
                printEntries(user.getTimeline(), timeLineFormatter);
            } else if (inputArr.length == 2 && "wall".equalsIgnoreCase(inputArr[1])) {
                printEntries(user.getWall(), wallFormatter);
            } else if (inputArr.length == 3 && "follows".equalsIgnoreCase(inputArr[1])) {
                String anotherUserName = inputArr[2];
                TwitterlikeUser anotherUser = getOrCreateUser(anotherUserName, users);
                user.follows(anotherUser);
            } else { //the only option left is publish '->'
                String message = inputArr[1];
                user.publish(message);
            }
        }
    }

    private String[] splitInput(String input) {
        if (input.contains("wall") || input.contains("follows")) {
            return input.split(" ");
        }
        if (input.contains("->")) {
            return input.split(" -> ");
        }
        return new String[]{ input.trim() };
    }

    private TwitterlikeUser getOrCreateUser(String userName, Map<String, TwitterlikeUser> users) {
        return users.computeIfAbsent(userName, TwitterlikeUser::new);
    }

    private void printEntries(List<Entry> entryList, EntryFormatter formatter) {
        TimelinePresenter<PrintStream> presenter = new SortedPrintStreamTimelinePresenter(entryList, formatter);
        presenter.present(System.out);
    }
}
