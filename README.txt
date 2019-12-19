To build the project you will need version Java 8 installed, please, find instructions here: https://java.com/en/download/manual.jsp.

You will also need maven installed on your computer, please, find instructions here: https://maven.apache.org/install.html



To build the project run the following command:

mvn clean install

This command will compile code and run tests.

The class containing main() method is GroceryApp. After launching the following prompt is printed to console:

Enter product and optionally amount separated by space or Enter to finish:

The user can enter one of the following: soup, bread, milk, apple - and amount after space and press Enter.
If amount is not entered - 1 is the default value.
After pressing Enter without any othe input - resulting price is printed to console.
