import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SelectNumDialog extends JDialog implements MouseListener {

    private SudoKuButton button;

    public SelectNumDialog() {
       this.setUndecorated(true);
       this.setSize(150,150);
       this.setLayout(null);
       addNum();
    }

    public void setButton(SudoKuButton button) {
        this.button = button;
    }

    private void addNum() {
        //添加按钮
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton btn = new JButton();
                btn.setSize(50, 50);
                btn.setLocation(i * 50, j * 50);
                btn.setText("" + (j * 3 + i + 1));
                btn.addMouseListener(this);
                this.add(btn);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        int modes = mouseEvent.getModifiersEx();
        if ((modes & InputEvent.BUTTON1_DOWN_MASK) != 0) {
            JButton btn = (JButton) mouseEvent.getSource();
            button.setText(btn.getText());
        }
        this.dispose();
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
