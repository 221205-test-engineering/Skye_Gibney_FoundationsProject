Feature: Manager & Matrix

# Declarative Style

    Background: 
        Given The manager is logged in as a manager 
        Given The manager is on the home page

    Scenario: Create a New Matrix
        When The manager chooses to create a new matrix
        When The manager creates a title for the matrix 
        When The manager adds requirements to the matrix
        When The manager saves the matrix 
        Then An alert with a success message should appear

    Scenario: Update Defects
        Given The manager is on the matrix homepage
        Given The manager has selected the matrix
        When The manager adds a defect
        When The manager confirms their changes 
        Then Then the matrix should saved  

    Scenario: Update Test Cases
        Given The manager is on the matrix homepage
        Given The manager has selected the matrix
        When The manager adds a Test Cases
        When The manager confirms their changes 
        Then Then the matrix should saved  

