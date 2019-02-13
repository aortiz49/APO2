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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.cupihurricanes.world.Hurricane;
import uniandes.cupi2.cupihurricanes.world.HurricaneSystem;

/**
 * Principal window of the application.
 */
public class HurricaneInterface extends JFrame {

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Constant with the properties file.
     */
    public static final String HURRICANE_FILE = "./data/hurricanes.txt";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Reference to the hurricane system.
     */
    private HurricaneSystem hurricaneSystem;

    // -----------------------------------------------------------------
    // Attributes of the userInterface
    // -----------------------------------------------------------------

    /**
     * Panel for the hurricane list .
     */
    private HurricaneListPanel panelList;

    /**
     * Panel for the hurricane data.
     */
    private HurricaneDataPanel dataPanel;

    /**
     * Panel for the extension buttons.
     */
    private PanelExtension panelExtension;

    /**
     * Panel for the sorting.
     */
    private SortingPanel panelSorting;

    /**
     * Panel for searches.
     */
    private SearchesPanel panelSearches;

    /**
     * Panel for banner image.
     */
    private ImagePanel panelImage;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Constructs the userI interface and initializes its components.
     *
     * @param pFile Name of the properties file containing the hurricane information.
     */
    public HurricaneInterface(String pFile) {
        // Creates principal class
        hurricaneSystem = new HurricaneSystem();
        loadHurricanes(pFile);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("CupiHurricanes");
        setSize(new Dimension(800, 650));
        setResizable(false);

        setLayout(new BorderLayout());

        panelImage = new ImagePanel();
        add(panelImage, BorderLayout.NORTH);

        JPanel panelHurricanes = new JPanel();
        panelHurricanes.setBorder(new TitledBorder("Hurricanes in the system"));
        panelList = new HurricaneListPanel(this);
        dataPanel = new HurricaneDataPanel();
        panelHurricanes.setLayout(new BorderLayout());
        panelHurricanes.add(panelList, BorderLayout.WEST);
        panelHurricanes.add(dataPanel, BorderLayout.CENTER);
        add(panelHurricanes, BorderLayout.CENTER);

        JPanel panelActions = new JPanel();
        panelActions.setLayout(new GridLayout(2, 1));

        panelSorting = new SortingPanel(this);
        panelActions.add(panelSorting);
        panelSearches = new SearchesPanel(this);
        panelActions.add(panelSearches);
        add(panelActions, BorderLayout.EAST);

        panelExtension = new PanelExtension(this);
        add(panelExtension, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        updateList();
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Updates the hurricanes list.
     */
    private void updateList() {
        panelList.refreshList(hurricaneSystem.getHurricanes());
    }

    /**
     * Sorts the hurricanes by velocity and updates the list.
     */
    public void orderByVelocity() {
        hurricaneSystem.orderByVelocity();
        dataPanel.clearData();
        updateList();
    }

    /**
     * Sorts the hurricanes by name and updates the list.
     */
    public void orderByName() {
        hurricaneSystem.orderByName();
        dataPanel.clearData();
        updateList();
    }

    /**
     * Sorts the hurricanes by estimated cost in damages and updates the list.
     */
    public void orderByDamageCosts() {
        hurricaneSystem.orderByDamage();
        dataPanel.clearData();
        updateList();
    }


    public void orderByDuration() {
        hurricaneSystem.orderDurationByInsertion();
        dataPanel.clearData();
        updateList();
    }

    /**
     * Finds a hurricane by the name. When it's found, it selects it in the list and shows its data.
     */
    public void find() {
        String nameFound = JOptionPane.showInputDialog(this, "Name of the hurricane");
        if (nameFound != null) {
            int index = hurricaneSystem.findBinaryByName(nameFound);

            if (index != -1) {
                updateList();
                panelList.select(index);
                Hurricane p = (Hurricane) hurricaneSystem.getHurricanes().get(index);
                updateHurricaneInformation(p);
            }
            else {
                JOptionPane.showMessageDialog(this, "Hurricane not found");
            }
        }
    }

    /**
     * Updates the hurricane data in the corresponding panel.
     *
     * @param pHurricane Hurricane whose data is to be viewed.
     *                   pHurricane != null
     */
    public void updateHurricaneInformation(Hurricane pHurricane) {
        dataPanel.updateHurricaneInformation(pHurricane);
    }

    /**
     * Registers a new  hurricane.
     *
     * @param pName                Name of the hurricane. pName != null && pName != "".
     * @param pCategory            Category of the hurricane. 1 <= pCategory <= 5.
     * @param pVelocity            Wind velocity. 0 <= pVelocity.
     * @param pEstimatedDamageCost Estimated damage cost. 0 <= pEstimatedDamageCost.
     * @param pImage               Hurricane image path. pImage != null && pImage != "".
     */
    public void registerHurricane(String pName, int pCategory, int pVelocity,
                                  double pEstimatedDamageCost, String pImage) {
        boolean agrego = hurricaneSystem
                .registerHurricane(pName, pCategory, pVelocity, pEstimatedDamageCost, pImage);
        if (!agrego)
            JOptionPane.showMessageDialog(this, "Hurricane " + pName
                                                  + "could not be added.\n  Already exists.",
                                          "Register Hurricane",
                                          JOptionPane.ERROR_MESSAGE);
        else {
            updateList();
            panelList.select(hurricaneSystem.getHurricanes().size() - 1);
        }
    }

    /**
     * Loads the data from the properties file.
     *
     * @param pFile File containing hurricane information.
     */
    private void loadHurricanes(String pFile) {
        try {
            FileInputStream fis = new FileInputStream(new File(pFile));
            Properties properties = new Properties();
            properties.load(fis);

            // Load the hurricanes
            String data;
            String name;
            int category;
            int velocity;
            double damageCosts;
            String image;
            String aux;
            data = "total.hurricanes";
            aux = properties.getProperty(data);
            int amount = Integer.parseInt(aux);

            for (int cont = 1; cont <= amount; cont++) {
                // Load a hurricane
                data = "hurricane" + cont + ".name";
                name = properties.getProperty(data);

                data = "hurricane" + cont + ".category";
                category = Integer.parseInt(properties.getProperty(data));

                data = "hurricane" + cont + ".velocity";
                velocity = Integer.parseInt(properties.getProperty(data));

                data = "hurricane" + cont + ".damageCost";
                damageCosts = Double.parseDouble(properties.getProperty(data));

                data = "hurricane" + cont + ".image";
                image = properties.getProperty(data);

                // Only loads hurricane if data is correct
                if (name != null && category > 0 && category < 6 && velocity > 0 && damageCosts >= 0
                        && image != null)
                    hurricaneSystem.registerHurricane(name, category, velocity, damageCosts, image);
                fis.close();
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Hurricane file doesn't exist.",
                                          "load hurricanes", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Problem with loading the images.",
                                          "load hurricanes", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Find ethe hurricane with the highest velocity and show it's data in the in the data panel.

     */
    public void findHighestVelocity() {
        int index = hurricaneSystem.findHurricaneHighestVelocity();

        updateList();
        if (index != -1) {
            panelList.select(index);
            Hurricane h = (Hurricane) hurricaneSystem.getHurricanes().get(index);
            updateHurricaneInformation(h);
        }
        else {
            JOptionPane.showMessageDialog(this, "No hurricanes registered in the system.");
        }
    }

    /**
     * Finds the hurricane with the highest estimated damage costs and shows its data in the
     * data panel.
     */
    public void findHighestDamageCosts() {
        int index = hurricaneSystem.findHurricaneHighestDamage();

        updateList();
        if (index != -1) {
            panelList.select(index);
            Hurricane h = (Hurricane) hurricaneSystem.getHurricanes().get(index);
            updateHurricaneInformation(h);
        }
        else {
            JOptionPane.showMessageDialog(this, "No hurricanes registered in the system.");
        }
    }

    /**
     * Finds the hurricane with the lowest estimated damage costs and shows its data in the
     *      * data panel.
     */
    public void findLowestDamageCosts() {
        int index = hurricaneSystem.findHurricaneLowestDamage();

        updateList();
        if (index != -1) {
            panelList.select(index);
            Hurricane h = (Hurricane) hurricaneSystem.getHurricanes().get(index);
            updateHurricaneInformation(h);
        }
        else {
            JOptionPane.showMessageDialog(this, "No hurricanes registered in the system.");
        }
    }

    /**
     * Display the dialog to registrar un hurricane.
     */
    public void showRegisterHurricaneDialog() {

        RegisterHurricaneDialog dialog = new RegisterHurricaneDialog(this);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

    }

    // -----------------------------------------------------------------
    // Extension points
    // -----------------------------------------------------------------

    /**
     * Method for extension 1.
     */
    public void reqFuncOption1() {
    	hurricaneSystem.orderDurationByInsertion();
    		Hurricane h1 = (Hurricane)hurricaneSystem.getHurricanes().get(hurricaneSystem.getHurricanes().size()-1);
    	   String result = Integer.toString(h1.getDuration());
        JOptionPane
                .showMessageDialog(this, result, "Response", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method for extension 2.
     */
    public void reqFuncOption2() {
    	
    	hurricaneSystem.orderDurationByInsertion();
        Hurricane h1 = (Hurricane)hurricaneSystem.findHurricaneLongestDuration();
        String result = "The hurricane with the longest duration is " + h1.getName() +" with a duration of " + " " + h1.getDuration() + " days.";
        JOptionPane
                .showMessageDialog(this, result,"Option 2" , JOptionPane.INFORMATION_MESSAGE);
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Method that executes the app, creating a new user interface.
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {

        }
        HurricaneInterface userInterface = new HurricaneInterface(HURRICANE_FILE);
        userInterface.setVisible(true);
    }

}