# advent-of-code-2019

![GitHub last commit](https://img.shields.io/github/last-commit/dbubenheim/advent-of-code-2019?style=for-the-badge) 
![Travis (.org)](https://img.shields.io/travis/dbubenheim/advent-of-code-2019?style=for-the-badge)
![GitHub](https://img.shields.io/github/license/dbubenheim/advent-of-code-2019?label=license&style=for-the-badge)
![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/dbubenheim/advent-of-code-2019?style=for-the-badge)
![GitHub top language](https://img.shields.io/github/languages/top/dbubenheim/advent-of-code-2019?style=for-the-badge)
![GitHub search hit counter](https://img.shields.io/github/search/dbubenheim/advent-of-code-2019/goto?style=for-the-badge)

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

What things you need to install the software and how to install them

- JDK 17 or later

### Installing

You can setup the project into an IDE by importing the folder. The following command shows how to do it in IntelliJ IDEA.

```
idea advent-of-code-2019
```

## Running the tests

You can easily run the automated tests of this project by the shipped gradle wrapper

```
❯ ./gradlew clean test
Starting a Gradle Daemon (subsequent builds will be faster)

> Task :app:compileKotlin

> Task :app:test

...

BUILD SUCCESSFUL in 1m 44s
5 actionable tasks: 5 executed
```

### Break down into tests

There are unit tests for all of the solved days. Each one is in a separate package located under `src/test/kotlin`.

```
── test
    ├── kotlin
    │   └── advent
    │       └── of
    │           └── code
    │               ├── day01
    │               │   └── Day01Test.kt
    │               ├── day02
    │               │   └── Day02Test.kt
    │               ├── day03
    │               │   └── Day03Test.kt
```

## Built With

* [Kotlin](https://kotlinlang.org/) - The programming language used
* [Gradle](https://gradle.org/) - Dependency Management

## Authors

* **Daniel Bubenheim** - *Initial work* - [dbubenheim](https://github.com/dbubenheim)

See also the list of [contributors](https://github.com/dbubenheim/advent-of-code-2019/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details