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

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 * Panel where the buttons for sorting the list of hurricanes are located.
 */
public class SortingPanel extends JPanel implements ActionListener {
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Constant for sorting by name.
     */
    private static final String SORT_NAME = "SortName";

    /**
     * Constant for sorting by damages.
     */
    private static final String SORT_DAMAGES = "SortDamageCosts";

    /**
     * Constant for sorting by velocity.
     */
    private static final String SORT_VELOCITY = "SortVelocity";

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
     * Button to sort the list of hurricanes by name.
     */
    private JButton buttonSortName;

    /**
     * Button to sort the list of hurricanes by damages.
     */
    private JButton buttonSortDamageCosts;

    /**
     * Button to sort the list of hurricanes by velocity.
     */
    private JButton buttonSortVelocity;


    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Constructs a panel and initializes its components.
     *
     * @param pPrincipal Principal window of the application. pPrincipal != null
     */
    public SortingPanel(HurricaneInterface pPrincipal) {
        principal = pPrincipal;

        setPreferredSize(new Dimension(200, 0));
        setBorder(new CompoundBorder(new EmptyBorder(4, 3, 3, 3), new TitledBorder("Sorting")));
        setLayout(new GridBagLayout());

        buttonSortName = new JButton("Sort by name");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.fill = GridBagConstraints.BOTH;
        buttonSortName.setActionCommand(SORT_NAME);
        buttonSortName.addActionListener(this);
        add(buttonSortName, gbc);

        buttonSortDamageCosts = new JButton("Sort by cost in damages");
        buttonSortDamageCosts.setActionCommand(SORT_DAMAGES);
        buttonSortDamageCosts.addActionListener(this);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.fill = GridBagConstraints.BOTH;
        add(buttonSortDamageCosts, gbc);

        buttonSortVelocity = new JButton("Sort by velocity");
        buttonSortVelocity.setActionCommand(SORT_VELOCITY);
        buttonSortVelocity.addActionListener(this);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.fill = GridBagConstraints.BOTH;
        add(buttonSortVelocity, gbc);

    }

    /**
     * Executes an action according to the button pressed.
     *
     * @param pEvent Event on button click.
     */
    public void actionPerformed(ActionEvent pEvent) {
        String command = pEvent.getActionCommand();

        if (SORT_VELOCITY.equals(command)) {
            principal.orderByVelocity();
        }
        else if (SORT_NAME.equals(command)) {
            principal.orderByName();
        }
        else if (SORT_DAMAGES.equals(command)) {
            principal.orderByDamageCosts();
        }

    }
}
