# SplayTree

## About The Project

A  program  that implements  node  insertion,  deletion  and  search  operation  on  Splay  trees.

Program accepts input from input file which contains a series of command and integer pair separated by space like below:

```sh
   insert 1
   insert 2
   remove 2
   insert 5
   remove 1
   find 5
   insert 19
   find 15
   insert 121
   remove 3
   ```


After each operation executed, program prints level order traversal of tree. If there is no left or right child element of any internal node, it prints a hyphen instead. For above input file, the output is like below:

```sh
    1
    2 1 -
    1
    5 1 -
    5
    5
    19 5 -
    5 - 19
    12 5 19
    5 - 12 - - - 19
```

## Built With

* Java

## Getting Started



* ```sh
    javac SplayTree.java

   ```

* ```sh
   java SplayTree input.txt output.txt

   ```


## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request


## License

Distributed under the MIT License. See `LICENSE` for more information.


## Contact

Nida Dinç - niddinc@gmail.com

Project Link: [https://github.com/nidadinch/SplayTree](https://github.com/nidadinch/SplayTree)
