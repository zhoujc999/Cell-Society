Refactoring Discussion
===

### Duplication Refactoring

* Instances of duplication code were not found


### Checklist Refactoring

* I fixed a couple of the checklist issues. I replaced all instances of checking an array of size 0 by array.empty.

* I also simplified 2 conditional loops in the Grid class to directly return a boolean instead of additional if else statements.

* I also refactored a few nested loops, invoking new methods and combining conditional statements.


### General Refactoring

* I refactored 2 nested loops, which contained if...for...if to invoke a new method.


### Longest Method Refactoring

* I refactored my longest method in FireCell class into 2 methods, which improved the readability of the original method.


