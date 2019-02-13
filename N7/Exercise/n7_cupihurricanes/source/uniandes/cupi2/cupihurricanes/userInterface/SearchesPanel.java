/*
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * University of the Andes
 * Department of Systems and Computer Engineering
 * Licensed under Academic Free License version 2.1
 * Project Cupi2 (http://cupi2.uniandes.edu.co)
 * Exercise: n7_Hurricane
 * Author: Andres Ortiz
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupihurricanes.userInterface;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * Panel where the searches regarding the system are found.
 */
public class SearchesPanel extends JPanel implements ActionListener {
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Constant for returning the highest velocity hurricane.
     */
    private static final String HIGHEST_VELOCITY = "Highest velocity";

    /**
     * Constant for returning the highest cost hurricane.
     */
    private static final String HIGHEST_DAMAGES = "Highest cost in damages";

    /**
     * Constant for returning the lowest cost hurricane.
     */
    private static final String LOWEST_COST = "Lowest cost in damages";

    /**
     * Constant for finding a hurricane.
     */
    private static final String SEARCH = "Search";


    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Principal window of the application.
     */
    private HurricaneInterface principal;

    // -----------------------------------------------------------------
    // Attributes of the interface
    // -----------------------------------------------------------------

    /**
     * Button to show the name of the hurricane with the highest velocity.
     */
    private JButton buttonHighestVelocity;

    /**
     * Button to show the name of the hurricane with the highest cost in damages.
     */
    private JButton buttonHighestCosts;

    /**
     * Button to show the name of the hurricane with the lowest cost in damages.
     */
    private JButton buttonLowestCosts;


    /**
     * Button to perform a search.
     */
    private JButton buttonSearch;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Constructs the panel and initializes its components.
     *
     * @param pPrincipal Principal window of the application. pPrincipal != null
     */
    public SearchesPanel(HurricaneInterface pPrincipal) {
        principal = pPrincipal;
        setLayout(new GridLayout(4, 1, 10, 10));
        setBorder(new CompoundBorder(new EmptyBorder(4, 3, 3, 3),
                                     new TitledBorder("Search system")));

        buttonSearch = new JButton("Find hurricane");
        buttonSearch.setActionCommand(SEARCH);
        buttonSearch.addActionListener(this);
        add(buttonSearch);

        // Highest Velocity
        buttonHighestVelocity = new JButton("Highest velocity");
        buttonHighestVelocity.addActionListener(this);
        buttonHighestVelocity.setActionCommand(HIGHEST_VELOCITY);
        add(buttonHighestVelocity);

        // Highest cost damages
        buttonHighestCosts = new JButton("Highest cost damages");
        buttonHighestCosts.addActionListener(this);
        buttonHighestCosts.setActionCommand(HIGHEST_DAMAGES);
        add(buttonHighestCosts);

        // Lowest cost en damages
        buttonLowestCosts = new JButton("Lowest cost in damages");
        buttonLowestCosts.addActionListener(this);
        buttonLowestCosts.setActionCommand(LOWEST_COST);
        add(buttonLowestCosts);


    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Executes an action according to the button pressed. E
     *
     * @param pEvent Event on button click..
     */
    public void actionPerformed(ActionEvent pEvent) {
        String command = pEvent.getActionCommand();

        if (HIGHEST_VELOCITY.equals(command)) {
            principal.findHighestVelocity();
        }
        else if (HIGHEST_DAMAGES.equals(command)) {
            principal.findHighestDamageCosts();
        }
        else if (LOWEST_COST.equals(command)) {
            principal.findLowestDamageCosts();
        }
        else if (SEARCH.equals(command)) {
            principal.find();
        }

    }
}