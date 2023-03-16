package Seq_Hyd_Maven.Seq_Hyd_Maven_Proj;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.support.ui.WebDriverWait;

public class TLDriverFactory {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public synchronized static void setTLDriver (String browser) {
        if (browser.equals("firefox")) {
        } else if (browser.equals("chrome")) {
            tlDriver = ThreadLocal.withInitial(() -> new ChromeDriver(OptionsManager.getChromeOptions()));
        }
    }

    public synchronized static WebDriver getTLDriver () {
        return tlDriver.get();
    }
}