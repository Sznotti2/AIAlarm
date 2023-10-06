package in.basulabs.shakealarmclock.dialogue.ai;

import android.content.Context;

import in.basulabs.shakealarmclock.R;
import in.basulabs.shakealarmclock.dialogue.Graph;
import in.basulabs.shakealarmclock.dialogue.Node;
import in.basulabs.shakealarmclock.dialogue.Selector;

public class Doctor extends NPC {
    private Graph graph;

    public Doctor() {
        initalise();
    }

    public void initalise() {
        graph = new Graph("Keltegetés");


        graph.addNode(new Node());

        graph.addNode(new Node("jol aludt", "Ezt örörmmel hallom, valószínűleg sok mindent jól csináltál tegnap.", R.raw.faaaaaart));
        graph.addNode(new Node("rosszul aludt", "Próbálj meg egy azon időpontban felkelni minden nap, miután ezt a szervezeted megszokja -ami nagyjából 3 nap- autómatikusan akkor leszel fáradt amennyi alvásra szükséged van. Például ha 7-kor kelsz 10-11 felé már határozottan érzed majd az ágy hívó szavát, he egy nehéz nap után lehet hamarabb is.", R.raw.faaaaaart));
        graph.addNode(new Node("elment", "\"Nincs mit megjavítani azon ami nem ment tönkre.\" A legtöbb ember az átlagosat rossznak gondolja, pedig az maga az optimum.", R.raw.faaaaaart));

        graph.addNode(new Node("lehet felkel", "Hogy érzed fel tudsz kelni magadtól vagy kell még egy kis noszogatás?", R.raw.fart));
        graph.addNode(new Node("noszogatás", "Gyerünk gyerünk ki az ágyból! Menj ki a napra az segít, de figyelni foglak!", R.raw.fart));

        graph.addNode(new Node("exit", "Itt a vége fuss el véle.", R.raw.fart)); // leaf node

        graph.createLink("root", "Igen", "jol aludt");
        graph.createLink("root", "Nem", "rosszul aludt");
        graph.createLink("root", "Elment", "elment");

        graph.createLink("jol aludt", "Tovább", "lehet felkel");
        graph.createLink("rosszul aludt", "Tovább", "lehet felkel");
        graph.createLink("elment", "Tovább", "lehet felkel");

        graph.createLink("lehet felkel", "Noszogass", "noszogatás");
        graph.createLink("lehet felkel", "Igen fel tudok", "exit");

        graph.createLink("noszogatás", "Vége", "exit");
    }
    public Graph getGraph() {
        return graph;
    }
}
