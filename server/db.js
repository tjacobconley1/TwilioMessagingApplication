// db.js
const sqlite3 = require('sqlite3').verbose();
const db = new sqlite3.Database('messages.db');

// Create table if it doesn't exist
db.serialize(() => {
  db.run(`
    CREATE TABLE IF NOT EXISTS messages (
      id INTEGER PRIMARY KEY AUTOINCREMENT,
      "to" TEXT,
      body TEXT,
      sid TEXT,
      status TEXT,
      sent_at DATETIME DEFAULT CURRENT_TIMESTAMP
    )
  `);
});

module.exports = db;
