Feature: mailChimp

  Scenario Outline: Test the signup page
    Given I choose a username <username>
    Given I signup with an email <email>
    Given I choose a password <password>
    When I press the sign up button
    Then im signed up for the service

    Examples:
    |username|password|email|
    |"filifjonkan123456"|"feLix123-"|"felix@bbbbmail.se"|
    |"flixo" |"abC1234-"|"hej@mail.com"|
    |"somethingKonstigt" |"aBcd1234-" |""|
    |"adcvbnhjklmadcvbnhjklmadcvbnhjklmadcvbnhjklmadcvbnhjklmadcvbnhjklmadcvbnhjklmadcvbnhjklmadcvbnhqesrty"|"avcD123-"|"felixbl@bmail.se"|

