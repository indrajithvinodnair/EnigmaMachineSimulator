# Enigma Machine Simulator

This project is a Java-based simulation of the Enigma machine, a cipher device used by the German military during World War II.

## Features

- Simulates the Enigma machine with multiple rotors and reflectors.
- Customizable plugboard for additional letter swaps.
- Supports multiple rotor configurations and reflectors.
- Allows user to input their own settings for rotor positions, ring settings, and plugboard mappings.

## Getting Started

### Read more about the actual machine
I highly recommend reading and understanding the concepts behind the actual machine before jumping into the code or attempting modifications of anysort.
I have used this [How did the Enigma Machine work?](https://www.youtube.com/watch?v=ybkkiGtJmkM) video to learn a lot about the intricasies of the workings of the machine.
More details of the machine can be found from this [resource](https://www.cryptomuseum.com/crypto/enigma/working.htm).

This was initially planned as just a java based implimentation of the machine, you can still find the initial plain java implementation here in this [feature branch](https://github.com/indrajithvinodnair/EnigmaMachineSimulator/tree/java_implementation-(console-based)). But at a later point , just to get a feel of it, I wanted to implement a springboot backend and host it somewhere, getting to know docker systems and other infrastructure related asepcts such as setting env vars for different envs. So I focused much on the BE, felt like [Khaosdoctor's](https://github.com/khaosdoctor/enigmajs) UI is the best thats out there, hence reused it making tiny tweaks here and there. 


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


### Note
If you are planning to deploy this to somewhere other than render.com, you might probably have to make use of the docker-compose file. The current docker-compose file would require an additional modification to make it deployable somewhere else, it is to add the env var that sets the backend api url for the Frontend docker image. You can set it like
```
frontend:
    build: 
      context: ./Frontend
      arguments:
        # inject the value provided by your root .env file, or an environment variable
        - VITE_BACKEND_URL=${VITE_BACKEND_URL}
    ports:
      - "80:80"
    networks:
      - enigma-encrypter-network
    depends_on:
      - backend
```
