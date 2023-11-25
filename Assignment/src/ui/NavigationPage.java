package ui;

/**
 * The NavigationPage class represents a generic navigation page in the NTU CAMS
 * System
 * It serves as a base class for specific navigation pages and ensures a
 * standardized structure
 * Subclasses must implement the 'run' method to define the behavior of the
 * navigation page
 * 
 * @author SCSZ Group 4
 * @version 1.0 
 * @since 25/11/2023
 */
public abstract class NavigationPage {
    /**
     * The main menu associated with the navigation page
     */
    protected MainMenuInterface menu;

    /**
     * Executes the navigation page's functionality
     * Subclasses must implement this method to define the behavior of the
     * navigation page
     */
    public abstract void run();
}
