package me.violantic.api.slack;


import me.violantic.api.slack.api.Slack;

/**
 * @author Ethan
 *
 * This class just tests the use of the API.
 */
public class SlackAPI {

    private static Slack slack;

    public static void main(String[] args) {
        init();
        slack.send("#general", "Hello, I am Violantic, and this is testing the Slack API!");
    }

    private static void init() {
        slack = new Slack("VioBot", "tesing-key-here", "Violantic");
    }

}
