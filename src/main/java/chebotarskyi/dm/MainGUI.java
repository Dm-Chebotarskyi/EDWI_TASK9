package chebotarskyi.dm;

import chebotarskyi.dm.exception.NotFoundException;
import chebotarskyi.dm.exception.NotSupportedShopException;
import org.apache.lucene.queryparser.classic.ParseException;

import javax.swing.*;

public class MainGUI {

    private JPanel rootPanel;
    private JTextField link_section_link_field;
    private JTextField link_section_count_field;
    private JLabel link_section_error_label;
    private JButton processButton;
    private JTextPane result_pane;
    private JButton doItButton;
    private JTextField queryToProcessTextField;

    private QueryProcessor queryProcessor;

    public static void main(String[] args) {

        MainGUI mainGUI = new MainGUI();

        JFrame frame = new JFrame("MainGUI");
        frame.setContentPane(new MainGUI().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 500);

        frame.setVisible(true);
    }

    private MainGUI() {

        queryProcessor = new QueryProcessor();
        processButton.addActionListener(e -> processButtonClick());
        doItButton.addActionListener(e -> queryButtonClick());
    }


    private void processButtonClick() {

        queryProcessor.closeIndex();

        String url = link_section_link_field.getText();
        int count;
        try {
            count = Integer.parseInt(link_section_count_field.getText());
        } catch (NumberFormatException e) {
            link_section_error_label.setText("Incorrect number");
            return;
        }
        if (!Validator.isLinkValid(url)) {
            link_section_error_label.setText("Incorrect link");
            return;
        }

        IndexUtils indexUtils = new IndexUtils();

        try {
            LinkProcessor linkProcessor = new LinkProcessor(url, indexUtils, count);
            linkProcessor.startProcessing();

            indexUtils.closeWriter();

            link_section_error_label.setText("Done");
        } catch (NotSupportedShopException e) {
            link_section_error_label.setText("Unsupported shop");
        }
    }

    private void queryButtonClick() {
        queryProcessor = new QueryProcessor();

        String query = queryToProcessTextField.getText();

        try {
            String context = queryProcessor.processQuery(query);

            result_pane.setText(context);

        } catch (ParseException e) {
            result_pane.setText("Error while parsing query");
        } catch (NotFoundException e) {
            result_pane.setText("No product found");
        }
    }

}
