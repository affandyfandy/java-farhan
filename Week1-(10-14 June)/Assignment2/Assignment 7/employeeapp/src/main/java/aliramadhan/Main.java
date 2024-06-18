package aliramadhan;

import aliramadhan.Manager.AppManager;

/**
 * Main Application
 * Call AppManager Singleton
 */
public class Main {

    public static void main(String[] args) {
        AppManager app = AppManager.getInstance();
        app.start();
    }
}
