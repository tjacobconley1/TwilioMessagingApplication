const express = require('express');
const twilio = require('twilio');
const bodyParser = require('body-parser');
const app = express();
const port = 3000;

// Twilio credentials
//const accountSid = 'YOUR_ACCOUNT_SID'; // Your Account SID
//const authToken = 'YOUR_AUTH_TOKEN'; // Your Auth Token
const accountSid = 'AC5e990a3c1a51ba0f1ae4480c4620c299'; // Your Account SID
const authToken = 'b3ae15b4b87573d044e833b7eb1163fa'; // Your Auth Token
const client = twilio(accountSid, authToken);

app.use(bodyParser.json());

// To send SMS
app.post('/send_sms', (req, res) => {
  const { to, body } = req.body;

  client.messages
    .create({
      body: body,
      from: '+18885551234', // Your Twilio phone number
      to: to
    })
    .then((message) => res.json({ status: 'success', sid: message.sid }))
    .catch((error) => res.json({ status: 'error', message: error.message }));
});

app.post("/log", (req, res) => {
  const { logType, logMessage } = req.body;
  console.log(`[${logType}] ${logMessage}`);
  res.json({ logId: 'log_' + Date.now() }); // Just a dummy response
});

app.post("/authenticate", (req, res) => {
    const { username, password } = req.body;
})

app.listen(port, () => {
  console.log(`Server running on port ${port}`);
});
