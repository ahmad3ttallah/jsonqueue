# jsonqueue
An implementation of a queue that can be converted to JSON string and generated from JSON string

Some times you have a queue that is needed to be save locally in a file or in any mean in a String format. The normal convert to JSON string of your objects and connections will not be presented correctly for FRONT, REAR and also will be huge nested objects that will make it hard to easilly check the JSON string.

So I developed a simple implementation to generate a JSON that looks as below:

{
  "queueContent": {
    "0": {
      "data": 0
    },
    "1": {
      "data": 1
    },
    "2": {
      "data": 2
    },
    "3": {
      "data": 3
    },
    "4": {
      "data": 4
    },
    "5": {
      "data": 5
    },
    "6": {
      "data": 6
    },
    "7": {
      "data": 7
    },
    "8": {
      "data": 8
    },
    "9": {
      "data": 9
    }
  },
  "front": 0,
  "rear": 9
}

And you can parse it and directly call puch(DATA), DATA pop() methods. And the ability to save it locally.
