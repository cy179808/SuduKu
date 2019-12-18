import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;


public class SudoKuFrame extends JFrame {

    private int type;
    public static long usedTime = 0;
    private SudoKuPanel sudoKuPanel;
    public static Timer userTimeAction;

    public SudoKuFrame(int type) throws HeadlessException {
        this.type = type;
        //初始化大小
        init();
        //添加游戏区
        addPanel();
        //添加时间区
        addComponent();
    }

    private void addPanel() {
        sudoKuPanel = new SudoKuPanel(type);
        sudoKuPanel.setBorder(new TitledBorder("游戏区"));

        this.add(sudoKuPanel, BorderLayout.CENTER);
    }

    private void addComponent() {
        JPanel panelComponent = new JPanel();

        addPanelTime(panelComponent);

        this.add(panelComponent, BorderLayout.NORTH);
    }

    private void addPanelTime(JPanel panelComponent) {
        JPanel panelTime = new JPanel();
        panelTime.setBorder(new TitledBorder("时间"));

        final Label UserTime = new Label();

        panelTime.add(UserTime, BorderLayout.CENTER);

        userTimeAction = new Timer(1000, actionEvent -> UserTime.setText("已用时：" + (++usedTime) + "sec"));
        userTimeAction.start();

        panelComponent.add(panelTime, BorderLayout.CENTER);
    }

    private void init() {
        this.setSize(515, 600);
        this.setLocation(500, 50);
        this.setTitle("数独游戏");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
