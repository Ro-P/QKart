package QKART_SANITY_LOGIN;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home {
    RemoteWebDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app";

    public Home(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void navigateToHome() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    public Boolean PerformLogout() throws InterruptedException {
        try {
            // Find and click on the Logout Button
            WebElement logout_button = driver.findElement(By.className("MuiButton-text"));
            logout_button.click();

            // SLEEP_STMT_10: Wait for Logout to complete
            // Wait for Logout to Complete
            Thread.sleep(3000);

            return true;
        } catch (Exception e) {
            // Error while logout
            return false;
        }
    }

    /*
     * Returns Boolean if searching for the given product name occurs without any
     * errors
     */
    public Boolean searchForProduct(String product) {
        try {
            WebElement searchBox = driver.findElement(By.xpath("(//input[@name='search'])[1]"));
            searchBox.click();
            searchBox.clear();
            searchBox.sendKeys(product);
            //Thread.sleep(5000);
            
             WebElement displayproduct =displayElement(driver);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOf(displayproduct));
                 return true;  
        }catch(Exception e)
        {
            System.out.println("Error while searching for a product: " + e.getMessage());
            return false;
        }
    }
    
    public static WebElement displayElement(WebDriver driver) throws Exception {
        WebElement present;
      try{  if (driver.findElement(By.xpath("//h4[contains(text(),'No products found')]")).isDisplayed()) 
         return  present = driver.findElement(By.xpath("//h4[contains(text(),'No products found')]"));
   } catch (Exception e) {
      return present = driver.findElement(By.xpath("//p[contains(@class,'css-yg30e6')]"));
   }
   return present = null;
     }



    /*
     * Returns Array of Web Elements that are search results and return the same
     */
    public List<WebElement> getSearchResults() {
        List<WebElement> searchResults = new ArrayList<WebElement>() {
        };
        try {
            searchResults = driver.findElements(By.className("css-1qw96cp"));
            Thread.sleep(2000);
            return searchResults;
        } catch (Exception e) {
            System.out.println("There were no search results: " + e.getMessage());
            return searchResults;

        }
    }

    /*
     * Returns Boolean based on if the "No products found" text is displayed
     */
    public Boolean isNoResultFound() {
        Boolean status = false;
        try {
            WebElement isProductFound =
                    driver.findElement(By.xpath("//*[contains(text(),' No products found')]"));
            // if(isProductFound.isDisplayed())
            if (isProductFound.isDisplayed()) {
                if (isProductFound.getText().equalsIgnoreCase("No products found")) {
                    return status = true;
                }
            }
            return status;
        } catch (Exception e) {
            return status;
        }
    }

    /*
     * Return Boolean if add product to cart is successful
     */
    public Boolean addProductToCart(String productName) {
        try {
            /*
             * Iterate through each product on the page to find the WebElement corresponding
             * to the matching productName
             * 
             * Click on the "ADD TO CART" button for that element
             * 
             * Return true if these operations succeeds
             */
            List<WebElement> searchResults =
                    driver.findElements(By.xpath("//p[contains(@class,'css-yg30e6')]"));
            if (searchResults.size() != 0) {
                for (WebElement webElement : searchResults) {

                    String title = webElement.getText();

                    if (title.equals(productName)) {
                        // * Click on the "ADD TO CART" button for that element
                        WebElement addToCartButton = driver.findElement(By.className("css-54wre3"));
                        addToCartButton.click();
                        // Return true if these operations succeeds
                        return true;
                    }
                }
            }
            System.out.println("Unable to find the given product");
            return false;
        } catch (Exception e) {
            System.out.println("Exception while performing add to cart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting the status of clicking on the checkout button
     */
    public Boolean clickCheckout() {
        Boolean status = false;
        try {
            WebElement checkOutButton = driver.findElement(By.className("checkout-btn"));
            checkOutButton.click();
            return status;
        } catch (Exception e) {
            System.out.println("Exception while clicking on Checkout: " + e.getMessage());
            return status;
        }
    }

    /*
     * Return Boolean denoting the status of change quantity of product in cart
     * operation
     */
    public Boolean changeProductQuantityinCart(String productName, int quantity) {
        try {
            boolean status = false;
            List<WebElement> itemsInTheCart =
                    driver.findElements(By.xpath("//div[contains(@class,'css-1gjj37g')]/div[1]"));
            WebDriverWait wait = new WebDriverWait(driver, 30);
            for (int i = 0; i < itemsInTheCart.size(); i++) {
                if (itemsInTheCart.get(i).getText().equalsIgnoreCase(productName)) {

                    String xpathForQuantity =
                            "(//div[contains(@class,'css-olyig7')])[" + (i + 1) + "]";
                    WebElement quantityDisplayed = driver.findElement(By.xpath(xpathForQuantity));
                    String getQuantity = quantityDisplayed.getText();
                    int quantityInInteger = Integer.parseInt(getQuantity);
                    int counter = 0;

                    while ((quantityInInteger != quantity)) {

                        if (quantityInInteger < quantity) {
                            String xpath = "(//div[contains(@class,'css-olyig7')])[" + (i + 1)
                                    + "]/following-sibling::button";
                            WebElement addQuantity = driver.findElement(By.xpath(xpath));
                           // Thread.sleep(2000);
                            addQuantity.click();
                            counter = quantityInInteger + 1;
                            wait.until(ExpectedConditions.textToBePresentInElementLocated(
                                    By.xpath(xpathForQuantity), String.valueOf(counter)));
                            Thread.sleep(3000);
                            
                        }
                        if ((quantityInInteger > quantity)) {
                            String xpath = "(//div[contains(@class,'css-olyig7')])[" + (i + 1)
                                    + "]/preceding-sibling::button";
                            WebElement removeQuantity = driver.findElement(By.xpath(xpath));
                            removeQuantity.click();
                            //Thread.sleep(3000);
                            counter = quantityInInteger - 1;
                            wait.until(ExpectedConditions.textToBePresentInElementLocated(
                                    By.xpath(xpathForQuantity), String.valueOf(counter)));
                        }
                        if (counter != 0) {
                            quantityDisplayed = driver.findElement(By.xpath(xpathForQuantity));
                            getQuantity = quantityDisplayed.getText();
                            quantityInInteger = Integer.parseInt(getQuantity);
                        }
                    }
                    return status = true;
                }
            }
            return status;
        } catch (Exception e) {
            if (quantity == 0)
                return true;
            System.out.println("exception occurred when updating cart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the cart contains items as expected
     */
    public Boolean verifyCartContents(List<String> expectedCartContents) {
        try {
            // Get all the cart items as an array of webelements
            List<WebElement> itemsInTheCart =
                    driver.findElements(By.xpath("//div[contains(@class,'css-1gjj37g')]/div[1]"));
            // Iterate through expectedCartContents and check if item with matching product
            // name is present in the cart
            int count = 0;
            for (String item : expectedCartContents) {
                for (WebElement itemDisplayed : itemsInTheCart) {
                    if (itemDisplayed.getText().equalsIgnoreCase(item)) {
                        count++;
                    }
                }

            }
            if (count == expectedCartContents.size()) {
                return true;
            }
            return true;

        } catch (Exception e) {
            System.out.println("Exception while verifying cart contents: " + e.getMessage());
            return false;
        }
    }
}
