# Enigma Machine Simulator

This project is a Java-based simulation of the Enigma machine, a cipher device used by the German military during World War II.

## Features

- Simulates the Enigma machine with multiple rotors and reflectors.
- Customizable plugboard for additional letter swaps.
- Supports multiple rotor configurations and reflectors.
- Allows user to input their own settings for rotor positions, ring settings, and plugboard mappings.

## Getting Started

### Read more about the actual machine
I really recommend reading and understanding the concepts behind the actual machine before jumping into the code or attempting modifications of anysort.
I have used this [How did the Enigma Machine work?](https://www.youtube.com/watch?v=ybkkiGtJmkM) video to learn a lot about the intricasies of the workings of the machine.
More details of the machine can be found from this [resource](https://www.cryptomuseum.com/crypto/enigma/working.htm).


### Prerequisites

- Java 17 or higher
- Maven (for dependency management)
- Node > v18

### Running the app in your local
If you have docker installed, you can get started right away by invoking docker-compose on the docker-compose.yml file.
If not and you wish to spin up the Frontend and Backend applications seperately, then you can do that but remember to modify the Backend's CORS
allowed origins according to the port number of your FE app in the file Backend/src/main/java/com/wehrmacht/enigma/config/WebConfig.java.

### Demo video

Encryption Process - Sets up the rotors,ring settings, offsets and plugboard mappings
![](./gifs/encryption.gif)



Decryption Process - Uses the same configs used for the encryption process
![](./gifs/decryption.gif)
