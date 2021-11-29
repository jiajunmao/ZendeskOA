# Zendesk Coding Challenge Demo Project

## Project objectives
- Connect to the Zendesk API
- Request all tickets of the user account
- Display all tickets in a list
- Display individual ticket details
- Paging through tickets

## Project overview
The project is coded in Java and the user interface is implemented using CLI. Junit5 is used for unit testing the framework.

There are two external libraries used for this project
- asciitable [[link]](https://github.com/vdmeer/asciitable) - used for generating nice looking ascii table output
- snakeyaml [[link]](https://github.com/spariev/snakeyaml) - used for parsing some configuration files related to cli

## Project usage
### Running the CLI
- Prebuilt JAR
  - A prebuilt jar can be found under `build/libs` with the name `Zendesk-1.0-SNAPSHOT-all.jar`. Simply executing the command `java -jar Zendesk-1.0-SNAPSHOT-all.jar` while in `build/libs` directory can run the CLI
- Using gradle wrapper
  - Since this project is built on gradle, you can also use the gradle wrapper through `./gradlew --console plain run` to run the CLI

### User credentials
User credentials environmental variables must be set prior to using this project. Two env variables are required:
- **ZENDESK_USERNAME**: your email for the zendesk account
- **ZENDESK_PASSWD**: your **API token** for zendesk (using naive password will result in authorization failure)

### Menu structure
- Root Menu
  - In the root menu, you can select the following actions by entering the key text before dash(-) and press enter
    - 1 - List all tickets
    - 2 - Select a ticket to view details
    - exit - Exit the program
  - For example, if you would like to ist all the tickets, you would enter 1 in the CLI and then press enter
- List all ticket menu
  - In the list all ticket menu, you will be able to page through pages of tickets* by entering the key text before dash(-) and press enter
    - \# - Input the page that you wish to page to
    - return - return to the previous menu (the root menu)
  - **Note!** the pound sign(#) does not mean the actual pound sign, but rather an integer number
- Ticket detail menu
  - In the ticket detail menu, you can select a specific ticket to display its more complete information by entering the key text before dash(-) and press enter
    - \# - The ticket ID that you wish to display the more complete information
    - return - return to the previous menu (the root menu)

## Design Considerations
- List all interface page max of 5 instead of 25
  - A max entry of 5 tickets is used when displaying all tickets in the list all ticket menu to avoid overwhelming the user with texts on the CLI. Furthermore, the underlying library(asciitable) has performance bottlenecks that would make the wait of generating ascii table quite an agony if 25 tickets are displayed at once. You can, however, customize that limit by changing the `PAGE_LEN` parameter in `ListAllHandler.java`
- Separation of menu text in `menu.yml`
  - This separation of text and actual hardcode allows us to (in future) implement reload functions that can hot-reload CLI menu info without shutting down and restarting the program (might be useful in some scenarios)
- No-caching
  - Caching was considered for both list all ticket and select a ticket feature where a HashMap could be used in storing all queried and returned tickets locally, and grab from the HashMap when user asks for a already queried ticket. However, this design was ultimately not adopted because of data inconsistency issue that would exist if a ticket is modified while user is using the program and the cache is valid.
