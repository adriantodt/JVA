# Java VLC API

Get access to the VLC's REST API and it's information with this API.

## Getting Started

### 1. Enable access to VLC HTTP API.

![Press Ctrl + P](https://i.imgur.com/qq37LW3.png)

![Switch to full preferences view](https://i.imgur.com/VSaU7Sq.png)

![Enable HTTP Interface](https://i.imgur.com/hXaWAi6.png)

![Set a Password](https://i.imgur.com/ZbfFDxv.png)

#### Warning:
Due to VideoLan's weird logics, you NEED to set a Password, otherwise it WON'T WORK..

### 2. Create the JVA object

**Example:**

````java
JVA jva = new JVA("<the password you set>");
````

You can also connect to remote VLCs, by setting the 

````java
JVA jva = new JVA("http://192.168.0.99:8080", "<password of the remote player>");
````
