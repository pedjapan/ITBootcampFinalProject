# ITBootcampFinalProject
Final project for ITBootcamp QA training course in Nis, 2020.

Version: 1.0.0

Project is use for testing functionalities.

Target application URL is: https://petstore.octoperf.com

Target browsers: 

    • Google Chrome, Version 80.0.3987.163 (Official Build) (64-bit)
    
    • Mozilla Firefox, Version 74.0.1 (Official Build) (64-bit)
    
    • Microsoft Edge, Version 80.0.361.109 (Official build) (64-bit) 

Run test suite: testng.xml. Total tests run: 27.

Used libraries:

    • TestNG automation testing framework
    
    • Selenium portable framework for testing web applications
    
    • Apache POI library for manipulating various file 
    
Locators are located in config folder.

Data for testing are located in data folder.

The following website functionalities are tested:

    • enter the store    
    • does links from all three menus works and do they take to correct page
    • registering users filling the form using data from .xlsx file
    • sign in using same users
    • adding a products from .xlsx file and testing does product from file match the product in cart 
    • deleting cookies for testing is cart empty
    • testing is total product price in cart correct 
      
This project contains 3 packages:

    • pages
    
    • tests
    
    • utils
    
All packages are in the src folder.

Package pages contains 6 classes:

    • CartPage
    
    • HomePage
    
    • PetStoreMenuPage
    
    • RegistrationPage
    
    • SignInPage
    
    • StoreItemPage
    
Package tests contains 8 classes:

    • CartPageTest
    
    • HomePageTest
    
    • PetStoreMenuPageTest
    
    • RegistrationPageTest
    
    • SignInPageTest
    
Package utils contains one class:

    • ExcelUtils
    
Utils and methods from ExcelUtils class are used for manipulating a .xlsx file.

Folder data contains:

    • pet-store-data.xlsx file 
      
 
