import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class kongaOrderingTask {

    private WebDriver driver;

    //we annotate this method as the method to call before test begins. This ensures all the
    //needed config are done before actual test begins.
    @BeforeTest
     public void setup() throws InterruptedException {
        //set the System to locate the driver
        System.setProperty("webdriver.chrome.driver","resources/chromedriver.exe");
        //next, instantiate the chrome web-driver object by passing in the argument
        ChromeOptions options = new ChromeOptions();  //add this option to allow origins issues in browser
        options.addArguments("--remote-allow-origins=*");
        //first initialize your web driver and open your Chrome browser
        driver = new ChromeDriver(options);
        //pass in the url into the get method of the driver for the site under test
        String url = "https://www.konga.com/";
        //i.e https://www.konga.com/
        driver.get(url);
        //wait for the loading of the url to complete before continuing
        Thread.sleep(5000);
        //driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
        //maximize the browser window
        driver.manage().window().maximize();
        //find the login button on the konga webpage and click on it to logi
        //here we use the xpath to find the login button and then click on it
        Thread.sleep(5000);
        String loginBtn = "//*[@id='nav-bar-fix']/div[1]/div/div/div[4]/a";
        driver.findElement(By.xpath(loginBtn)).click();
        Thread.sleep(5000);
    }


    //We annotate this method with the test method with priority 0 by default
    @Test()  //this signs in the user using username and password
    public void LoginUser() throws InterruptedException {
        //The login screen has appeared here from the last step we conducted.
        //next, find the username and password input boxes and supply the necessary details to login
        //find the username box first. It has id #username, so we use id to locate it and pass in the values
        String username = "royalswiftlogistics@gmail.com";
        driver.findElement(By.id("username")).sendKeys(username);

        //Also, find the password box and pass in the values. It has id = password. we use this to locate it
        String password = "Ogbola@1990";
        driver.findElement(By.id("password")).sendKeys(password);

        //next, we find the login button and click on it.
        //we use xPath to locate the login button and click on it
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[2]/div/form/div[3]/button")).click();

        //wait for the login to complete by using freezing the thread
        Thread.sleep(3000);
    }
    @Test(priority = 1)  //finds the category group and click on computers and accessories
    public void ClickOnCategories() throws InterruptedException {
        //user is logged in already over here
        //locate the computers and accessories using xpath and click on it
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[2]/div/a[2]")).click();
        Thread.sleep(5000);  //wait for the computer and accessories to load
        //we have to wait for the computer list subsection to appear before we click on it,
        //we will use the WebDriver wait method, and time duration to ensure it is visible before we click on it
        Duration timeout = Duration.ofSeconds(10);//wait for 10 seconds

        WebDriverWait wait = new WebDriverWait(driver, timeout);
        String laptopItemPath = "//*[@id=\"mainContent\"]/section[3]/section/div/section/div[2]/div[2]/ul/li[3]/a/label/span";
        WebElement laptopElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(laptopItemPath)));

        laptopElement.click(); //click the item when it becomes visible

        Thread.sleep(3000);
        //find the Apple Macbook and click on it with Xpath = //*[@id="mainContent"]/section[3]/section/div/section/div[2]/div[2]/ul/li[3]/a/ul/li[1]/a/label/span
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/div/section/div[2]/div[2]/ul/li[3]/a/ul/li[1]/a/label/span")).click();

        Thread.sleep(3000);
    }

    @Test(priority = 2) //add an item to the cart
    public void AddItemToCart() throws InterruptedException {

        Thread.sleep(3000);
        //wait for the selected laptop to be available before clicking on it,
        //I selected the 512,000 naira MacBook
        String selectedMacbookXPath = "//*[@id=\"mainContent\"]/section[3]/section/section/section/section/ul/li[1]/div/div/div[2]/form/div[3]/button";

        Duration timeout = Duration.ofSeconds(50);
        //wait for the selected MacBook to be available before clicking and adding to cart
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        //wait for the selected Macbook to be available first
        WebElement selectedMacBook = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(selectedMacbookXPath)));
        //Once the selected MacBook is available, click on this add to cart button to add the Macbook to cart
        Thread.sleep(2000);

        // we Scroll to the selectedMacBook button element using JavaScriptExecutor
        //JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        //we cast the driver object to a javascript ScriptExecutor object

       // jsExecutor.executeScript("arguments[0].scrollIntoView(true);", selectedMacBook);
        //we use the javascript object to click on the first instance found of the button

        // Clicking on the button element once the javascript object finds it
        selectedMacBook.click();

        Thread.sleep(3000);  //Give the adding to cart process some time to finish
    }

    @Test(priority = 3) //after adding to cart, click on the cart to open the cart screen
    public void ClickOnCart() throws InterruptedException {
        //wait for the add to cart to finish, after which you will click on the cart button
        //get the xpath of the cart button and click on it
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/a[2]/span[1]")).click();

        //wait for this cart screen to open
        Thread.sleep(5000);

        //wait for the checkout button to become visible before clicking on it

        String checkOutBtnXPath = "//*[@id=\"app-content-wrapper\"]/div[3]/section/section/aside/div[3]/div/div[2]/button";

        Duration timeout = Duration.ofSeconds(35);
        //wait for the checkout button to be available before clicking and adding address
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        //wait for the checkout button to be available first.
        //Once available, we use the Xpath to find it and then click on it
        WebElement checkOutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(checkOutBtnXPath)));

        //Once button is available, we click on it
        checkOutButton.click();

        //wait for this to navigate to the Add address screen
        Thread.sleep(5000);
    }


    @Test(priority = 4) //after clicking on add to cart button, wait for the address screen to load
    //After loading, find the add address button and click on it
    public void ClickAddressButton() throws InterruptedException {
        //Wait for the find address screen to load
        String addressBtnXPath = "//*[@id=\"mainContent\"]/div/form/div/div[1]/section[1]/div/div/div[2]/div[1]/div[2]/div[1]/div/button";

        //wait for this Add Address Button to become available before clicking on it
        Duration timeout = Duration.ofSeconds(50);
        //wait for the checkout button to be available before clicking and adding address
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        //wait for the Add Address Button to be available first.
        //Once available, we use the Xpath to find it and then click on it. We pass the XPath into the method
        WebElement addressButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(addressBtnXPath)));

        //Once the Add Address button is available, we click on it and provide our address
        addressButton.click();
        //wait for this to navigate to the address form
        Thread.sleep(3000);

    }

    @Test(priority = 5) //fill the address form after it loads
    public void CompleteAddressForm() throws InterruptedException {
        //wait for the address form to load before filling it.
        //We will use the first item in the form to judge this. Wait for the firstname to appear before we proceed
        //wait for this Add Address Button to become available before clicking on it
        String firstNameId = "firstname";
        String lastNameId = "lastname";
        String phoneNoId = "telephone";
        String streetAddressId = "street";
        String cityId = "city";
        String stateByName = "regionId";
        String lgaByXPath = "//*[@id='app-content-wrapper']/div[2]/section/section/aside/div[2]/div/div/form/div[7]/div/div/select";
        Duration timeout = Duration.ofSeconds(10);
        //wait for the first Name input box to be available before filling the rest with the input box
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        //wait for the First name to be available first.
        //Once available, we use the Xpath to find it and then fill the values. We pass the XPath into the method
        WebElement firstInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(firstNameId)));

        //once first name is available, all others will be available as well
        firstInput.sendKeys("Miriam");
        //We can then find all others and set their values

        //for lastname values
        driver.findElement(By.id(lastNameId)).sendKeys("Ajayi");

        //for phoneNo values
        driver.findElement(By.id(phoneNoId)).sendKeys("08163347323");

        //for street  values
        driver.findElement(By.id(streetAddressId)).sendKeys("23 Ajobiewe street, Ogba, lagos");

        //for city values
        driver.findElement(By.id(cityId)).sendKeys("Ogba");

        //for state values
        driver.findElement(By.name(stateByName)).sendKeys("Lagos");

        //for lga values
        //wait for the lga item to be fully loaded and available before clicking
        WebElement lgaInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(lgaByXPath)));

        //once the lga input box is fully available, set the input value to Ikeja
        lgaInput.sendKeys("Ikeja");

        //wait for some small-time and click the continue button. we get it by name: saveDeliveryAddress

        Thread.sleep(1000);

        //There is another item here covering the button we want to click on,
        //We can use the Action method to move the cursor to the button so that we can click the part
        //not covered by the overlapping element and thereafter click the button
        //driver.findElement(By.name("saveDeliveryAddress")).click();
        WebElement submitButton = driver.findElement(By.name("saveDeliveryAddress"));

        JavascriptExecutor executor = (JavascriptExecutor)driver;  //we used javascript to locate the button by casting
        //by casting the Webdriver instance into a Javascript executor and we used it to execute
        // the first instance of the submit button found on the page
        executor.executeScript("arguments[0].click();", submitButton);
        //arguments[0] means occurrence of the first instance of the button on the page
        //This help us to avoid the overlapping element
        Thread.sleep(2000);



    }

    @Test(priority = 6) //after the above, confirm the filed in address in the Address book,
    // close the modal address and continue to payment method
    public void SelectDeliveryAddress() throws InterruptedException {

        //wait for the page to fully load and available before you click on the radio button to
        //confirm the filled in address
        String addressRadioButtonByName = "selectDeliveryAddress";

        Duration timeout = Duration.ofSeconds(40);
        //wait for the select delivery address box to be fully available
        // before clicking on the address radio button
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        //wait for the radio button to be available first.
        //Once available, we use the name property to find it and then click on it to select it. We pass the addressRadioButtonByName into the method
        WebElement selectAddressRadioBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(addressRadioButtonByName)));

        //once fully available, we click on it
        selectAddressRadioBtn.click(); //This will select the address to use

        Thread.sleep(2000);  //wait a bit to complete the clicking before clicking on the use this button address

        //We can then click on the use this address button that appears so it can navigate to the payment method page

        //find the Use this address button that appears by XPath = //*[@id="app-content-wrapper"]/div[2]/section/section/aside/div[3]/div/div/div/a

        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[3]/div/div/div/a")).click();

        Thread.sleep(2000); //wait while it navigates to the select payment page
    }


    @Test(priority = 7) //This selects the card payment method
    public void SelectCardPayment() throws InterruptedException {

        //wait for the radio button for making card payment to be available before clicking on it.

        String cardPaymentRadioXPath = "//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[1]/div[1]/span/input";

        Duration timeout = Duration.ofSeconds(40);
        //wait for the cardPayment page to be fully available
        // before clicking on the cardPayment radio button
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        //wait for the cardPayment radio button to be available first.
        //Once available, we use the Xpath property to find it and then click on it to select it. We pass the cardPaymentRadioXPath into the method
        WebElement cardPaymentRadioBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(cardPaymentRadioXPath)));

        //once the pay now button is fully available, we click on it
        cardPaymentRadioBtn.click();

        Thread.sleep(2000);

        //wait for to continue to payment button to appear and then click on it.
        // We use the name attribute: placeOrder to locate continue to payment button and click on it.
        driver.findElement(By.name("placeOrder")).click();
        Thread.sleep(2000);

    }


    @Test(priority = 8) //A modal appears for selecting payment method,
    //select card payment and close the modal.
    public void SelectCardPaymentAndMakePayment() throws InterruptedException {

        Thread.sleep(2000);
        //wait for the modal for the card payment inside the container they
        // are to appear before we choose the card option
        //we use the name property called Card to locate this card
        String iFrameId = "kpg-frame-component";
        String cardPaymentXPath = "//*[@id=\"channel-template\"]/div[2]/div/div[2]/button";
        Duration timeout = Duration.ofSeconds(50);
        //wait for the iFrame modal to be fully available
        // before clicking on the card Payment
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        //wait for the iFrame modal to be available first and after that
        //we will switch the driver to this Iframe so it can get any element we
        //want from it.

        WebElement modalIFrame = driver.findElement(By.id(iFrameId));

        //Switch the driver to this iFrame that appears
        driver.switchTo().frame(modalIFrame); //once switched, all the elements will be accessible

        // Find the cardPayment in the modal and click it since we are now in the environment of the modal
        WebElement cardPayment  = driver.findElement(By.xpath(cardPaymentXPath));

        //click the card payment
        cardPayment.click();
        //wait some minutes for this to navigate to the card details page before filling in the details
        Thread.sleep(2000);

        //wait for the Debit card inputs to be available before filling it with fake card details
        //we use the id = card-number to locate it
        //we use the timeout above and the WebDriver wait method
        String cardNumberId = "card-number";
        WebElement cardNoInputBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(cardNumberId)));
        //once it is available, the other inputs too wil be available
        //use fake debit card no
        String fakeCardNo = "5568890788662345";

        cardNoInputBox.sendKeys(fakeCardNo);
        //we use the id of the expiry input called expiry
        String fakeExpiryDate ="09/27";
        driver.findElement(By.id("expiry")).sendKeys(fakeExpiryDate);

        //we do same to Cvv, we use the id which is cvv
        String fakeCvv = "673";
        driver.findElement(By.id("cvv")).sendKeys(fakeCvv);

        //we do same for card pin, we use the id which is card-pin-new
        String fakePin = "0937";
        driver.findElement(By.id("card-pin-new")).sendKeys(fakePin);

        String expectedErrorMessageId = "card-number_unhappy";
        String expectedErrorMessage = "Invalid card number";

        //compare the messages expected and the actual message to see if the test passed or failed

        if(driver.findElement(By.id(expectedErrorMessageId)).getText().contains(expectedErrorMessage)){

            System.out.println("Test Passed Successfully!!");
        }else{
            System.out.println("Test Failed!!");
        }

        Thread.sleep(2000);

        String closeModalBtnXPath = "/html/body/section/section/section/div[2]/div[1]/aside";
        //use the close modal button to close the modal
        driver.findElement(By.xpath(closeModalBtnXPath)).click();
        Thread.sleep(2000);
    }


    @AfterTest//after all the test completes, close the browser
    public void CloseBrowser(){
        driver.quit();
    }


}
