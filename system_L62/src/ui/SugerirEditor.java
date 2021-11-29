package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import business.Articulo;
import business.Revisor;
import business.Tema;
import persistence.DataBaseRevisor;
import persistence.DatabaseRecomendacion;

public class SugerirEditor extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnNewButton;

    private List<Tema> temas;

    private DataBaseRevisor dbRevisores = new DataBaseRevisor();
    private List<Revisor> revisoresRecomendados = new ArrayList<Revisor>();
    private int nRevisores = 0;
    private JButton btnNewButton_1;
    private Articulo article;
    private JLabel lblNewLabel;
    private JTextField textField;
    private JLabel lblNewLabel_1;
    private JComboBox<Tema> comboBox;
    private JButton btnNewButton_2;
    private JTextField textField_1;
    private JLabel lblNewLabel_2;
    private JSpinner spinner;
    private JTextField textField_2;
    private JLabel lblNewLabel_3;

    /**
     * Create the frame.
     */
    public SugerirEditor(Articulo article) {
	this.article = article;
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 522, 311);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	contentPane.add(getBtnNewButton());
	contentPane.add(getBotonHecho());
	contentPane.add(getLblNewLabel());
	contentPane.add(getTextField_1());
	contentPane.add(getLblNewLabel_1());
	contentPane.add(getComboBox_1());
	contentPane.add(getBtnNewButton_2());
	contentPane.add(getTextField_1_1());
	contentPane.add(getLblNewLabel_2());
	contentPane.add(getSpinner());
	contentPane.add(getTextField_2());
	contentPane.add(getLblNewLabel_3());
	setModal(true);
    }

    private JButton getBtnNewButton() {
	if (btnNewButton == null) {
	    btnNewButton = new JButton("Sugerir");
	    btnNewButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    if (revisoresRecomendados.size() < 3) {
			if (!comprobarCampos()) {
			    Revisor r = new Revisor(temas, (Integer) getSpinner().getValue(),
				    getTextField_1().getText());
			    temas = new ArrayList<Tema>();
			    revisoresRecomendados.add(r);

			    getTextField_1().setText("");
			    getTextField_1_1().setText("");
			    getSpinner().setValue(1);
			    getTextField_2().setText(revisoresRecomendados.toString() + '\n');
			}
		    }

		}
	    });
	    btnNewButton.setBounds(343, 7, 89, 23);
	}
	return btnNewButton;
    }

    protected boolean comprobarCampos() {
	return this.getTextField_1().getText().isBlank() || this.getTextField_1_1().getText().isBlank();
    }

    private JButton getBotonHecho() {
	if (btnNewButton_1 == null) {
	    btnNewButton_1 = new JButton("Hecho");
	    btnNewButton_1.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

		    DatabaseRecomendacion dbr = new DatabaseRecomendacion(article);
		    dbr.insertRecommendations(revisoresRecomendados);
		    disposeDialog();
		}
	    });
	    btnNewButton_1.setBounds(407, 238, 89, 23);
	}
	return btnNewButton_1;
    }

    private void disposeDialog() {
	this.dispose();
    }

    private JLabel getLblNewLabel() {
	if (lblNewLabel == null) {
	    lblNewLabel = new JLabel("Nombre:");
	    lblNewLabel.setBounds(10, 11, 115, 14);
	}
	return lblNewLabel;
    }

    private JTextField getTextField_1() {
	if (textField == null) {
	    textField = new JTextField();
	    textField.setBounds(135, 8, 175, 23);
	    textField.setColumns(10);
	}
	return textField;
    }

    private JLabel getLblNewLabel_1() {
	if (lblNewLabel_1 == null) {
	    lblNewLabel_1 = new JLabel("Temas");
	    lblNewLabel_1.setBounds(10, 36, 48, 14);
	}
	return lblNewLabel_1;
    }

    private JComboBox<Tema> getComboBox_1() {
	if (comboBox == null) {
	    comboBox = new JComboBox<Tema>();
	    comboBox.setBounds(10, 61, 175, 23);
	}
	return comboBox;
    }

    private JButton getBtnNewButton_2() {
	if (btnNewButton_2 == null) {
	    btnNewButton_2 = new JButton("A\u00F1adir");
	    btnNewButton_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    Tema t = (Tema) getComboBox_1().getSelectedItem();
		    if (!temas.contains(t)) {
			temas.add(t);
		    }
		}
	    });
	    btnNewButton_2.setBounds(195, 61, 89, 23);
	}
	return btnNewButton_2;
    }

    private JTextField getTextField_1_1() {
	if (textField_1 == null) {
	    textField_1 = new JTextField();
	    textField_1.setEditable(false);
	    textField_1.setBounds(10, 95, 175, 36);
	    textField_1.setColumns(10);
	}
	return textField_1;
    }

    private JLabel getLblNewLabel_2() {
	if (lblNewLabel_2 == null) {
	    lblNewLabel_2 = new JLabel("Tiempo de revision");
	    lblNewLabel_2.setBounds(10, 145, 115, 14);
	}
	return lblNewLabel_2;
    }

    private JSpinner getSpinner() {
	if (spinner == null) {
	    spinner = new JSpinner();
	    spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(0), null, new Integer(1)));
	    spinner.setBounds(155, 142, 30, 20);
	}
	return spinner;
    }

    private JTextField getTextField_2() {
	if (textField_2 == null) {
	    textField_2 = new JTextField();
	    textField_2.setEditable(false);
	    textField_2.setBounds(298, 142, 198, 85);
	    textField_2.setColumns(10);
	}
	return textField_2;
    }

    private JLabel getLblNewLabel_3() {
	if (lblNewLabel_3 == null) {
	    lblNewLabel_3 = new JLabel("Revisores:");
	    lblNewLabel_3.setBounds(298, 117, 66, 14);
	}
	return lblNewLabel_3;
    }
}
