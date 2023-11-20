# Take-home assignment from Taboola.

## Problem 1 - JSON Parser
The parser has two parts - lexer/tokenizer and the parser itself. Parser is implemented using recursive descent algorithm. 

There are a couple of things I would add if I had more time:
* Tests
* Handling escape characters
* Ability to get input from `stdin` or file

## Problem 2 - Binary Tree Serialization
I couldn't quite figure out what to do in case the cycle is detected and I can't throw a `RuntimeException`. Of course, continuing the serialization from the node that caused the cycle is not an option and I would need to somehow mark the spot as a cycle and backtrack, but I couldn't figure out how to do that in a way as to make deserialization understand it. 
Also, the picture in the assignment example doesn't quite show a cycle - the connection should be to one of the node's parents, not siblings.

Things to improve:
* Tests
* Implement Q2Bonus
* Implement Q3 with generics

## Problem 3 - MySQL Script
Not many comments here, just a typical script.