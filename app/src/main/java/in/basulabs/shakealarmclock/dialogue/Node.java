package in.basulabs.shakealarmclock.dialogue;


import in.basulabs.shakealarmclock.R;

import java.util.HashMap;
import java.util.Map;

public class Node {
    private String nodeName;
    private String nodeText;
    private Map<String, String> choices; // < válasz, node neve >
    private int speechUri;

    public Node() {
        nodeName = "root";
        nodeText = "node Text";
        choices = new HashMap<>();
        speechUri = R.raw.rehehehe;
    }
    public Node(String nodeName, String nodeText, int speechUri) {
        this.nodeName = nodeName;
        this.nodeText = nodeText;
        choices = new HashMap<>();
        this.speechUri = speechUri;
    }

    public void addChoice(String key, String value) {
        choices.put(key, value);
    }

    public String getNodeText() {
        return nodeText;
    }
    public String getNodeName() {
        return nodeName;
    }
    public Map<String, String> getChoices() {
        return choices;
    }
    public int getSpeechUri() {
        return speechUri;
    }
}
