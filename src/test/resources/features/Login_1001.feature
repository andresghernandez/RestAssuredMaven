#Auto generated Octane revision tag
@TID1001REV0.2.0
Feature: login rest assured
	
    @CASO1001
	Scenario Outline: CASO1001 login 
		Given that I need to log in to the page <row>
		When send the data to log in  
		Then I get a successful response

	Examples:
		| row | 
		| 1   | 