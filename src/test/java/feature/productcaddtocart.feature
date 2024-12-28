Feature: Add to Cart

  As a customer of the AskOmDch app,
  I want to be able to add product(s) to my cart,
  So that I can purchase them conveniently.

  Rule: Add from Store

    @smoke
    Scenario Outline: Add a single quantity from the Store
      Given I am on the Store Page
      When I add "<product_name>" to the cart
      Then I should see 1 "<product_name>" in the cart

      Examples:
        | product_name    |
        | Blue Shoes      |
        | Anchor Bracelet |

  