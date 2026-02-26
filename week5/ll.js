// created a class called Node that has a constructor
// that takes in a value and sets the next property to null

class Node {
  constructor(value) {
    this.value = value;
    this.next = null;
    this.previous = null;
  }
}

class LinkedList {
  constructor() {
    this.head = null;
    // if we want a double linked list we need to add a tail
    this.tail = null;
  }

  addtoHead(value) {
    const newNode = new Node(value);
    const formerHead = this.head;

    this.head = newNode;

    if (formerHead) {
      formerHead.previous = newNode;
      newNode.next = formerHead;
    } else {
      this.tail = newNode;
    }
  }
}
