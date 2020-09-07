# Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project dialog first)
1. Set up the correct JDK version, as follows:
   1. Click `Configure` > `Structure for New Projects` and then `Project Settings` > `Project` > `Project SDK`
   1. If JDK 11 is listed in the drop down, select it. If it is not, click `New...` and select the directory where you installed JDK 11
   1. Click `OK`
1. Import the project into Intellij as follows:
   1. Click `Open or Import`.
   1. Select the project directory, and click `OK`
   1. If there are any further prompts, accept the defaults.
1. After the importing is complete, locate the `src/main/java/ip.Duke.java` file, right-click it, and choose `Run Duke.main()`. If the setup is correct, you should see something like the below:
   ```
        ___  ________  ________  ________
       |\  \|\   __  \|\_____  \|\_____  \
       \ \  \ \  \|\  \\|___/  /|\|___/  /|
     __ \ \  \ \   __  \   /  / /    /  / /
    |\  \\_\  \ \  \ \  \ /  /_/__  /  /_/__
    \ \________\ \__\ \__\\________\\________\
     \|________|\|__|\|__|\|_______|\|_______|
    ------------------------------------------------------------
     Hey there! The name's Jazz.
     What can I do for you?
    ------------------------------------------------------------
   ```
