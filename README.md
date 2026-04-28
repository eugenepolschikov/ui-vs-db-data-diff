## Problem state
There are multiple hospital/clinics chains, which are collecting PHI data,
specifically about the incidents within hospital chains. 
And PHI records within one hospital/ hospital chain should not be exposed for/into
another hospital.
Input: every hospital have its own website  (admin-backoffice) and database PHI with 
records, having unique records of PHI data.

# Find all PHI records incidents NOT belonging to the hospital chain. Ideation at high level
Login to UI and grab all the records from UI. 
Extract resultset from DB (for particular hospital/hospitalchain). 
And the difference between these two somehow needs to be evaluated.

# More rigorous mathematical solution
From mathematical standpoint set data structure and set difference operation 
fits more than perfectly here (attaching the image below).

# Key implementation points
* BasePage includes common waits/mechanisms:
`explicit wait (WebDriverWait)`
`fluent wait (FluentWait)`
`reusable actions (click, type, visibility checks, table-load wait)`
* LoginPage:
`opens ${base.url}/login`
`supports loginAs(username, password)`
* HomePage:
`verifies page/table load`
`reads PHI table rows as raw text list`
* DriverFactory:
`initializes Chrome driver with WebDriverManager`
`sets implicitWait = 0 and page load timeout.`
* Verification
Ran: ```mvn -q -DskipTests compile```
 
How to run by environment
Dev: ```mvn test -Pdev```
Stage: ```mvn test -Pstage```
Prod: ```mvn test -Pprod```

To ensure that build still pass w/o introducing breaking changes:
```mvn -DskipTests compile```