package lab;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.*;

public class CarAnimation implements ActionListener, KeyListener {
    private Button go;
    private TransformGroup car;
    private Transform3D translateTransform;
    private Transform3D rotateTransform;

    private JFrame mainFrame;

    private float sign = 1.0f;
    private float x = 0.0f;
    private float y = 0.0f;

    private Timer timer;

    public CarAnimation(TransformGroup car, JFrame frame){
        go = new Button("Go");
        this.car = car;
        translateTransform = new Transform3D();
        translateTransform.setScale(new Vector3d(0.3,0.3,0.3));
        mainFrame = frame;
        rotateTransform = new Transform3D();
        timer = new Timer(500,this);

        Panel p =new Panel();
        p.add(go);
        mainFrame.add("North",p);
        go.addActionListener(this);
        go.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // start timer when button is pressed
        if (e.getSource()==go){
            if (!timer.isRunning()) {
                timer.start();
                go.setLabel("Stop");
            }
            else {
                timer.stop();
                go.setLabel("Go");
            }
        }
        else {
            Move();
            translateTransform.setTranslation(new Vector3f(x, y,0));
            car.setTransform(translateTransform);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void Move(){
        x += 0.3 * sign;
        if (x > 4 || x < 0) {
            sign = -1.0f * sign;
            rotateTransform.rotZ(-Math.PI / 2);
            translateTransform.mul(rotateTransform);
            car.setTransform(translateTransform);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            rotateTransform.rotZ(-Math.PI / 2);
            translateTransform.mul(rotateTransform);
            car.setTransform(translateTransform);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Invoked when a key has been pressed.
        if (e.getKeyChar()=='d') {x += .2f;}
        if (e.getKeyChar()=='a') {x -= .2f;}
        if (e.getKeyChar()=='w') {y += .2f;}
        if (e.getKeyChar()=='s') {y -= .2f;}
        if (e.getKeyChar()=='e') {
            rotateTransform.rotZ(-0.1f);
            translateTransform.mul(rotateTransform);
        }
        if (e.getKeyChar()=='q') {
            rotateTransform.rotZ(0.1f);
            translateTransform.mul(rotateTransform);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //Invoked when a key has been typed.
    }
    @Override
    public void keyReleased(KeyEvent e) {
        // Invoked when a key has been released.
    }
}
