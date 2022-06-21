package QKART_SANITY_LOGIN;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResult {
    WebElement parentElement;

    public SearchResult(WebElement SearchResultElement) {
        this.parentElement = SearchResultElement;
    }

    /*
     * Return title of the parentElement denoting the card content section of a search result
     */
    public String getTitleofResult() {
        String titleOfSearchResult = "";
        WebElement title =
                parentElement.findElement(By.xpath("//p[contains(@class,'css-yg30e6')]"));
        titleOfSearchResult = title.getText();
        return titleOfSearchResult;
    }

    /*
     * Return Boolean denoting if the open size chart operation was successful
     */
    public Boolean openSizechart() {
        try {
            Thread.sleep(2000);
            WebElement sizeChart =
                    parentElement.findElement(By.xpath("//button[text()='Size chart']"));
            Thread.sleep(3000);
            sizeChart.click();
            return true;
        } catch (Exception e) {
            System.out.println("Exception while opening Size chart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the close size chart operation was successful
     */
    public Boolean closeSizeChart(WebDriver driver) {
        try {
            Thread.sleep(2000);
            Actions action = new Actions(driver);

            action.sendKeys(Keys.ESCAPE);
            action.perform();
            Thread.sleep(2000);
            return true;
        } catch (Exception e) {
            System.out.println("Exception while closing the size chart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean based on if the size chart exists
     */
    public Boolean verifySizeChartExists() {
        Boolean status = false;
        try {
            /*
             * Check if the size chart element exists. If it exists, check if the text of the
             * element is "SIZE CHART". If the text "SIZE CHART" matches for the element, set status
             * = true , else set to false
             */
            WebElement link = parentElement.findElement(By.xpath("//button[text()='Size chart']"));
            Thread.sleep(4000);
            // button[normalize-space()='Size chart']
            if (link.isDisplayed()) {
                if (link.getText().toUpperCase().equals("SIZE CHART")) {
                    return true;
                }
            }
            return status;
        } catch (Exception e) {
            return status;
        }
    }

    /*
     * Return Boolean if the table headers and body of the size chart matches the expected values
     */
    public Boolean validateSizeChartContents(List<String> expectedTableHeaders,
            List<List<String>> expectedTableBody, WebDriver driver) {
        Boolean status = true;

        int sizeTableHeader = expectedTableHeaders.size();
        // int sizeTableData = expectedTableBody.size();


        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2

            // * Locate the table element when the size chart modal is open
            List<WebElement> tableHead = driver.findElements(By.xpath("//table/thead/tr//th"));
            List<WebElement> tableData = driver.findElements(By.xpath("//table//tr//td"));
            // * Validate that the contents of expectedTableHeaders is present as the table
            // * header in the same order
            if (sizeTableHeader == tableHead.size()) {
                for (int i = 0; i < sizeTableHeader; i++) {
                    Thread.sleep(2000);
                    status = expectedTableHeaders.get(i).equals(tableHead.get(i).getText());
                    if (status == false) {
                        return false;
                    }
                }
            }
            // * Validate that the contents of expectedTableBody are present in the table body
            // * in the same order
            int i = 0;
            for (List<String> list : expectedTableBody) {

                for (String item : list) {
                    Thread.sleep(2000);
                    status = item.equals(tableData.get(i).getText());
                    if (status == false) {
                        return false;
                    }
                    i++;
                }

            }
            return status;

        } catch (Exception e) {
            System.out.println("Error while validating chart contents");
            return false;
        }
    }

    /*
     * Return Boolean based on if the Size drop down exists
     */
    public Boolean verifyExistenceofSizeDropdown(WebDriver driver) {
        Boolean status = false;
        try {
            WebElement dropDown = driver.findElement(By.id("uncontrolled-native"));
            status = dropDown.isDisplayed();
            return status;
        } catch (Exception e) {
            return status;
        }
    }
}
