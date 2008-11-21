package org.curlybraces.synapse.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.curlybraces.synapse.Message;
import org.curlybraces.synapse.Node;
import org.curlybraces.synapse.NodeExecutor;
import org.curlybraces.synapse.NodeListener;
import org.curlybraces.synapse.Synapse;
import org.curlybraces.synapse.SynapseJettyHandler;
import org.curlybraces.synapse.Update;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;

public class UI
{
    private final boolean isMac;
    
    public UI()
    {
        String os = System.getProperty("os.name").toLowerCase();
        this.isMac = os.startsWith("mac os x");
    }
    
    public void start() throws Exception
    {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        
        if (isMac)
        {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
        }

        final Node node = new Node();
        
        final Server server = new Server();
        SocketConnector connector = new SocketConnector();
        
        // Set some timeout options to make debugging easier.
        connector.setMaxIdleTime(1000 * 60 * 60);
        connector.setSoLingerTime(-1);
        connector.setPort(8888);
        server.setConnectors(new Connector[] { connector });

        server.addHandler(new SynapseJettyHandler(node));
        server.start();
        
        final JFrame frame = new JFrame("Synapse");
        
        node.addListener(new NodeListener()
        {
            @Override
            public void update(Message missive)
            {
                JOptionPane.showMessageDialog(frame, "Eggs are not supposed to be green.");
            }
        });
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add the ubiquitous "Hello World" label.
        final JTextField field = new JTextField();
        JButton button = new JButton("Update");
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                String text = field.getText();
                
                Message missive = new Message();
                
                missive.setId(UUID.randomUUID());
                missive.setDate(new Date());
                missive.setProfileId(UUID.randomUUID());
                missive.setText(text);
                
                Update update = new Update(missive);
                Synapse synapse = new Synapse(update);
                new NodeExecutor(node, synapse).execute();
            }
        });
        
        frame.getContentPane().add(field, BorderLayout.CENTER);
        frame.getContentPane().add(button, BorderLayout.LINE_END);

        frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent event)
            {
                try
                {
                    server.stop();
                }
                catch (Exception e)
                {
                }
            }
        });

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] args) throws Exception
    {
        new UI().start();
    }
}