# ZavrsniProjekat
Version: 1.0.0


### ZavrsniProjekat is used for testing functionalities  

Target application URL is: ``https://petstore.octoperf.com/``  
Target browser: Chrome  
Browser version: 80.0.3987.149

Run test suite: `testng.xml`
Total tests run: 10

### Used libraries: 
- TestNG automation testing framework  
- Selenium portable framework for testing web applications 
- Apache POI library for manipulating various file formats 

### The following website functionalities are tested: 
- enter the store
- menu links status
- menu links paths
- user registration
- user sign in 
- add items to cart
- accuracy of price calculation
- cart content after deleting cookies

## This project contains 3 packages:
- pages
- test
- utils
All packages are in the `src` folder.

## Package `pages` contains 6 classes:
- HomePage
- PetStoreMenuPage
- RegistrationPage
- SignInPage
- StoreItemPage
- CartPage

## Package `test` contains 5 classes:
- HomeEnterTest
- PetStoreMenuTest
- RegistrationTest
- SignInTest
- CartTest

## Package `utils` contains 1 class:
- ExcelUtils

## Folder `config` contains 1 file:
- config.properties

## Folder `data` contains 1 file:
- pet-store-data.xlsx
