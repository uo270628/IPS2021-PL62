package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import business.Articulo;
import business.Revisor;
import persistence.DataBaseRevisor;
import persistence.DatabaseRecomendacion;

public class SugerirEditor extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<Revisor> comboBox;
    private JButton btnNewButton;
    private List<Revisor> revisores;
    private DataBaseRevisor dbRevisores = new DataBaseRevisor();
    private JTextField textField;
    private Revisor[] revisoresRecomendados = new Revisor[3];
    private int nRevisores = 0;
    private JButton btnNewButton_1;
    private Articulo article;

    /**
     * Create the frame.
     */
    public SugerirEditor(Articulo article) {
	this.article = article;
	revisores = dbRevisores.getAllRevisores();
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 482, 212);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	contentPane.add(getComboBox());
	contentPane.add(getBtnNewButton());
	contentPane.add(getTextField());
	contentPane.add(getBotonHecho());
	setModal(true);
    }

    private JComboBox<Revisor> getComboBox() {
	if (comboBox == null) {
	    comboBox = new JComboBox<Revisor>();
	    comboBox.setModel(new DefaultComboBoxModel<Revisor>(revisores.toArray(new Revisor[revisores.size()])));
	    comboBox.setBounds(10, 46, 348, 22);
	}
	return comboBox;
    }

    private JButton getBtnNewButton() {
	if (btnNewButton == null) {
	    btnNewButton = new JButton("Sugerir");
	    btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    if (nRevisores < 3) {
			revisoresRecomendados[nRevisores] = (Revisor) getComboBox().getSelectedItem();
			nRevisores++;
			String tmp = "";
			for (Revisor r : revisoresRecomendados) {
			    tmp += r.getNombre();
			    tmp += "\n";
			}
			textField.setText(tmp);
		    }
		}
	    });
	    btnNewButton.setBounds(368, 46, 89, 23);
	}
	return btnNewButton;
    }

    private JTextField getTextField() {
	if (textField == null) {
	    textField = new JTextField();
	    textField.setEditable(false);
	    textField.setBounds(10, 79, 348, 83);
	    textField.setColumns(10);
	}
	return textField;
    }

    private JButton getBotonHecho() {
	if (btnNewButton_1 == null) {
	    btnNewButton_1 = new JButton("Hecho");
	    btnNewButton_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    article.setRevisoresRecomendados(revisoresRecomendados);
		    DatabaseRecomendacion dbr = new DatabaseRecomendacion(article);
		    dbr.insertRecommendations(revisoresRecomendados);
		    disposeDialog();
		}
	    });
	    btnNewButton_1.setBounds(368, 139, 89, 23);
	}
	return btnNewButton_1;
    }

    private void disposeDialog() {
	this.dispose();
    }
}
