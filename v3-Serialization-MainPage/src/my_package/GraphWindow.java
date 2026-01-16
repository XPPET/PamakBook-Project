package my_package;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.function.Function;

import javax.swing.JFrame;
import javax.swing.JLabel;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.shortestpath.DistanceStatistics;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import org.apache.commons.collections15.Transformer;

public class GraphWindow extends JFrame {
    public GraphWindow(ArrayList<User> allUsers) {
        UndirectedSparseGraph<User, String> g = new UndirectedSparseGraph<>();
        
        for (User u : allUsers) {
            g.addVertex(u);


            for (User friend : u.getFriends()) {
                String edgeId = u.getName().compareTo(friend.getName()) < 0 ? 
                                u.getName() + "-" + friend.getName() : 
                                friend.getName() + "-" + u.getName();
                if (!g.containsEdge(edgeId)) {
                    g.addEdge(edgeId, u, friend); 
                }
            }
        }

        double diameter = DistanceStatistics.diameter(g); 

        CircleLayout<User, String> layout = new CircleLayout<>(g);
        VisualizationViewer<User, String> vv = new VisualizationViewer<>(layout);
        vv.setPreferredSize(new Dimension(550, 550));
        
        vv.getRenderContext().setVertexLabelTransformer(new Transformer<User, String>() {
            @Override
            public String transform(User user) {
                return user.getName(); // Επιστρέφει το όνομα του χρήστη ως label 
            }
        });
        
        vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);

        this.setTitle("Friend Graph");
        this.setLayout(new BorderLayout());
        this.add(vv, BorderLayout.CENTER);
        
        JLabel label = new JLabel("Diameter = " + diameter); 
        label.setHorizontalAlignment(JLabel.CENTER);
        this.add(label, BorderLayout.SOUTH);
        
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}