import javax.swing.*;
import java.awt.*;

public class StartFrame extends JFrame {

    public StartFrame() throws HeadlessException {
        this.setSize(515, 600);
        this.setLocation(500, 50);
        this.setTitle("难度选择");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ChooseType();
    }

    private void ChooseType() {
        //选择难度
        JButton easy = new JButton("简单");
        JButton normal = new JButton("一般");
        JButton hard = new JButton("困难");

        easy.addActionListener(actionEvent -> {
            this.dispose();
            SudoKuFrame sudoKuFrame = new SudoKuFrame(1);
            sudoKuFrame.setVisible(true);
        });
        normal.addActionListener(actionEvent -> {
            this.dispose();
            SudoKuFrame sudoKuFrame = new SudoKuFrame(2);
            sudoKuFrame.setVisible(true);
        });
        hard.addActionListener(actionEvent -> {
            this.dispose();
            SudoKuFrame sudoKuFrame = new SudoKuFrame(3);
            sudoKuFrame.setVisible(true);
        });

        JPanel panel = new JPanel();
        panel.add(easy);
        panel.add(normal);
        panel.add(hard);

        this.add(panel, BorderLayout.CENTER);
    }
}
