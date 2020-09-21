# User Guide

## Features 

### Add `Task`
Add a `Task` to the task manager. 3 types of `Task`s are available - `Todo`, `Deadline`, or `Event`. 
`Deadline`s and `Event`s have dates or times attached to them.

### List `Task`s
List all `Task`s currently in the task manager.

### Mark `Task` as Done
Mark a `Task` in the task manager as completed.

### Delete `Task`
Remove a `Task` from the task manager.

### Exit Program
Exit from the application.

## Usage

The format of commands below keep to the following.

| :information_source: | Words in `UPPER_CASE` are parameters |
|----------------------|:-------------------------------------|

### Adding a `Todo`: `todo`

Adds a `Todo` `Task` to the task manager.

Format: `todo TASK`

Example of usage: 

`todo sleep`

### Adding a `Deadline`: `deadline`

Adds a `Deadline` `Task` to the task manager with an attached deadline.

Format: `deadline TASK \by DATETIME`

Example of usage:

* `deadline homework \by 2020-09-22 23:59`
* `deadline clean room \by tonight`

### Adding an `Event`: `event`

Adds an `Event` `Task` to the task manager with an attached timing.

Format: `event TASK \at DATETIME`

Example of usage:

* `event concert \at 2020-09-22 20:00`
* `event watch movie \at 2pm`

| :bulb: | For `Event`s and `Deadline`s `DATETIME`, follow the ISO format (`YYYY-MM-DD HH:MM`) to allow for easier printing and searching of the `Task` by its date. |
|--------|:----------------------------------------------------------------------------------------------------------------------------------------------------|

### List all `Task`s: `list`

Lists all `Task`s in the task manager.

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

Marks a specified `Task` as done. Generally, this is used in conjunction after `list` to find out the ID of the task needed.

Format: `done ID`

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

Format: `delete ID`

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