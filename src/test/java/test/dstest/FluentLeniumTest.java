package test.dstest;

import org.apache.commons.exec.OS;
import org.junit.Before;
import test.utils.MyProperties;
import org.fluentlenium.adapter.junit.FluentTest;
import org.junit.BeforeClass;
import java.io.File;
import java.util.Objects;


public class FluentLeniumTest extends FluentTest {

    private static MyProperties myProperties = new MyProperties();

    @BeforeClass
    public static void setUp() {

        ClassLoader classLoader = FluentLeniumTest.class.getClassLoader();

        String driverName = myProperties.getProperty("driver");

        switch(driverName){
            case "chrome":
                File fileOne = null;
                if (OS.isFamilyWindows()){
                    fileOne = new File(Objects.requireNonNull(classLoader.getResource("chromedriver.exe")).getFile());
                }
                else if (OS.isFamilyUnix()){
                    fileOne = new File(Objects.requireNonNull(classLoader.getResource("chromedriver")).getFile());

                }

                System.setProperty("webdriver.chrome.driver", fileOne.getAbsolutePath());

                break;

//            case "mozilla":
                //Solution can be extended to cover more browser types

            default:
                System.out.println("No driver selected");
                break;
        }

    }

    @Override
    public String getWebDriver() {
        return myProperties.getProperty("driver");
    }

}