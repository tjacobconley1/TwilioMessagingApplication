const express = require('express');
const twilio = require('twilio');
const bodyParser = require('body-parser');
const app = express();
const port = 3000;

// Twilio credentials
const accountSid = 'YOUR_ACCOUNT_SID'; // Your Account SID
const authToken = 'YOUR_AUTH_TOKEN'; // Your Auth Token
const client = twilio(accountSid, authToken);

app.use(bodyParser.json());

// To send SMS
app.post('/send_sms', (req, res) => {
  const { to, body } = req.body;

  client.messages
    .create({
      body: body,
      from: '+18005555555', // Your Twilio phone number
      to: to
    })
    .then((message) => res.json({ status: 'success', sid: message.sid }))
    .catch((error) => res.json({ status: 'error', message: error.message }));
});

app.listen(port, () => {
  console.log(`Server running on port ${port}`);
});
