# PumaAddtocartTC

Automation testing/SDETs: Automate the following scenario: 
Navigate to "https://in.puma.com" and assert the page title
Click on Men/Shoes/Running
Click on the second shoe from listing page
Add the shoe to cart from product detail page (for any size or quantity)
Assert that the cart has the correct shoe selected in previous step (Based on name of product/quantity/price)

Framework used:
Selenium/JAVA/TestNG/MAVEN.


Please provide chromedriver.exe path in AppTest class to run program. example:
private static final String CHROME_DRIVER_PATH = "//download//..//chromedriver";

To run : mvn clean install
