# Play-Scala 2.6 in : Websocket Example
## With       : Scala, Akka Actor

A example where a client will establish a connection with Play server. The websocket is handled
and directed by Akka actor. 

Play server will send updated information to the web client based on the "Tick" scheduler

## Versionning
Play Version:           2.6

Scala Version:          2.12.2

SBT Plugin Version :    2.6.2   

## Running the application
* `git clone https://github.com/fnoyer/scala-akka-websocket.git`
* `play-scala-akka>sbt`
* `[scala-akka] $ run`

# The Console message shown during execution
## Web Client Message

### Initiation of the websocket connection
`Sending init to host[object Event]`

### Informative message received from the Actor at the initial phase
`(index):42 Received message from actor: {"message":"init message received from web client"}`

### The data {received by the client}={sent by the server} every 'Tick' seconds.
`7(index):42 Received message from actor: {"person":{"firstName":"Jorge","lastName":"Medeck"}}`

## Server Console Log
### Registering a new client
```aidl
[info] application - Host Actor :: Registering a new web client. There is 0 connected client(s)
```
### Unregistering a new client
```aidl
[info] application - Host Actor :: Unregistering a web client... There is 0 remaining connected client(s)
```

### Sending message to web client
```aidl
[info] application - Sending data to 1 web clients and the object sent is : Person(Jorge,Medeck)
```

Thank You to @dhinojosa who helped me to finish this first Akka example.
