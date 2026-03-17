/**
 Real Engineering Use Case for Linked Lists
Linked lists appear in many real systems:

• music playlists
• browser navigation
• memory allocation systems
• graph traversal
• adjacency lists in networking

One of the most intuitive examples is a music playlist.

Think about Spotify.

When you press "next song", the system simply moves to the next node.

Song A → Song B → Song C → Song D
If you insert a song after Song B:

Song A → Song B → Song X → Song C → Song D
Only one pointer changes.

That is extremely efficient.
 */

// first step in Creating a LL

// NODE
class Node {
  constructor(value) {
    this.value = value;
    this.next = null;
  }
}

// Create the Linked List

class Linkedlist {
  constructor() {
    this.head = null;
  }

  // Add Elements to the list
  // 1. Old Node Points to the New Node
  // 2. New Node becomes the tail
  addFirst(value) {
    const newNode = new Node(value);

    if (this.head === null) {
      this.head = newNode;
      return;
    }
    newNode.next = this.head;
    this.head = newNode;
  }

  addLast(value) {
    const newNode = new Node(value);

    if (this.head === null) {
      this.head = newNode;
      return;
    }

    let current = this.head;
    while (current.next !== null) {
      current = current.next;
    }
    current.next = newNode;
  }

  removeTail() {
    if (this.head === null) {
      return null;
    }

    let current = this.head;
    while (current.next.next !== null) {
      current = current.next;
    }
    const removed = current.next;
    current.next = null;
    return removed;
  }
  removeHead() {
    if (this.head === null) {
      return null;
    }
    const value = this.head.value;
    this.head = this.head.next;
    return value;
  }

  printList() {
    let current = this.head;
    while (current !== null) {
      console.log(current.value);
      current = current.next;
    }
  }
}

///// CODE DEMO WORKS

// const list = new Linkedlist();
// list.addLast(1);
// list.addLast(2);
// list.addLast(3);

// list.printList();
// console.log(list);

/**
 Applying Linked Lists — Playlist System
Now we apply this to a real use case.

A playlist behaves exactly like a linked list.

Songs are connected sequentially.

Song A → Song B → Song C
When the user presses "next", we simply move forward.
 */

class Playlist {
  constructor() {
    this.list = new Linkedlist();
  }
  addSong(song) {
    this.list.addFirst(song);
  }
  showPlaylist() {
    this.list.printList();
  }
}

// Use our Playlist

// const playlist = new Playlist();
// playlist.addSong("Eternal Sunshine");
// playlist.addSong("Pokemon Theme Song");
// playlist.addSong("kirk");

// playlist.showPlaylist();

// Stack

class Stack {
  constructor() {
    this.list = new Linkedlist();
  }
  push(value) {
    this.list.addFirst(value);
  }
  pop() {
    return this.list.removeHead();
  }
  peek() {
    return this.list.head ? this.head.value : null;
  }
}

// Undo Manager

class UndoManager {
  constructor() {
    this.stack = new Stack();
  }
  perform(action) {
    console.log("action: ", action);
    this.stack.push(action);
  }
  undo() {
    const action = this.stack.pop();
    if (!action) {
      console.log("Nothing to undo");
    }
    console.log("Undo :", action);
  }
}

// const editor = new UndoManager();

// editor.perform("type hello");
// editor.perform("type world");
// editor.perform("delete world");

// editor.undo();
// editor.undo();

// Queue

class Queue {
  constructor() {
    this.list = new Linkedlist();
  }
}
