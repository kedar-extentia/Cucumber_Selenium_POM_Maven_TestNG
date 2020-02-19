package utils;

/**
 * Created with IntelliJ IDEA.
 * User: dev
 * Date: 05/04/13
 * Time: 16:42
 * To change this template use File | Settings | File Templates.
 */
public class AutomationConstants {

    public static final String BROWSER_TYPE = "chrome";
    public static final String REMOTE_BROWSER = "false";
    //Automation Framework
    public static final int FIVE_SECONDS = 5;
    public static final int TEN_SECONDS = 10;
    public static final int TWENTY_SECONDS = 20;
    public static final int DEFAULT_WAIT_SECONDS = TEN_SECONDS;
    //    static final String SELENIUM_GRID_URL = "https://gmail.com";
//    public static final String URL = "https://gmail.com/";
//    public static final String USERNAME = "kedar.extentia@gmail.com";
//    public static final String PASSWORD = "kedar@123";

    public static final String URL = "https://s2.demo.opensourcecms.com/orangehrm/symfony/web/index.php/auth/login";
    public static final String USERNAME = "opensourcecms";
    public static final String PASSWORD = "opensourcecms";

    public static final boolean local_reports = true;
    public static final long MAX_TIMEOUTS = 60000;

    public static final String OrangeUSERNAMEField = "txtUsername";
    public static final String OrangePASSWORDField = "txtPassword";

}
