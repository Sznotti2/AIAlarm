package in.basulabs.shakealarmclock.dialogue;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import in.basulabs.shakealarmclock.R;
import in.basulabs.shakealarmclock.dialogue.ai.Doctor;
import in.basulabs.shakealarmclock.dialogue.ai.NPC;

import java.util.Map;

public class Dialogus extends AppCompatActivity {
    private Graph graph;
    private Node activeNode;
    private MediaPlayer mediaPlayer;
    private NPC npc;
    private TextView textView;
    private LinearLayout buttonContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogus);

        npc = new Doctor();
        graph = npc.getGraph();

        activeNode = graph.getNode("root");

        // setUpGraph();

        playSound();

        setUpScreen();
    }

    void playSound() {
        mediaPlayer = MediaPlayer.create(getApplicationContext(), activeNode.getSpeechUri());
        mediaPlayer.start();
    }
    void setUpScreen() {
        textView = findViewById(R.id.textHolder);
        buttonContainer = findViewById(R.id.buttonContainer);

        // currenttly active node's text
        textView.setText(activeNode.getNodeText());

        playSound();

        // currenttly active node's choices
        if (buttonContainer.getChildCount() > 0) { // if the parent isn't empty
            buttonContainer.removeAllViewsInLayout(); // remove previous Views
        }

        if (activeNode.getChoices().size() > 0) { // if active ins't a leaf node
            for (Map.Entry<String, String> choice : activeNode.getChoices().entrySet()) {
// https://stackoverflow.com/questions/3995215/add-and-remove-views-in-android-dynamically
                Button button = new Button(this); // create button
                // set Button width to match_parent
                textView.post(new Runnable() {
                    @Override
                    public void run() {
                        button.setWidth(textView.getWidth());
                    }
                });

                button.setText(choice.getKey());

                // adding onClickListener
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        activeNode = graph.getNode(choice.getValue()); // find the next node
                        setUpScreen();
                    }
                });

                buttonContainer.addView(button);
            }
        } else { // if leaf node
            Button button = new Button(this);
            textView.post(new Runnable() {
                @Override
                public void run() {
                    button.setWidth(textView.getWidth());
                }
            });

            button.setText("Exit");

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }
}