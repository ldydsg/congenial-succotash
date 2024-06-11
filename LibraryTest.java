package Library;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class LibraryTest {
    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
//        Supermarket supermarket = new Supermarket();
//        supermarket.addCategory("Food", "总目录");
//        supermarket.addCategory("Frozen Food", "Food");
//        supermarket.addCategory("Dumplings", "Frozen Food");
//        supermarket.addCategory("Glutinous Rice Balls", "Frozen Food");
//        supermarket.addCategory("Grains and Oils", "Food");
//
//        supermarket.displayCategories();
//
//        System.out.println();
//
//       supermarket.removeCategory("Dumplings");
//       supermarket.displayCategories();
//
//       System.out.println();
//
//        supermarket.findCategory("Glutinous Rice Balls");
//        supermarket.findCategory("Non-existent Product");
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                SupermarketGuI gui=new SupermarketGuI();
                gui.setVisible(true);
            }
        });
    }
}
