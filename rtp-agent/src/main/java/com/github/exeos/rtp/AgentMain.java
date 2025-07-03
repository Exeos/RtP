package com.github.exeos.rtp;

import com.formdev.flatlaf.FlatDarkLaf;
import com.github.exeos.rtp.swing.BaseWindow;
import java.lang.instrument.Instrumentation;
import javax.swing.UIManager;

public class AgentMain {
  public static boolean continueLaunch;

  public static void premain(String agentArgs, Instrumentation inst) {
    agentmain(agentArgs, inst);
  }

  @SuppressWarnings("BusyWait") // no shit sherlock
  public static void agentmain(String args, Instrumentation instrumentation) {
    try {
      UIManager.setLookAndFeel(new FlatDarkLaf());
    } catch (Exception e) {
      e.printStackTrace(System.err);
    }

    new Thread(() -> {
      BaseWindow dialog = new BaseWindow();
      dialog.pack();
      dialog.setSize(300, 100);
      dialog.setVisible(true);
    }, "GUI Thread").start();

    while (continueLaunch) {
      try {
        Thread.sleep(50L);
      } catch (InterruptedException e) {
        e.printStackTrace(System.err);
      }
    }
  }
}