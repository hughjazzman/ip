# User Guide

Refer to the [Setting up](https://github.com/hughjazzman/ip/blob/master/README.md#setting-up-in-intellij) guide for instructions on how to set up the project on IntelliJ.

## Features 

### Add `Task`
Add a `Task` to the task manager. 3 types of `Task`s are available - `Todo`, `Deadline`, or `Event`. 
`Deadline` and `Event` objects have dates or times attached to them.

### List `Task`
List all `Task` objects currently in the task manager.

### Mark `Task` as Done
Mark a `Task` in the task manager as completed.

### Delete `Task`
Remove a `Task` from the task manager.

### Find `Task`
Search for all `Task` objects by their description using a keyword or phrase.

### Exit Program
Exit from the application.

### Saving the Data
`Task` data are saved in the hard disk automatically after any command that changes the data.

There is no need to save manually.

## Usage

The format of commands below keep to the following.

| :information_source: | Words in `UPPER_CASE` are parameters. |
|----------------------|:-------------------------------------|

### Adding a `Todo`: `todo`

Adds a `Todo` `Task` to the task manager.

Format: `todo TASK`

Example of usage: 

`todo sleep`

### Adding a `Deadline`: `deadline`

Adds a `Deadline` `Task` to the task manager with an attached deadline.

Format: `deadline TASK /by DATETIME`

Example of usage:

* `deadline homework /by 2020-09-22 23:59`
* `deadline clean room /by tonight`

### Adding an `Event`: `event`

Adds an `Event` `Task` to the task manager with an attached timing.

Format: `event TASK /at DATETIME`

Example of usage:

* `event concert /at 2020-09-22 20:00`
* `event watch movie /at 2pm`

`Deadline` and `Event` objects can store the `DATETIME` as `LocalDate` and `LocalTime` objects,
if it is given in the ISO format, allowing for easier searching and printing of the date.

| :bulb: | Follow the ISO format (`YYYY-MM-DD HH:MM`) for `DATETIME`. |
|--------|:----------------------------------------------------------|

### List `Task`: `list`

Lists all `Task` objects in the task manager.

Example of usage:

`list`

Expected outcome:

```
------------------------------------------------------------
Here are the tasks in your list:
 1.[T][/] read book
 2.[D][X] return book (by: June 6 2020 23:59:00)
 3.[E][X] project meeting (at: Aug 6th 2-4pm)
 4.[T][/] join sports club
 5.[T][X] borrow book
------------------------------------------------------------
```

### Mark `Task` as done: `done`

Marks a specified `Task` as done. Generally, this is used in conjunction after `list` to find out the index of the task needed.

Format: `done INDEX`

Example of usage:

`done 5`

Expected outcome:

```
------------------------------------------------------------
Nice! I've marked this task as done:
 [T][/] borrow book
------------------------------------------------------------
```

### Delete `Task`: `delete`

Removes a specified `Task` from the task manager. Similar to `done`, this is used with `list`.

Format: `delete INDEX`

Example of usage:

`delete 1`

Expected outcome:

```
------------------------------------------------------------
 Noted. I've removed this task:
   [T][/] read book
 Now you have 4 tasks in the list.
------------------------------------------------------------
```

### Find `Task`: `find`

Finds all `Task` objects that match the searched term or phrase, and prints them as a list.

Format: `find KEYWORD`

Example of usage:

`find book`

Expected outcome:

```
------------------------------------------------------------
Here are the matching tasks in your list (search: book):
 1.[T][/] read book
 2.[D][X] return book (by: June 6 2020 23:59:00)
 3.[T][X] borrow book
------------------------------------------------------------
```

### Exit Application: `bye`

Exits the application process and prints a farewell message.

Example of usage:

`bye`

Expected outcome:

```
------------------------------------------------------------
 Bye. See you next time!
------------------------------------------------------------
```

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Duke home folder.

## Command summary

Action | Format, Examples
--------|------------------
**Todo** | `todo TASK` <br> e.g., `todo sleep`
**Deadline** | `deadline TASK /by DATETIME` <br> e.g., `deadline homework /by 2020-09-22 23:59`
**Event** | `event TASK /at DATETIME` <br> e.g., `event concert /at 2020-09-22 20:00`
**List** | `list`
**Done** | `done INDEX`<br> e.g., `done 1`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Find** | `find KEYWORD`<br> e.g., `find book`