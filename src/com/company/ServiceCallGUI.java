package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by marian on 11/21/2015.
 */
public class ServiceCallGUI extends JFrame {
    Scanner s = new Scanner(System.in);

    boolean quit = false;
    private static LinkedList<ServiceCall> todayServiceCalls;
    LinkedList<ServiceCall> todayServiceCallQueue = new LinkedList<>();
    LinkedList<ServiceCall> resolvedServiceCalls = new LinkedList<>();
    private JPanel rootPanel;
    private JList serviceCalllist1;
    private JTextField AddressextField1;
    private JTextField descriptiontextField2;
    private JTextField modeltextField3;
    private JButton addButton;
    private JRadioButton furnaceradioButton1;
    private JRadioButton ACRadioButton;
    private JLabel modelLabel;
    private JComboBox furnaceTypeComboBox;
    private JLabel furnaceLabel;
    private JButton deleteButton;
    private JButton quitButton;
    //private JTextField ResolutiontextField1;
    //LinkedList<ServiceCall> todayServiceCalls = new LinkedList<>();
    //LinkedList<ServiceCall> resolvedServiceCalls = new LinkedList<>();
    protected String resolution;
    protected double fee;

    DefaultListModel<ServiceCall> serviceCallGUIListModel;

    public ServiceCallGUI() {
        super("Service call");
        setContentPane(rootPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setSize(new Dimension(400, 400));
        final String furnaceTypes = "forced Air";
        final String furnaceType = "Boiler/Radiators";
        final String FurnacType = "Older 'Octopus' Style";
        furnaceTypeComboBox.addItem(furnaceTypes);
        furnaceTypeComboBox.addItem(furnaceType);
        furnaceTypeComboBox.addItem(FurnacType);


        serviceCallGUIListModel = new DefaultListModel<ServiceCall>();
        serviceCalllist1.setModel(serviceCallGUIListModel);
        serviceCalllist1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        modeltextField3.setVisible(false);
        modelLabel.setVisible(false);
        ACRadioButton.setSelected(false);
        //ResolutiontextField1.setVisible(false);


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String serviceAddress = AddressextField1.getText();
                String report = descriptiontextField2.getText();
                //String models = modeltextField3.getText();
                String description = descriptiontextField2.getText();
                //String furnacetoString = furnaceradioButton1.getText();
                Date date = new Date();

                if (ACRadioButton.isSelected()) {
                    String model = modeltextField3.getText();
                    CentralAC newservice = new CentralAC(serviceAddress, description, date, model);
                    serviceCallGUIListModel.addElement(newservice);
                    todayServiceCallQueue.add(newservice);
                } else {
                    int furnaceType = 0;
                    if (furnaceradioButton1.isSelected()) {
                        if (furnaceTypeComboBox.getSelectedItem().equals("forced Air")) {
                            furnaceType = 1;

                            Furnace furnace = new Furnace(serviceAddress, description, date, furnaceType);
                            serviceCallGUIListModel.addElement(furnace);
                            todayServiceCallQueue.add(furnace);
                        }
                        if (furnaceTypeComboBox.getSelectedItem().equals("Boiler/Radiators")){
                            furnaceType = 2;
                            Furnace furnace = new Furnace(serviceAddress, description, date, furnaceType);
                            serviceCallGUIListModel.addElement(furnace);
                            todayServiceCallQueue.add(furnace);

                        }
                        if (furnaceTypeComboBox.getSelectedItem().equals( "Older 'Octopus' Style")){
                            furnaceType = 3;
                            Furnace furnace = new Furnace(serviceAddress, description, date, furnaceType);
                            serviceCallGUIListModel.addElement(furnace);
                            todayServiceCallQueue.add(furnace);
                        }
                    }

                }
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }


        });
        ACRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ACRadioButton.isSelected()) {
                    modeltextField3.setVisible(true);
                    modelLabel.setVisible(true);
                } else {
                    modeltextField3.setVisible(false);
                    modelLabel.setVisible(false);
                }
            }
        });
        furnaceradioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (furnaceradioButton1.isSelected()) {
                    furnaceTypeComboBox.setVisible(true);
                    furnaceLabel.setVisible(true);
                } else {
                    furnaceTypeComboBox.setVisible(false);
                    furnaceLabel.setVisible(false);
                }
            }

        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ServiceCall serviceCall = (ServiceCall) ServiceCallGUI.this.serviceCalllist1.getSelectedValue();
                ServiceCallGUI.this.serviceCallGUIListModel.removeElement(serviceCall);


            }
        });
    }
}
