import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SudoKuPanel extends JPanel implements MouseListener {


    private SudoKuButton[][] buttons = new SudoKuButton[9][9];
    private int[][] maps;
    private SelectNumDialog selectNumDialog;
    private int type;

    public SudoKuPanel(int type) {
        this.type = type;
        //初始化时间
        SudoKuFrame.usedTime = 0;
        //获得数独原型
        maps = new SudoKuFactory().generateSudoKuII();

        this.setLayout(null);
        //根据难度挖空
        switch (type) {
            case 1:
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        buttons[i][j] = new SudoKuButton();
                        buttons[i][j].setLocation(20 + i * 50 + (i / 3) * 5, 20 + j * 50 + (j / 3) * 5);
                        if (((i + 1) * 9 + j + 1) % 3 == 0 || ((i + 1) * 9 + j + 1) % 5 == 0) {
                            buttons[i][j].setText("" + maps[i][j]);
                            buttons[i][j].setEnabled(false);
                        } else {
                            buttons[i][j].addMouseListener(this);
                        }
                        this.add(buttons[i][j]);
                    }
                }
                break;
            case 2:
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        buttons[i][j] = new SudoKuButton();
                        buttons[i][j].setLocation(20 + i * 50 + (i / 3) * 5, 20 + j * 50 + (j / 3) * 5);
                        if (((i + 1) * 9 + j + 1) % 3 == 0 || ((i + 1) * 9 + j + 1) % 7 == 0) {
                            buttons[i][j].setText("" + maps[i][j]);
                            buttons[i][j].setEnabled(false);
                        } else {
                            buttons[i][j].addMouseListener(this);
                        }
                        this.add(buttons[i][j]);
                    }
                }
                break;
            case 3:
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        buttons[i][j] = new SudoKuButton();
                        buttons[i][j].setLocation(20 + i * 50 + (i / 3) * 5, 20 + j * 50 + (j / 3) * 5);
                        if (((i + 1) * 9 + j + 1) % 5 == 0) {
                            buttons[i][j].setText("" + maps[i][j]);
                            buttons[i][j].setEnabled(false);
                        } else {
                            buttons[i][j].addMouseListener(this);
                        }
                        this.add(buttons[i][j]);
                    }
                }
                break;
        }

    }

    private void CheckFinish() {
        //检查是否完成
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!Check(i, j)) {
                    return;
                }
            }
        }
        //停止计时
        SudoKuFrame.userTimeAction.stop();
        //去除所有监听
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                buttons[i][j].removeMouseListener(this);
            }
        }
        //返回选项
        int o = JOptionPane.showConfirmDialog(this, "已通关，是否退出？", "",
                0);
        if (o == 1) {
            System.exit(0);
        } else {
            ((SudoKuFrame) this.getParent()).dispose();
            StartFrame startFrame = new StartFrame();
            startFrame.setVisible(true);
        }
    }

    //冲突检测
    private boolean Check(int i, int j) {
        if (buttons[i][j].getText().isEmpty()) {
            return false;
        }

        for (int k = 0; k < 9; k++) {
            if (buttons[i][j].getText().trim().equals(buttons[i][k].getText().trim()) && j != k) {
                JOptionPane.showMessageDialog(this, "数字冲突，请检查");
                return false;
            }
            if (buttons[i][j].getText().trim().equals(buttons[k][j].getText().trim()) && i != k) {
                JOptionPane.showMessageDialog(this, "数字冲突，请检查");
                return false;
            }
            int ii = (i / 3) * 3 + k / 3;
            int jj = (j / 3) * 3 + k % 3;
            if (buttons[i][j].getText().trim().equals(buttons[ii][jj].getText().trim()) && !(i == ii && j == jj)) {
                JOptionPane.showMessageDialog(this, "数字冲突，请检查");
                return false;
            }
        }
        return true;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        int modes = mouseEvent.getModifiersEx();
        if ((modes & InputEvent.BUTTON3_DOWN_MASK) != 0) {
            ((SudoKuButton) mouseEvent.getSource()).setText("");
        } else if ((modes & InputEvent.BUTTON1_DOWN_MASK) != 0) {
            if (selectNumDialog != null) {
                selectNumDialog.dispose();
            }

            selectNumDialog = new SelectNumDialog();
            selectNumDialog.setModal(true);
            selectNumDialog.setLocation(mouseEvent.getLocationOnScreen().x, mouseEvent.getLocationOnScreen().y);
            selectNumDialog.setButton(((SudoKuButton) mouseEvent.getSource()));
            selectNumDialog.setVisible(true);
        }
        CheckFinish();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
