package in.basulabs.shakealarmclock.dialogue;

import java.util.ArrayList;

public class Graph {
    private String graphName;
    private ArrayList<Node> nodeList;

    public Graph() {
    }
    public Graph(String graphName) {
        this.graphName = graphName;
        this.nodeList = new ArrayList<>();
    }

    /**
     *
     * @param source
     * @param key
     * @param destination
     */
    public void createLink(String source, String key, String destination) {
        Node sourceNode = getNode(source);
        sourceNode.addChoice(key, destination);
    }


    public void addNode(Node node) {
        nodeList.add(node);
    }

    public Node getNode(String nodeName) {
        for (Node node : nodeList) {
            if (nodeName.equals(node.getNodeName())) {
                return node;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "graphName='" + graphName + '\'' +
                ", nodeList=" + nodeList +
                '}';
    }
}
