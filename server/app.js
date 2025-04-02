const express = require('express');
const twilio = require('twilio');
const bodyParser = require('body-parser');
const app = express();

const port = 3000;

// Twilio credentials
const accountSid = 'YOUR_ACCOUNT_SID'; // Your Account SID
const authToken = 'YOUR_AUTH_TOKEN'; // Your Auth Token
const client = twilio(accountSid, authToken);
const db = require('./db');


app.use(bodyParser.json());

app.use(express.static('public'));

// To send SMS
app.post('/send_sms', (req, res) => {
  const { to, body } = req.body;

  client.messages
    .create({
      body: body,
      from: '+18885551234', // Your Twilio phone number
      to: to
    })
    .then((message) => {
          // Store in DB
          db.run(`
            INSERT INTO messages ("to", body, sid, status)
            VALUES (?, ?, ?, ?)
          `, [to, body, message.sid, 'success'], (err) => {
            if (err) console.error('DB insert error:', err.message);
          });

          res.json({ status: 'success', sid: message.sid })

    })
    .catch((error) => {

        // Optional: log errors too
        db.run(`
            INSERT INTO messages ("to", body, sid, status)
            VALUES (?, ?, ?, ?)
            `, [to, body, null, 'error: ' + error.message], (err) => {
            if (err) console.error('DB insert error:', err.message);
        });
        res.json({ status: 'error', message: error.message })

    });
});

app.post("/log", (req, res) => {
  const { logType, logMessage } = req.body;
  console.log(`[${logType}] ${logMessage}`);
  res.json({ logId: 'log_' + Date.now() }); // Just a dummy response
});

// Get all messages from the DB
app.get('/messages', (req, res) => {
  db.all('SELECT * FROM messages ORDER BY sent_at DESC', [], (err, rows) => {
    if (err) {
      console.error('DB read error:', err.message);
      res.status(500).json({ status: 'error', message: err.message });
    } else {
      res.json({ status: 'success', data: rows });
    }
  });
});


//app.post("/authenticate", (req, res) => {
//    const { username, password } = req.body;
//})

app.listen(port, () => {
  console.log(`Server running on port ${port}`);
});
