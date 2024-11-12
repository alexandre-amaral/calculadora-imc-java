import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class CalculadoraIMC extends JFrame implements ActionListener {
    private JTextField pesoField, alturaField;
    private JLabel resultadoLabel;

    public CalculadoraIMC() {
        // Configurações da Janela
        setTitle("Calculadora de IMC");
        setSize(500, 350); // Aumentando o tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela
        setResizable(false); // Janela com tamanho fixo

        // Painel com borda
        JPanel painelPrincipal = new JPanel(new GridBagLayout());
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Borda interna para espaçamento

        // Restrições de layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Elementos da interface
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelPrincipal.add(new JLabel("Peso (kg):"), gbc);
        pesoField = new JTextField(10);
        gbc.gridx = 1;
        painelPrincipal.add(pesoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        painelPrincipal.add(new JLabel("Altura (m):"), gbc);
        alturaField = new JTextField(10);
        gbc.gridx = 1;
        painelPrincipal.add(alturaField, gbc);

        JButton calcularButton = new JButton("Calcular IMC");
        calcularButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        painelPrincipal.add(calcularButton, gbc);

        resultadoLabel = new JLabel("Resultado: ");
        gbc.gridx = 0;
        gbc.gridy = 3;
        painelPrincipal.add(resultadoLabel, gbc);

        // Adiciona o painel principal à janela
        add(painelPrincipal);
    }

    public static void main(String[] args) {
        CalculadoraIMC app = new CalculadoraIMC();
        app.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Substitui a vírgula por ponto para aceitar ambos os formatos
            String pesoText = pesoField.getText().replace(",", ".");
            String alturaText = alturaField.getText().replace(",", ".");

            double peso = Double.parseDouble(pesoText);
            double altura = Double.parseDouble(alturaText);
            double imc = peso / (altura * altura);

            // Formata o resultado para duas casas decimais
            DecimalFormat df = new DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.US));
            String imcFormatado = df.format(imc);

            String recomendacao = "";
            if (imc < 18.5) {
                recomendacao = "Abaixo do peso. Procure orientação nutricional.";
            } else if (imc < 24.9) {
                recomendacao = "Peso normal. Mantenha uma dieta equilibrada.";
            } else if (imc < 29.9) {
                recomendacao = "Sobrepeso. Considere atividade física.";
            } else {
                recomendacao = "Obesidade. Consulte um médico.";
            }

            resultadoLabel.setText("IMC: " + imcFormatado + " - " + recomendacao);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores numéricos válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}