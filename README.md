Huffman Tree Java
=================

Advanced Data Structures in Java was a fun class.  There was a lot to learn and the pace was good.  I readily admit that Java is not my strongest language, but that is one of the reasons I took the class in Java.  I wanted to push myself.  One of our projects was an implementation of the Huffman encoding, which made strides in data compression.  There are numerous links on the web regarding Huffman encoding.  Feel free to use this code if it will help you understand Huffman encoding.

The specific requirements for this project were:

1) Read a file containing characters and counts, as shown in the input files below.

2) Construct a Huffman coding tree for the characters using data from step 1.

3) Output a table showing each character, the number of occurrences of the character, and the computed Huffman codeword for the character.

4) Compute and output the average code word length (Lavg) as well as the compression rate (vs 8-bit ASCII).

Happy coding!

Below is a HuffmanTree class .

Note that there are 2 input files.  Note the paths.

Input file 1 is c:\java\p2input1.txt.  Contents are:
A:3
B:1
C:2
D:2

Input file 2 is c:\java\p2input2.txt.  Contents are:
A:20
B:2
C:5
D:15
E:10
F:100
G:80
H:175
I:150
J:6
K:100