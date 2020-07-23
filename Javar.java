package javar;

import javar.constants.JavarConstants;
import javar.codepane.CodePane;
import javar.filelist.FileList;
import javar.tabbedpane.TabbedPane;
import javar.upperbar.UpperBar;
import javar.managerbar.ManagerBar;
import javar.menuitemprovider.MenuItemProvider;
import javar.creatorwindow.CreatorWindow;
import javar.infolabel.InfoLabel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Javar
{
    public static JFrame mainWindow;
    public static TabbedPane codeEditor;
    public static TabbedPane outputArea;
    public static FileList fileList;
    public static InfoLabel infoLabel;
    public static UpperBar upperBar;
    public static ManagerBar managerBar;
    public static CreatorWindow creatorWindow;
    
    public void initMainWindow()
    {
        /* Malloc */
        mainWindow = new JFrame();
        codeEditor = new TabbedPane(JavarConstants.CodePane);
        outputArea = new TabbedPane(JavarConstants.OutputArea);
        fileList = new FileList();
        infoLabel = new InfoLabel();
        upperBar = new UpperBar();
        managerBar = new ManagerBar();
        creatorWindow = new CreatorWindow();
        // Set file list scrollable
        var fileListScrollable = new JScrollPane(fileList);
        /* Start init */
        codeEditor.setPreferredSize(new Dimension(JavarConstants.codeEditorWidth, JavarConstants.codeEditorHeight));
        outputArea.setPreferredSize(new Dimension(JavarConstants.outputAreaWidth, JavarConstants.outputAreaHeight));
        fileListScrollable.setPreferredSize(new Dimension(JavarConstants.fileListScrollableWidth, JavarConstants.fileListScrollableHeight));
        infoLabel.setPreferredSize(new Dimension(JavarConstants.infoLabelWidth, JavarConstants.infoLabelHeight));
        upperBar.setPreferredSize(new Dimension(JavarConstants.upperBarWidth, JavarConstants.upperBarHeight));
        var leftPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, fileListScrollable, infoLabel);
        var rightPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, codeEditor, outputArea);
        var centerPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        leftPanel.setContinuousLayout(false);
        leftPanel.resetToPreferredSizes();
        rightPanel.setContinuousLayout(false);
        rightPanel.setOneTouchExpandable(true);
        rightPanel.resetToPreferredSizes();
        centerPanel.setContinuousLayout(false);
        centerPanel.resetToPreferredSizes();
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        mainWindow.setLayout(gb);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gb.setConstraints(upperBar, gbc);
        mainWindow.add(upperBar);
        gbc.weighty = 1;
        gb.setConstraints(centerPanel, gbc);
        mainWindow.add(centerPanel);
        mainWindow.setJMenuBar(managerBar);
        mainWindow.pack();
        mainWindow.setLocation((int)(JavarConstants.screenWidth/2-mainWindow.getWidth()/2), 0);
        mainWindow.setVisible(true);
    }

    public static void main(String[] args)
    {
        JavarConstants.initJavarConstants();
        MenuItemProvider.initMenuItemProvider();
        var app = new Javar();
        app.initMainWindow();
    }
}
